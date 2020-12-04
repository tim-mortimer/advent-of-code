package toboggantrajectorypart2;

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

    public int countTreesAlongPath(int xJump, int yJump) {
        int treeCount = 0;

        for (int yPosition = yJump; yPosition < locations.size(); yPosition += yJump) {
            ArrayList<Character> row = locations.get(yPosition);
            int numberOfJumps = yPosition / yJump;
            int xPosition = (xJump * numberOfJumps) % row.size();
            if (row.get(xPosition) == '#') {
                treeCount++;
            }
        }

        return treeCount;
    }
}
