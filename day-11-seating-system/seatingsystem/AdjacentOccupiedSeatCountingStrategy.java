package seatingsystem;

import java.util.List;

public class AdjacentOccupiedSeatCountingStrategy implements OccupiedSeatCountingStrategy {

    @Override
    public int count(List<List<Character>> seatingGrid, int rowNumber, int columnNumber) {
        int adjacentOccupiedSeatCount = 0;
        List<Character> row = seatingGrid.get(rowNumber);

        for (int k = rowNumber - 1; k < rowNumber + 2; k++) {
            if (k < 0 || k > seatingGrid.size() - 1) {
                continue;
            }

            for (int l = columnNumber - 1; l < columnNumber + 2; l++) {
                if (l < 0 || l > row.size() - 1 || (k == rowNumber && l == columnNumber)) {
                    continue;
                }

                if (seatingGrid.get(k).get(l) == '#') {
                    adjacentOccupiedSeatCount++;
                }
            }
        }

        return adjacentOccupiedSeatCount;
    }

    @Override
    public int peopleTolerance() {
        return 4;
    }
}
