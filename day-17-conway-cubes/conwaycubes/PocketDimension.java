package conwaycubes;

import java.util.ArrayList;
import java.util.List;

public class PocketDimension {
    private final List<List<List<Character>>> grid;

    public PocketDimension(List<List<List<Character>>> grid) {
        this.grid = grid;
    }

    public PocketDimension(PocketDimension expandedPocketDimension) {
        int zSize = expandedPocketDimension.grid().size();
        int xySize = expandedPocketDimension.grid().get(0).size();

        List<List<List<Character>>> newGrid = new ArrayList<>();

        for (int z = 0; z < zSize; z++) {
            List<List<Character>> newZPlane = new ArrayList<>();

            for (int y = 0; y < xySize; y++) {
                List<Character> newY = new ArrayList<>();

                for (int x = 0; x < xySize; x++) {
                    newY.add(expandedPocketDimension.grid().get(z).get(y).get(x));
                }

                newZPlane.add(newY);
            }

            newGrid.add(newZPlane);
        }

        grid = newGrid;
    }

    public char get(int x, int y) {
        return grid.get(0).get(y).get(x);
    }

    public char get(int x, int y, int z) {
        int gridSize = grid.size();
        int zOffset = gridSize / 2;
        return grid.get(z + zOffset).get(y).get(x);
    }

    public PocketDimension expand() {
        List<List<List<Character>>> newGrid = new ArrayList<>();

        newGrid.add(createInactiveZPlane(grid.get(0).size() + 2));

        for (int i = 0; i < grid.size(); i++) {
            newGrid.add(expandZPlane(grid.get(i)));
        }

        newGrid.add(createInactiveZPlane(grid.get(0).size() + 2));

        return new PocketDimension(newGrid);
    }

    private List<List<Character>> expandZPlane(List<List<Character>> zPlane) {
        List<List<Character>> newZPlane = new ArrayList<>();

        int xSize = zPlane.get(0).size();
        List<Character> extraY = createInactiveList(xSize + 2);

        newZPlane.add(extraY);

        for (List<Character> y: zPlane) {
            List<Character> newY = new ArrayList<>();
            newY.add('.');
            newY.addAll(y);
            newY.add('.');
            newZPlane.add(newY);
        }

        newZPlane.add(extraY);
        return newZPlane;
    }

    private List<List<Character>> createInactiveZPlane(int size) {
        List<List<Character>> inactiveZPlane = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            inactiveZPlane.add(createInactiveList(size));
        }

        return inactiveZPlane;
    }

    private List<Character> createInactiveList(int size) {
        List<Character> inactiveList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            inactiveList.add('.');
        }

        return inactiveList;
    }

    public PocketDimension performCycle() {
        PocketDimension expandedPocketDimension = this.expand();
        int zSize = expandedPocketDimension.grid().size();
        int xySize = expandedPocketDimension.grid().get(0).size();

        PocketDimension result = new PocketDimension(expandedPocketDimension);

        for (int z = 0; z < zSize; z++) {
            for (int y = 0; y < xySize; y++) {
                for (int x = 0; x < xySize; x++) {
                    int activeNeighbourCount = expandedPocketDimension.countActiveNeighbours(x, y, z);

                    if (expandedPocketDimension.grid().get(z).get(y).get(x) == '#') {
                        boolean hasTwoOrThreeActiveNeighbours = activeNeighbourCount == 2 || activeNeighbourCount == 3;
                        if (!hasTwoOrThreeActiveNeighbours) {
                            result.set(x, y, z, '.');
                        }
                    } else {
                        if (activeNeighbourCount == 3) {
                            result.set(x, y, z, '#');
                        }
                    }
                }
            }
        }

        return result;
    }

    private int countActiveNeighbours(int x, int y, int z) {
        int activeNeighbourCount = 0;
        int zSize = grid.size();
        int xySize = grid.get(0).size();

        for (int zDelta = -1; zDelta <= 1; zDelta++) {
            for (int yDelta = -1; yDelta <= 1; yDelta++) {
                for (int xDelta = -1; xDelta <= 1; xDelta++) {
                    if (zDelta == 0 && yDelta == 0 && xDelta == 0) {
                        continue;
                    }

                    int inspectedZ = z + zDelta;
                    int inspectedY = y + yDelta;
                    int inspectedX = x + xDelta;

                    boolean inspectedZIsOutsideGrid = inspectedZ < 0 || inspectedZ >= zSize;
                    boolean inspectedYIsOutsideGrid = inspectedY < 0 || inspectedY >= xySize;
                    boolean inspectedXIsOutsideGrid = inspectedX < 0 || inspectedX >= xySize;

                    if (inspectedZIsOutsideGrid || inspectedYIsOutsideGrid || inspectedXIsOutsideGrid) {
                        continue;
                    }

                    if (grid.get(inspectedZ).get(inspectedY).get(inspectedX) == '#') {
                        activeNeighbourCount++;
                    }
                }
            }
        }

        return activeNeighbourCount;
    }

    private void set(int x, int y, int z, char c) {
        List<List<Character>> zPlane = grid.get(z);
        List<Character> yRow = zPlane.get(y);
        yRow.set(x, c);
    }

    public int activeCubeCount() {
        int zSize = grid.size();
        int xySize = grid.get(0).size();
        int activeCubeCount = 0;

        for (int z = 0; z < zSize; z++) {
            for (int y = 0; y < xySize; y++) {
                for (int x = 0; x < xySize; x++) {
                    if (grid.get(z).get(y).get(x) == '#') {
                        activeCubeCount++;
                    }
                }
            }
        }

        return activeCubeCount;
    }

    private List<List<List<Character>>> grid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (List<List<Character>> zPlane: grid) {
            for (List<Character> yRow: zPlane) {
                result.append(yRow).append("\n");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
