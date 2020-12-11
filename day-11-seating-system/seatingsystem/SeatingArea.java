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
        return applyRules(new AdjacentOccupiedSeatCountingStrategy());
    }

    public SeatingArea applyRules(OccupiedSeatCountingStrategy strategy) {
        List<List<Character>> newSeatingGrid = new ArrayList<>();

        for (int rowNumber = 0; rowNumber < seatingGrid.size(); rowNumber++) {
            List<Character> row = seatingGrid.get(rowNumber);
            List<Character> newRow = new ArrayList<>();

            for (int columnNumber = 0; columnNumber < row.size(); columnNumber++) {
                char occupancy = row.get(columnNumber);
                int adjacentOccupiedSeatCount = strategy.count(seatingGrid, rowNumber, columnNumber);
                int peopleTolerance = strategy.peopleTolerance();

                boolean isTolerableOccupiedSeat = row.get(columnNumber) == '#' && adjacentOccupiedSeatCount < peopleTolerance;
                boolean isUntolerableOccupiedSeat = row.get(columnNumber) == '#' && adjacentOccupiedSeatCount >= peopleTolerance;
                boolean isUnoccupiedSeatSurroundedByNoOccupiedSeats = occupancy == 'L' && adjacentOccupiedSeatCount == 0;

                if (isTolerableOccupiedSeat) {
                    newRow.add('#');
                } else if (isUntolerableOccupiedSeat) {
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
