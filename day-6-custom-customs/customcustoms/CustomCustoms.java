package customcustoms;

import java.io.*;
import java.util.HashMap;

public class CustomCustoms {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-6-custom-customs/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        int questionCountSum = 0;
        HashMap<Character, Boolean> questionCounts = new HashMap<>();

        while((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                questionCountSum += questionCounts.keySet().size();
                questionCounts = new HashMap<>();
            }

            for(Character character: line.toCharArray()) {
                questionCounts.put(character, true);
            }
        }

        questionCountSum += questionCounts.keySet().size();
        System.out.println(questionCountSum);
    }
}
