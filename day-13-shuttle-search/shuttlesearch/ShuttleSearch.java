package shuttlesearch;

import java.io.*;
import java.util.ArrayList;
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
                .map(id -> id.equals("x") ? "0" : id)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        boolean matchFound = false;
        int currentTime = estimatedDepartTime;

        while (!matchFound) {
            for (int busId : busIds) {
                if (busId == 0) {
                    continue;
                }

                if (currentTime % busId == 0) {
                    System.out.println((currentTime - estimatedDepartTime) * busId);
                    matchFound = true;
                }
            }

            currentTime++;
        }

        List<Long> moduli = new ArrayList<>();
        List<Long> remainders = new ArrayList<>();
        long n = 1;
        List<Long> nDividedByModuli = new ArrayList<>();
        List<Long> inverses = new ArrayList<>();

        for (int busId : busIds) {
            if (busId == 0) {
                continue;
            }

            int index = busIds.indexOf(busId);
            moduli.add((long) busId);
            long remainder = busId - index;
            remainders.add(remainder);
        }

        for (long nI : moduli) {
            n *= nI;
        }

        for (long modulus : moduli) {
            nDividedByModuli.add(n / modulus);
        }

        for (int i = 0; i < nDividedByModuli.size(); i++) {
            long currentNi = nDividedByModuli.get(i);
            long modulus = moduli.get(i);
            long remainder = currentNi % modulus;
            long inverse = 1;

            while ((remainder * inverse) % modulus != 1) {
                inverse++;
            }

            inverses.add(inverse);
        }

        long x = 0;

        for (int i = 0; i < remainders.size(); i++) {
            long remainder = remainders.get(i);
            long NI = nDividedByModuli.get(i);
            long inverse = inverses.get(i);
            x += remainder * NI * inverse;
        }

        System.out.println(x % n);
    }
}
