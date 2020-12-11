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

        List<List<Character>> seatingGrid = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            List<Character> row = new ArrayList<>();

            for (char c: line.toCharArray()) {
                row.add(c);
            }

            seatingGrid.add(row);
        }

        SeatingArea seatingArea = new SeatingArea(seatingGrid);

        SeatingArea adjacentStrategyStableSeatingArea = findStableSeatingArrangement(seatingArea, new AdjacentOccupiedSeatCountingStrategy());
        System.out.println(adjacentStrategyStableSeatingArea.occupiedSeatCount());

        SeatingArea visibleStrategyStableSeatingArea = findStableSeatingArrangement(seatingArea, new VisibleOccupiedSeatCountingStrategy());
        System.out.println(visibleStrategyStableSeatingArea.occupiedSeatCount());
    }

    private static SeatingArea findStableSeatingArrangement(SeatingArea seatingArea, OccupiedSeatCountingStrategy occupiedSeatCountingStrategy) {
        SeatingArea currentSeatingArea = seatingArea;
        boolean match = false;

        while (!match) {
            SeatingArea nextSeatingArea = currentSeatingArea.applyRules(occupiedSeatCountingStrategy);

            if (nextSeatingArea.equals(currentSeatingArea)) {
                match = true;
            }

            currentSeatingArea = nextSeatingArea;
        }

        return currentSeatingArea;
    }
}
