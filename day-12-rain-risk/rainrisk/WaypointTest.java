package rainrisk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaypointTest {

    @Test
    public void the_waypoint_starts_at_10_units_east_and_3_units_north() {
        Waypoint waypoint = new Waypoint();
        assertEquals(10, waypoint.xValue());
        assertEquals(1, waypoint.yValue());
    }

    @Test
    public void moving_the_waypoint_north() {
        Waypoint waypoint = new Waypoint();
        assertEquals(new Waypoint(10, 6), waypoint.moveNorth(5));
    }

    @Test
    public void moving_the_waypoint_east() {
        Waypoint waypoint = new Waypoint();
        assertEquals(new Waypoint(15, 1), waypoint.moveEast(5));
    }

    @Test
    public void moving_the_waypoint_south() {
        Waypoint waypoint = new Waypoint();
        assertEquals(new Waypoint(10, -4), waypoint.moveSouth(5));
    }

    @Test
    public void moving_the_waypoint_west() {
        Waypoint waypoint = new Waypoint();
        assertEquals(new Waypoint(5, 1), waypoint.moveWest(5));
    }

    @Test
    public void rotating_a_quadrant_1_waypoint_90_degrees_clockwise() {
        Waypoint waypoint = new Waypoint(6, 10);
        assertEquals(new Waypoint(10, -6), waypoint.rotateClockwise(90));
    }

    @Test
    public void rotating_a_quadrant_2_waypoint_90_degrees_clockwise_around_a_ship() {
        Waypoint waypoint = new Waypoint(-6, 10);
        assertEquals(new Waypoint(10, 6), waypoint.rotateClockwise(90));
    }

    @Test
    public void rotating_a_quadrant_3_waypoint_90_degrees_clockwise() {
        Waypoint waypoint = new Waypoint(-6, -10);
        assertEquals(new Waypoint(-10, 6), waypoint.rotateClockwise(90));
    }

    @Test
    public void rotating_a_quadrant_4_waypoint_90_degrees_clockwise() {
        Waypoint waypoint = new Waypoint(6, -10);
        assertEquals(new Waypoint(-10, -6), waypoint.rotateClockwise(90));
    }

    @Test
    public void rotating_a_waypoint_270_degrees_clockwise() {
        Waypoint waypoint = new Waypoint(6, 10);
        assertEquals(new Waypoint(-10, 6), waypoint.rotateClockwise(270));
    }

    @Test
    public void rotating_a_waypoint_90_degrees_anticlockwise() {
        Waypoint waypoint = new Waypoint(6, 10);
        assertEquals(new Waypoint(-10, 6), waypoint.rotateAnticlocwise(90));
    }

    @Test
    public void rotating_waypoint_270_degrees_anticlockwise() {
        Waypoint waypoint = new Waypoint(6, 10);
        assertEquals(new Waypoint(10, -6), waypoint.rotateAnticlocwise(270));
    }
}
