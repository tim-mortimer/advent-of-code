package binaryboarding;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BinaryBoarding {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-5-binary-boarding/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        ArrayList<String> boardingPasses = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            boardingPasses.add(line);
        }


        ArrayList<Integer> sortedSeatIds = (ArrayList<Integer>) boardingPasses.stream()
                .map(BinaryBoarding::calculateSeatId)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        System.out.println(sortedSeatIds.get(sortedSeatIds.size() - 1));

        int lastSeatId = sortedSeatIds.get(0);

        for (int i=1; i < sortedSeatIds.size(); i++) {
            if (sortedSeatIds.get(i) - lastSeatId == 2) {
                System.out.println(lastSeatId + 1);
                break;
            }

            lastSeatId = sortedSeatIds.get(i);
        }
    }

    public static Integer calculateSeatId(String boardingPass) {
        Range rowRange = new Range(0, 127);
        Range columnRange = new Range(0, 7);

        for (char c : boardingPass.toCharArray()) {
            switch (c) {
                case 'F' -> {
                    rowRange = rowRange.takeLower();
                }
                case 'B' -> {
                    rowRange = rowRange.takeUpper();
                }
                case 'R' -> {
                    columnRange = columnRange.takeUpper();
                }
                case 'L' -> {
                    columnRange = columnRange.takeLower();
                }
            }
        }

        System.out.println(rowRange);
        System.out.println(columnRange);

        return rowRange.lower() * 8 + columnRange.lower();
    }
}
