package monstermessagespart2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonsterMessages {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-19-monster-messages/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        Map<Integer, String> rules = new HashMap<>();

        while (!(line = bufferedReader.readLine()).equals("")) {
            Pattern rulePattern = Pattern.compile("^(\\d+): (.+)$");
            Matcher ruleMatcher = rulePattern.matcher(line);

            if (ruleMatcher.find()) {
                int ruleNumber = Integer.parseInt(ruleMatcher.group(1));
                String ruleString = ruleMatcher.group(2);
                rules.put(ruleNumber, ruleString);
            }
        }

        List<String> messages = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            messages.add(line);
        }

        String rule0Regex = "^" + toRegex(rules, 0) + "$";
        Pattern rule0RegexPattern = Pattern.compile(rule0Regex);

        int matchesRule0Count = 0;

        for (String message: messages) {
            Matcher rule0RegexPatternMatcher = rule0RegexPattern.matcher(message);

            if (rule0RegexPatternMatcher.find()) {
                matchesRule0Count++;
            }
        }

        System.out.println(matchesRule0Count);
    }

    private static int rule8CallCount = 0;
    private static int rule11CallCount = 0;

    private static String toRegex(Map<Integer, String> rules, int ruleNumber) {
        if (ruleNumber == 8) {
            rule8CallCount++;
        }

        if (ruleNumber == 11) {
            rule11CallCount++;
        }

        String rule = rules.get(ruleNumber);

        if (rule.startsWith("\"") && rule.endsWith("\"")) {
            return rule.substring(1, rule.length() - 1);
        }

        Pattern digitPattern = Pattern.compile("\\d+");
        Matcher digitMatcher = digitPattern.matcher(rule);

        String regex = digitMatcher.replaceAll(matchResult -> {
            if (rule8CallCount > 100 && matchResult.group().equals("8")) {
                return "";
            }

            if (rule11CallCount > 100 && matchResult.group().equals("11")) {
                return "";
            }

            return toRegex(rules, Integer.parseInt(matchResult.group()));
        });

        regex = regex.replaceAll(" ", "");
        return "(" + regex + ")";
    }
}
