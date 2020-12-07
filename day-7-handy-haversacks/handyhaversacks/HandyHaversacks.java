package handyhaversacks;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandyHaversacks {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-7-handy-haversacks/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        Map<String, HashSet<String>> allowedContainingBags = new HashMap<>();

        while ((line = bufferedReader.readLine()) != null) {
            Pattern pattern = Pattern.compile("([a-z\\s]+) bags contain (?:no other bags|((?:\\d+ [a-z\\s]+ bags?[,\\s]*)+))");
            Matcher matcher = pattern.matcher(line);

            if (!matcher.find()) {
                continue;
            }

            String containingBag = matcher.group(1);
            String containedBags = matcher.group(2);

            if (containedBags == null) {
                continue;
            }

            Pattern containedBagsPattern = Pattern.compile("(\\d+) ([a-z\\s]+) bags?");
            Matcher containingBagPatternMatcher = containedBagsPattern.matcher(containedBags);

            while (containingBagPatternMatcher.find()) {
                String containedBag = containingBagPatternMatcher.group(2);

                HashSet<String> allowedContainingBagsForBag = allowedContainingBags.getOrDefault(
                        containedBag,
                        new HashSet<>()
                );

                allowedContainingBagsForBag.add(containingBag);

                allowedContainingBags.put(containedBag, allowedContainingBagsForBag);
            }
        }

        System.out.println(allowedContainingBags(allowedContainingBags, "shiny gold").size());
    }

    private static List<String> allowedContainingBags(Map<String, HashSet<String>> allowedContainingBags, String colour) {
        List<String> bags = new ArrayList<>();

        for (String allowedBag: allowedContainingBags.get(colour)) {
            bags.add(allowedBag);

            if (allowedContainingBags.get(allowedBag) == null) {
                continue;
            }

            for (String allowedContainingBagsForAllowedBag: allowedContainingBags.get(allowedBag)) {
                List<String> potentialBags = allowedContainingBags(allowedContainingBags, allowedBag);
                for (String potentialBag: potentialBags) {
                    if (!bags.contains(potentialBag)) {
                        bags.add(potentialBag);
                    }
                }
            }
        }

        return bags;
    }
}
