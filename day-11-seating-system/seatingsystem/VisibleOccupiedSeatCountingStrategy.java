package seatingsystem;

import java.util.List;

public class VisibleOccupiedSeatCountingStrategy implements OccupiedSeatCountingStrategy {

    @Override
    public int count(List<List<Character>> seatingGrid, int rowNumber, int columnNumber) {
        int visibleOccupiedSeatCount = 0;
        int gridWidth = seatingGrid.get(0).size();
        int gridHeight = seatingGrid.size();

        List<List<Integer>> trajectories = List.of(
                List.of(0, 1),
                List.of(1, 1),
                List.of(1, 0),
                List.of(1, -1),
                List.of(0, -1),
                List.of(-1, -1),
                List.of(-1, 0),
                List.of(-1, 1)
        );

        for (List<Integer> trajectory: trajectories) {
            int currentX = columnNumber;
            int currentY = rowNumber;
            int xStep = trajectory.get(0);
            int yStep = trajectory.get(1);
            boolean seatFound = false;

            while (!seatFound) {
                int nextX = currentX + xStep;
                int nextY = currentY + yStep;
                boolean xIsOutOfBounds = nextX < 0 || nextX > gridWidth - 1;
                boolean yIsOutOfBounds = nextY < 0 || nextY > gridHeight - 1;

                if (xIsOutOfBounds || yIsOutOfBounds) {
                    break;
                }

                char occupancy = seatingGrid.get(nextY).get(nextX);

                if (occupancy == 'L') {
                    seatFound = true;
                }

                if (occupancy == '#') {
                    visibleOccupiedSeatCount++;
                    seatFound = true;
                }

                currentX = nextX;
                currentY = nextY;
            }
        }

        return visibleOccupiedSeatCount;
    }

    @Override
    public int peopleTolerance() {
        return 5;
    }
}
