package customcustomspart2;

import java.io.*;
import java.util.HashMap;

public class CustomCustomsPart2 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-6-custom-customs/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        int questionCountSum = 0;
        int groupSize = 0;
        HashMap<Character, Integer> questionCounts = new HashMap<>();

        while((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                int finalGroupSize = groupSize;
                questionCountSum += questionCounts.values()
                        .stream()
                        .filter(value -> value == finalGroupSize)
                        .count();


                questionCounts = new HashMap<>();
                groupSize = 0;
                continue;
            }

            groupSize++;

            for(Character character: line.toCharArray()) {
                int characterCount = questionCounts.getOrDefault(character, 0);
                questionCounts.put(character, ++characterCount);
            }
        }

        int finalGroupSize = groupSize;
        questionCountSum += questionCounts.values()
                .stream()
                .filter(value -> value == finalGroupSize)
                .count();

        System.out.println(questionCountSum);
    }
}
