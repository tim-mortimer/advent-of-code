package conwaycubes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConwayCubes {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-17-conway-cubes/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<List<Character>> input = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            List<Character> row = new ArrayList<>();

            for (char c: line.toCharArray()) {
                row.add(c);
            }

            input.add(row);
        }

        List<List<List<Character>>> grid = new ArrayList<>();
        grid.add(input);

        PocketDimension pocketDimension = new PocketDimension(grid);

        for (int i = 0; i < 6; i++) {
            pocketDimension = pocketDimension.performCycle();
            System.out.println(pocketDimension);
            System.out.println(pocketDimension.activeCubeCount());
        }
    }
}
