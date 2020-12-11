package seatingsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SeatingSystem {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-11-seating-system/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<List<Character>> seatingArea = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            List<Character> row = new ArrayList<>();

            for (char c: line.toCharArray()) {
                row.add(c);
            }

            seatingArea.add(row);
        }

        SeatingArea currentSeatingArea = new SeatingArea(seatingArea);
        boolean match = false;

        while (!match) {
            SeatingArea nextSeatingArea = currentSeatingArea.applyRules();

            if (nextSeatingArea.equals(currentSeatingArea)) {
                match = true;
            }

            currentSeatingArea = nextSeatingArea;
        }

        System.out.println(currentSeatingArea.occupiedSeatCount());
    }
}
