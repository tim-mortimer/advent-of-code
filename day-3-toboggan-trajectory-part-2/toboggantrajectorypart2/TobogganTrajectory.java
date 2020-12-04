package toboggantrajectorypart2;

import java.io.IOException;
import java.util.List;

public class TobogganTrajectory {

    public static void main(String[] args) throws IOException {
        Locations locations = Locations.fromFilePath("day-3-toboggan-trajectory/input.txt");
        locations.print();

        long treeCount = List.of(
                List.of(1, 1),
                List.of(3, 1),
                List.of(5, 1),
                List.of(7, 1),
                List.of(1, 2)
            )
                .stream()
                .map(path -> (long) locations.countTreesAlongPath(path.get(0), path.get(1)))
                .reduce(1L, (Long i, Long j) -> i * j);

        System.out.println(treeCount);
    }
}
