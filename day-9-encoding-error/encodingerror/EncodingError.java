package encodingerror;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EncodingError {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-9-encoding-error/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Long> numbers = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            numbers.add(Long.parseLong(line));
        }

        long invalidNumber = -1;

        for (int i = 25; i < numbers.size(); i++) {
            boolean isValid = false;
            long numberUnderInspection = numbers.get(i);

            for (int j = i - 25; j < i; j++) {
                for (int k = i - 25; k < i; k++) {
                    if (j == k) {
                        continue;
                    }

                    if (numbers.get(j) + numbers.get(k) == numberUnderInspection) {
                        isValid = true;
                        break;
                    }
                }
            }

            if (!isValid) {
                invalidNumber = numberUnderInspection;
                System.out.println(invalidNumber);
                break;
            }
        }

        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                List<Long> contiguousNumbers = new ArrayList<>();
                long contiguousSum = 0;

                for (int k = i; k < j; k++) {
                    contiguousNumbers.add(numbers.get(k));
                    contiguousSum += numbers.get(k);
                }

                if (contiguousNumbers.size() == 1) {
                    continue;
                }

                if (contiguousSum == invalidNumber) {
                    List<Long> sortedContiguousNumbers = contiguousNumbers.stream()
                            .sorted(Comparator.naturalOrder())
                            .collect(Collectors.toList());

                    long minimumContiguousNumber = sortedContiguousNumbers.get(0);
                    long maximumContiguousNumber = sortedContiguousNumbers.get(sortedContiguousNumbers.size() - 1);
                    long encryptionWeakness = minimumContiguousNumber + maximumContiguousNumber;

                    System.out.println(encryptionWeakness);
                }
            }
        }
    }
}
