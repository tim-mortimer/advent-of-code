package seatingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeatingArea {
    private final List<List<Character>> seatingGrid;

    public SeatingArea(List<List<Character>> seatingGrid) {
        this.seatingGrid = seatingGrid;
    }

    public SeatingArea applyRules() {
        List<List<Character>> newSeatingGrid = new ArrayList<>();

        for (int i = 0; i < seatingGrid.size(); i++) {
            List<Character> row = seatingGrid.get(i);
            List<Character> newRow = new ArrayList<>();

            for (int j = 0; j < row.size(); j++) {
                char occupancy = row.get(j);
                int adjacentOccupiedSeatCount = adjacentOccupiedSeatCount(i, j);

                boolean isUnbotheredOccupiedSeat = row.get(j) == '#' && adjacentOccupiedSeatCount < 4;
                boolean isBotheredOccupiedSeat = row.get(j) == '#' && adjacentOccupiedSeatCount >= 4;
                boolean isUnoccupiedSeatSurroundedByNoOccupiedSeats = occupancy == 'L' && adjacentOccupiedSeatCount == 0;

                if (isUnbotheredOccupiedSeat) {
                    newRow.add('#');
                } else if (isBotheredOccupiedSeat) {
                    newRow.add('L');
                } else if (isUnoccupiedSeatSurroundedByNoOccupiedSeats) {
                    newRow.add('#');
                } else {
                    newRow.add(occupancy);
                }
            }

            newSeatingGrid.add(newRow);
        }

        return new SeatingArea(newSeatingGrid);
    }

    private int adjacentOccupiedSeatCount(int i, int j) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatingArea otherSeatingArea = (SeatingArea) o;

        boolean hasSameRowCount = seatingGrid.size() == otherSeatingArea.seatingGrid.size();

        if (!hasSameRowCount) {
            return false;
        }

        for (int i = 0; i < seatingGrid.size(); i++) {
            List<Character> row = seatingGrid.get(i);
            List<Character> otherRow = otherSeatingArea.seatingGrid.get(i);

            if (row.size() != otherRow.size()) {
                return false;
            }

            for (int j = 0; j < row.size(); j++) {
                Character seat = row.get(j);
                Character otherSeat = otherRow.get(j);

                if (!seat.equals(otherSeat)) {
                    return false;
                }
            }
        }

        return true;
    }

    public int occupiedSeatCount() {
        int occupiedSeatCount = 0;

        for (List<Character> row: seatingGrid) {
            for (char c: row) {
                if (c == '#') {
                    occupiedSeatCount++;
                }
            }
        }

        return occupiedSeatCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatingGrid);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (List<Character> row: seatingGrid) {
            output.append(row.toString()).append("\n");
        }

        return output.toString();
    }
}
