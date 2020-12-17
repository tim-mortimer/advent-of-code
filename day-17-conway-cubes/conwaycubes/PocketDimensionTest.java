package conwaycubes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PocketDimensionTest {

    @Test
    public void creating_a_pocket_dimension_from_an_input_slice() {
        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> input = new ArrayList<>();
        input.add(List.of('#', '.', '.'));
        input.add(List.of('.', '.', '#'));
        input.add(List.of('#', '#', '#'));
        grid.add(input);

        PocketDimension pocketDimension = new PocketDimension(grid);

        assertEquals('#', pocketDimension.get(0, 0));
        assertEquals('.', pocketDimension.get(1, 0));
        assertEquals('.', pocketDimension.get(2, 0));
        assertEquals('.', pocketDimension.get(0, 1));
        assertEquals('.', pocketDimension.get(1, 1));
        assertEquals('#', pocketDimension.get(2, 1));
        assertEquals('#', pocketDimension.get(0, 2));
        assertEquals('#', pocketDimension.get(1, 2));
        assertEquals('#', pocketDimension.get(2, 2));
    }

    @Test
    public void expanding_a_pocket_dimension() {
        List<List<List<Character>>> grid = new ArrayList<>();
        List<List<Character>> zPlane = new ArrayList<>();
        zPlane.add(List.of('#', '.'));
        zPlane.add(List.of('.', '.'));
        grid.add(zPlane);

        PocketDimension pocketDimension = new PocketDimension(grid);
        PocketDimension expandedPocketDimension = pocketDimension.expand();

        System.out.println(expandedPocketDimension);

        assertEquals('.', expandedPocketDimension.get(0, 0, 0));
        assertEquals('.', expandedPocketDimension.get(1, 0, 0));
        assertEquals('.', expandedPocketDimension.get(2, 0, 0));
        assertEquals('.', expandedPocketDimension.get(3, 0, 0));

        assertEquals('.', expandedPocketDimension.get(0, 1, 0));
        assertEquals('#', expandedPocketDimension.get(1, 1, 0));
        assertEquals('.', expandedPocketDimension.get(2, 1, 0));
        assertEquals('.', expandedPocketDimension.get(3, 1, 0));

        assertEquals('.', expandedPocketDimension.get(0, 2, 0));
        assertEquals('.', expandedPocketDimension.get(1, 2, 0));
        assertEquals('.', expandedPocketDimension.get(2, 2, 0));
        assertEquals('.', expandedPocketDimension.get(3, 2, 0));

        assertEquals('.', expandedPocketDimension.get(0, 0, -1));
        assertEquals('.', expandedPocketDimension.get(1, 0, -1));
        assertEquals('.', expandedPocketDimension.get(2, 0, -1));
        assertEquals('.', expandedPocketDimension.get(3, 0, -1));

        assertEquals('.', expandedPocketDimension.get(0, 1, -1));
        assertEquals('.', expandedPocketDimension.get(1, 1, -1));
        assertEquals('.', expandedPocketDimension.get(2, 1, -1));
        assertEquals('.', expandedPocketDimension.get(3, 1, -1));

        assertEquals('.', expandedPocketDimension.get(0, 2, -1));
        assertEquals('.', expandedPocketDimension.get(1, 2, -1));
        assertEquals('.', expandedPocketDimension.get(2, 2, -1));
        assertEquals('.', expandedPocketDimension.get(3, 2, -1));

        assertEquals('.', expandedPocketDimension.get(0, 0, 1));
        assertEquals('.', expandedPocketDimension.get(1, 0, 1));
        assertEquals('.', expandedPocketDimension.get(2, 0, 1));
        assertEquals('.', expandedPocketDimension.get(3, 0, 1));

        assertEquals('.', expandedPocketDimension.get(0, 1, 1));
        assertEquals('.', expandedPocketDimension.get(1, 1, 1));
        assertEquals('.', expandedPocketDimension.get(2, 1, 1));
        assertEquals('.', expandedPocketDimension.get(3, 1, 1));

        assertEquals('.', expandedPocketDimension.get(0, 2, 1));
        assertEquals('.', expandedPocketDimension.get(1, 2, 1));
        assertEquals('.', expandedPocketDimension.get(2, 2, 1));
        assertEquals('.', expandedPocketDimension.get(3, 2, 1));
    }
}
