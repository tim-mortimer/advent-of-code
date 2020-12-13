package shuttlesearch;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShuttleSearch {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-13-shuttle-search/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        int estimatedDepartTime = Integer.parseInt(bufferedReader.readLine());

        List<Integer> busIds = Arrays.stream(bufferedReader.readLine().split(","))
                .filter(id -> !id.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(estimatedDepartTime);
        System.out.println(busIds);

        boolean matchFound = false;
        int currentTime = estimatedDepartTime;

        while (!matchFound) {
            for (int busId: busIds) {
                if (currentTime % busId == 0) {
                    System.out.println((currentTime - estimatedDepartTime) * busId);
                    matchFound = true;
                }
            }

            currentTime++;
        }
    }
}
