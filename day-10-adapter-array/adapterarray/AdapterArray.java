package adapterarray;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AdapterArray {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-10-adapter-array/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Integer> joltages = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            joltages.add(Integer.parseInt(line));
        }

        List<Integer> sortedJoltages = joltages.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        int highestAdapterJoltage = sortedJoltages.get(sortedJoltages.size() - 1);
        sortedJoltages.add(highestAdapterJoltage + 3);

        int previousJoltage = 0;
        Map<Integer, Integer> joltageDifferences = new HashMap<>();

        for (int joltage : sortedJoltages) {
            int joltageDifference = joltage - previousJoltage;

            int currentJoltageDifferenceCount = joltageDifferences.getOrDefault(joltageDifference, 0);
            joltageDifferences.put(joltageDifference, ++currentJoltageDifferenceCount);

            previousJoltage = joltage;
        }

        int oneJoltageDifferenceCount = joltageDifferences.getOrDefault(1, 0);
        int threeJoltageDifferenceCount = joltageDifferences.getOrDefault(3, 0);
        System.out.println(oneJoltageDifferenceCount * threeJoltageDifferenceCount);

        sortedJoltages.add(0, 0);

        Map<Integer, List<Integer>> possibleSteps = new HashMap<>();

        for (int i = 0; i < sortedJoltages.size(); i++) {
            int currentJoltage = sortedJoltages.get(i);
            List<Integer> possibleDestinations = new ArrayList<>();

            for (int j = i + 1; j < i + 4; j++) {
                if (j > sortedJoltages.size() - 1) {
                    continue;
                }

                if (sortedJoltages.get(j) - currentJoltage <= 3) {
                    possibleDestinations.add(j);
                }
            }

            possibleSteps.put(i, possibleDestinations);
        }

        System.out.println(pathCountFrom(possibleSteps, new HashMap<>(), 0));
    }

    private static long pathCountFrom(Map<Integer, List<Integer>> possibleSteps, Map<Integer, Long> pathCountCache, int i) {
        if (pathCountCache.containsKey(i)) {
            return pathCountCache.get(i);
        }

        long pathCount = 0;

        List<Integer> possibleDestinations = possibleSteps.get(i);

        for (int possibleDestination : possibleDestinations) {
            pathCount += pathCountFrom(possibleSteps, pathCountCache, possibleDestination);
        }

        pathCountCache.put(i, pathCount);

        return Math.max(1, pathCount);
    }
}
