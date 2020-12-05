package binaryboarding;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

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


        Integer maxSeatId = boardingPasses.stream()
                .map(BinaryBoarding::calculateSeatId)
                .max(Comparator.naturalOrder())
                .orElse(null);

        System.out.println(maxSeatId);
    }

    public static int calculateSeatId(String boardingPass) {
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
