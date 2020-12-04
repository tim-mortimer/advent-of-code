package toboggantrajectory;

import java.io.IOException;

public class TobogganTrajectory {

    public static void main(String[] args) throws IOException {
        Locations locations = Locations.fromFilePath("day-3-toboggan-trajectory/input.txt");
        locations.print();

        int treeCount = locations.countTreesAlongStandardPath();
        System.out.println(treeCount);
    }
}
