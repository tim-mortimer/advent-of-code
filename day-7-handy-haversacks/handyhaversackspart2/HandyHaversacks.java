package handyhaversackspart2;

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

        Map<String, Map<String, Integer>> containedBags = new HashMap<>();

        while ((line = bufferedReader.readLine()) != null) {
            Pattern pattern = Pattern.compile("([a-z\\s]+) bags contain (?:no other bags|((?:\\d+ [a-z\\s]+ bags?[,\\s]*)+))");
            Matcher matcher = pattern.matcher(line);

            if (!matcher.find()) {
                continue;
            }

            String containingBag = matcher.group(1);
            String containedBagCounts = matcher.group(2);

            if (containedBagCounts == null) {
                continue;
            }

            Pattern containedBagCountsPattern = Pattern.compile("(\\d+) ([a-z\\s]+) bags?");
            Matcher containingBagCountsPatternMatcher = containedBagCountsPattern.matcher(containedBagCounts);

            Map<String, Integer> containedBagsForBag = new HashMap<>();

            while (containingBagCountsPatternMatcher.find()) {
                int containedBagCount = Integer.parseInt(containingBagCountsPatternMatcher.group(1));
                String containedBag = containingBagCountsPatternMatcher.group(2);

                containedBagsForBag.put(containedBag, containedBagCount);
            }

            containedBags.put(containingBag, containedBagsForBag);
        }

        System.out.println(containedBagCount(containedBags, "shiny gold"));
    }

    private static int containedBagCount(Map<String, Map<String, Integer>> containedBags, String colour) {
        int containedBagCount = 0;
        Map<String, Integer> containedBagsForColour = containedBags.get(colour);

        if (containedBagsForColour != null) {
            for (String containedBag : containedBagsForColour.keySet()) {
                int containedBagMultiple = containedBagsForColour.get(containedBag);
                containedBagCount += containedBagMultiple + containedBagMultiple * containedBagCount(containedBags, containedBag);
            }
        }

        return containedBagCount;
    }
}
