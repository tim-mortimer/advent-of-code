package toboggantrajectory;

import java.io.*;
import java.util.ArrayList;

public class Locations {
    private final ArrayList<ArrayList<Character>> locations;

    public Locations(ArrayList<ArrayList<Character>> locations) {
        this.locations = locations;
    }

    public static Locations fromFilePath(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        ArrayList<ArrayList<Character>> locationsArray = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            ArrayList<Character> horizontalLocations = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {
                horizontalLocations.add(line.charAt(i));
            }

            locationsArray.add(horizontalLocations);
        }

        return new Locations(locationsArray);
    }

    public void print() {
        for (ArrayList<Character> row: locations) {
           for (char c: row) {
               System.out.print(c);
           }

            System.out.print("\n");
        }
    }

    public int countTreesAlongStandardPath() {
        int treeCount = 0;

        for (int verticalPosition = 1; verticalPosition < locations.size(); verticalPosition++) {
            ArrayList<Character> row = locations.get(verticalPosition);
            int horizontalPosition = (3 * verticalPosition) % row.size();
            if (row.get(horizontalPosition) == '#') {
                treeCount++;
            }
        }

        return treeCount;
    }
}
