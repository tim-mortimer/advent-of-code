package rainrisk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {

    @Test
    public void the_ship_starts_by_facing_east() {
        Ship ship = new Ship();
        int orientation = ship.orientation();
        assertEquals(0, orientation);
    }

    @Test
    public void the_ship_starts_at_the_origin() {
        Ship ship = new Ship();
        int xPosition = ship.xPosition();
        int yPosition = ship.yPosition();
        assertEquals(0, xPosition);
        assertEquals(0, yPosition);
    }

    @Test
    public void moving_north_zero_distance() {
        Ship ship = new Ship();
        Ship movedShip = ship.moveNorth(0);
        assertEquals(ship, movedShip);
    }

    @Test
    public void moving_north() {
        Ship ship = new Ship(90, 0, 1);
        Ship movedShip = ship.moveNorth(5);
        Ship expectedShip = new Ship(90, 0, 6);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_north_preserves_orientation() {
        Ship ship = new Ship(180, 0, 0);
        Ship movedShip = ship.moveNorth(5);
        Ship expectedShip = new Ship(180, 0, 5);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_east_zero_distance() {
        Ship ship = new Ship();
        Ship movedShip = ship.moveEast(0);
        assertEquals(ship, movedShip);
    }

    @Test
    public void moving_east() {
        Ship ship = new Ship(0, 1, 0);
        Ship movedShip = ship.moveEast(5);
        Ship expectedShip = new Ship(0, 6, 0);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_east_preserves_orientation() {
        Ship ship = new Ship(270, 0, 0);
        Ship movedShip = ship.moveEast(5);
        Ship expectedShip = new Ship(270, 5, 0);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_south_zero_distance() {
        Ship ship = new Ship();
        Ship movedShip = ship.moveSouth(0);
        assertEquals(ship, movedShip);
    }

    @Test
    public void moving_south() {
        Ship ship = new Ship(0, 0, 1);
        Ship movedShip = ship.moveSouth(5);
        Ship expectedShip = new Ship(0, 0, -4);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_south_preserves_orientation() {
        Ship ship = new Ship(90, 0, 0);
        Ship movedShip = ship.moveSouth(5);
        Ship expectedShip = new Ship(90, 0, -5);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_west_zero_distance() {
        Ship ship = new Ship();
        Ship movedShip = ship.moveWest(0);
        assertEquals(ship, movedShip);
    }

    @Test
    public void moving_west() {
        Ship ship = new Ship(0, 1, 0);
        Ship movedShip = ship.moveWest(5);
        Ship expectedShip = new Ship(0, -4, 0);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_west_preserves_orientation() {
        Ship ship = new Ship(90, 0, 0);
        Ship movedShip = ship.moveWest(5);
        Ship expectedShip = new Ship(90, -5, 0);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void turning_left_within_360_degrees() {
        Ship ship = new Ship(0, 1, 1);
        Ship turnedShip = ship.turnLeft(180);
        Ship expectedShip = new Ship(180, 1, 1);
        assertEquals(expectedShip, turnedShip);
    }

    @Test
    public void turning_left_over_360_degrees() {
        Ship ship = new Ship(270, 1, 1);
        Ship turnedShip = ship.turnLeft(180);
        Ship expectedShip = new Ship(90, 1, 1);
        assertEquals(expectedShip, turnedShip);
    }

    @Test
    public void turning_right_within_360_degrees() {
        Ship ship = new Ship(270, 1, 1);
        Ship turnedShip = ship.turnRight(180);
        Ship expectedShip = new Ship(90, 1, 1);
        assertEquals(expectedShip, turnedShip);
    }

    @Test
    public void turning_right_over_360_degrees() {
        Ship ship = new Ship(90, 1, 1);
        Ship turnedShip = ship.turnRight(180);
        Ship expectedShip = new Ship(270, 1, 1);
        assertEquals(expectedShip, turnedShip);
    }

    @Test
    public void moving_forward_facing_east() {
        Ship ship = new Ship(0, 1, 1);
        Ship movedShip = ship.moveForward(5);
        Ship expectedShip = new Ship(0, 6, 1);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_forward_facing_north() {
        Ship ship = new Ship(90, 1, 1);
        Ship movedShip = ship.moveForward(5);
        Ship expectedShip = new Ship(90, 1, 6);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_forward_facing_west() {
        Ship ship = new Ship(180, 1, 1);
        Ship movedShip = ship.moveForward(5);
        Ship expectedShip = new Ship(180, -4, 1);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void moving_forward_facing_south() {
        Ship ship = new Ship(270, 1, 1);
        Ship movedShip = ship.moveForward(5);
        Ship expectedShip = new Ship(270, 1, -4);
        assertEquals(expectedShip, movedShip);
    }

    @Test
    public void calculating_the_manhattan_distance() {
        Ship ship = new Ship(270, 40, -50);
        int manhattanDistance = ship.manhattanDistance();
        assertEquals(40 + 50, manhattanDistance);
    }
}
