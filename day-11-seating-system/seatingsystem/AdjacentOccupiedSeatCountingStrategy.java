package seatingsystem;

import java.util.List;

public class AdjacentOccupiedSeatCountingStrategy implements OccupiedSeatCountingStrategy {

    @Override
    public int count(List<List<Character>> seatingGrid, int i, int j) {
        int adjacentOccupiedSeatCount = 0;
        List<Character> row = seatingGrid.get(i);

        for (int k = i - 1; k < i + 2; k++) {
            if (k < 0 || k > seatingGrid.size() - 1) {
                continue;
            }

            for (int l = j - 1; l < j + 2; l++) {
                if (l < 0 || l > row.size() - 1 || (k == i && l == j)) {
                    continue;
                }

                if (seatingGrid.get(k).get(l) == '#') {
                    adjacentOccupiedSeatCount++;
                }
            }
        }

        return adjacentOccupiedSeatCount;
    }
}
