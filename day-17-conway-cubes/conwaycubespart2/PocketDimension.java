package conwaycubespart2;

import java.util.ArrayList;
import java.util.List;

public class PocketDimension {
    private final List<List<List<List<Character>>>> grid;

    public PocketDimension(List<List<List<List<Character>>>> grid) {
        this.grid = grid;
    }

    public PocketDimension(PocketDimension expandedPocketDimension) {
        int wSize = expandedPocketDimension.wSize();
        int zSize = expandedPocketDimension.zSize();
        int xySize = expandedPocketDimension.xySize();

        List<List<List<List<Character>>>> newGrid = new ArrayList<>();

        for (int w = 0; w < wSize; w++) {
            List<List<List<Character>>> newWPlane = new ArrayList<>();

            for (int z = 0; z < zSize; z++) {
                List<List<Character>> newZPlane = new ArrayList<>();

                for (int y = 0; y < xySize; y++) {
                    List<Character> newY = new ArrayList<>();

                    for (int x = 0; x < xySize; x++) {
                        newY.add(expandedPocketDimension.get(x, y, z, w));
                    }

                    newZPlane.add(newY);
                }

                newWPlane.add(newZPlane);
            }

            newGrid.add(newWPlane);
        }

        grid = newGrid;
    }

    public PocketDimension performCycle() {
        PocketDimension expandedPocketDimension = this.expand();
        PocketDimension result = new PocketDimension(expandedPocketDimension);

        for (int w = 0; w < expandedPocketDimension.wSize(); w++) {
            for (int z = 0; z < expandedPocketDimension.zSize(); z++) {
                for (int y = 0; y < expandedPocketDimension.xySize(); y++) {
                    for (int x = 0; x < expandedPocketDimension.xySize(); x++) {
                        int activeNeighbourCount = expandedPocketDimension.countActiveNeighbours(x, y, z, w);

                        if (expandedPocketDimension.get(x, y, z, w) == '#') {
                            boolean hasTwoOrThreeActiveNeighbours = activeNeighbourCount == 2 || activeNeighbourCount == 3;
                            if (!hasTwoOrThreeActiveNeighbours) {
                                result.set(x, y, z, w, '.');
                            }
                        } else {
                            if (activeNeighbourCount == 3) {
                                result.set(x, y, z, w, '#');
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public PocketDimension expand() {
        List<List<List<List<Character>>>> newGrid = new ArrayList<>();

        newGrid.add(createInactiveWPlane(zSize() + 2, xySize() + 2));

        for (List<List<List<Character>>> wPlane: grid) {
            newGrid.add(expandWPlane(wPlane));
        }

        newGrid.add(createInactiveWPlane(zSize() + 2, xySize() + 2));

        return new PocketDimension(newGrid);
    }

    public List<List<List<Character>>> expandWPlane(List<List<List<Character>>> wPlane) {
        List<List<List<Character>>> newWPlane = new ArrayList<>();

        newWPlane.add(createInactiveZPlane(wPlane.get(0).size() + 2));

        for (List<List<Character>> zPlane : wPlane) {
            newWPlane.add(expandZPlane(zPlane));
        }

        newWPlane.add(createInactiveZPlane(wPlane.get(0).size() + 2));

        return newWPlane;
    }

    private List<List<Character>> expandZPlane(List<List<Character>> zPlane) {
        List<List<Character>> newZPlane = new ArrayList<>();

        newZPlane.add(createInactiveList(zPlane.get(0).size() + 2));

        for (List<Character> y: zPlane) {
            List<Character> newY = new ArrayList<>();
            newY.add('.');
            newY.addAll(y);
            newY.add('.');
            newZPlane.add(newY);
        }

        newZPlane.add(createInactiveList(zPlane.get(0).size() + 2));

        return newZPlane;
    }

    private List<List<List<Character>>> createInactiveWPlane(int depth, int width) {
        List<List<List<Character>>> inactiveWPlane = new ArrayList<>();

        for (int i = 0; i < depth; i++) {
            inactiveWPlane.add(createInactiveZPlane(width));
        }

        return inactiveWPlane;
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

    private int countActiveNeighbours(int x, int y, int z, int w) {
        int activeNeighbourCount = 0;

        for (int wDelta = -1; wDelta <= 1; wDelta++) {
            for (int zDelta = -1; zDelta <= 1; zDelta++) {
                for (int yDelta = -1; yDelta <= 1; yDelta++) {
                    for (int xDelta = -1; xDelta <= 1; xDelta++) {
                        if (wDelta == 0 && zDelta == 0 && yDelta == 0 && xDelta == 0) {
                            continue;
                        }

                        int inspectedW = w + wDelta;
                        int inspectedZ = z + zDelta;
                        int inspectedY = y + yDelta;
                        int inspectedX = x + xDelta;

                        boolean inspectedWIsOutsideGrid = inspectedW < 0 || inspectedW >= wSize();
                        boolean inspectedZIsOutsideGrid = inspectedZ < 0 || inspectedZ >= zSize();
                        boolean inspectedYIsOutsideGrid = inspectedY < 0 || inspectedY >= xySize();
                        boolean inspectedXIsOutsideGrid = inspectedX < 0 || inspectedX >= xySize();

                        if (inspectedWIsOutsideGrid || inspectedZIsOutsideGrid || inspectedYIsOutsideGrid || inspectedXIsOutsideGrid) {
                            continue;
                        }

                        if (get(inspectedX, inspectedY, inspectedZ, inspectedW) == '#') {
                            activeNeighbourCount++;
                        }
                    }
                }
            }
        }

        return activeNeighbourCount;
    }

    private void set(int x, int y, int z, int w, char c) {
        List<List<List<Character>>> wPlane = grid.get(w);
        List<List<Character>> zPlane = wPlane.get(z);
        List<Character> yRow = zPlane.get(y);
        yRow.set(x, c);
    }

    public int activeCubeCount() {
        int activeCubeCount = 0;

        for (int w = 0; w < wSize(); w++) {
            for (int z = 0; z < zSize(); z++) {
                for (int y = 0; y < xySize(); y++) {
                    for (int x = 0; x < xySize(); x++) {
                        if (get(x, y, z, w) == '#') {
                            activeCubeCount++;
                        }
                    }
                }
            }
        }

        return activeCubeCount;
    }


    public char get(int x, int y, int z, int w) {
        return grid.get(w).get(z).get(y).get(x);
    }

    private int wSize() {
        return grid.size();
    }

    private int zSize() {
        return grid.get(0).size();
    }

    private int xySize() {
        return grid.get(0).get(0).size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (List<List<List<Character>>> wPlane: grid) {
            for (List<List<Character>> zPlane: wPlane) {
                for (List<Character> yRow: zPlane) {
                    result.append(yRow).append("\n");
                }

                result.append("\n");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
