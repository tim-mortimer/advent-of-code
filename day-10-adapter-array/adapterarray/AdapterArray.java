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
    }
}
