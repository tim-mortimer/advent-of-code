package rainrisk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RainRisk {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-12-rain-risk/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Map<Character, Integer>> navigationInstructions = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            char action = line.toCharArray()[0];
            int value = Integer.parseInt(line.substring(1));
            Map<Character, Integer> navigationInstruction = Map.of(action, value);
            navigationInstructions.add(navigationInstruction);
        }

        Ship ship = new Ship();

        for (Map<Character, Integer> instruction: navigationInstructions) {
            if (instruction.containsKey('N')) {
                ship = ship.moveNorth(instruction.get('N'));
            } else if (instruction.containsKey('S')) {
                ship = ship.moveSouth(instruction.get('S'));
            } else if (instruction.containsKey('E')) {
                ship = ship.moveEast(instruction.get('E'));
            } else if (instruction.containsKey('W')) {
                ship = ship.moveWest(instruction.get('W'));
            } else if (instruction.containsKey('L')) {
                ship = ship.turnLeft(instruction.get('L'));
            } else if (instruction.containsKey('R')) {
                ship = ship.turnRight(instruction.get('R'));
            } else if (instruction.containsKey('F')){
                ship = ship.moveForward(instruction.get('F'));
            }
        }

        System.out.println(ship.manhattanDistance());

        ship = new Ship();
        Waypoint waypoint = new Waypoint();

        for (Map<Character, Integer> instruction: navigationInstructions) {
            if (instruction.containsKey('N')) {
                waypoint = waypoint.moveNorth(instruction.get('N'));
            } else if (instruction.containsKey('S')) {
                waypoint = waypoint.moveSouth(instruction.get('S'));
            } else if (instruction.containsKey('E')) {
                waypoint = waypoint.moveEast(instruction.get('E'));
            } else if (instruction.containsKey('W')) {
                waypoint = waypoint.moveWest(instruction.get('W'));
            } else if (instruction.containsKey('L')) {
                waypoint = waypoint.rotateAnticlocwise(instruction.get('L'));
            } else if (instruction.containsKey('R')) {
                waypoint = waypoint.rotateClockwise(instruction.get('R'));
            } else if (instruction.containsKey('F')){
                ship = ship.followWaypoint(waypoint, instruction.get('F'));
            }
        }

        System.out.println(ship.manhattanDistance());
    }
}
