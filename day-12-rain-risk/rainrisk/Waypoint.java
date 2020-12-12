package rainrisk;

import java.util.Objects;

@SuppressWarnings("SuspiciousNameCombination")
public class Waypoint {
    private final int xValue;
    private final int yValue;

    public Waypoint(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public Waypoint() {
        this(10, 1);
    }

    public int xValue() {
        return xValue;
    }

    public int yValue() {
        return yValue;
    }

    public Waypoint moveNorth(int distance) {
        return new Waypoint(xValue, yValue + distance);
    }

    public Waypoint moveEast(int distance) {
        return new Waypoint(xValue + distance, yValue);
    }

    public Waypoint moveSouth(int distance) {
        return new Waypoint(xValue, yValue - distance);
    }

    public Waypoint moveWest(int distance) {
        return new Waypoint(xValue - distance, yValue);
    }

    public Waypoint rotateClockwise(int degrees) {
        Waypoint rotatedWaypoint = new Waypoint(yValue, -xValue);

        if (degrees == 90) {
            return rotatedWaypoint;
        }

        return rotatedWaypoint.rotateClockwise(degrees - 90);
    }

    public Waypoint rotateAnticlocwise(int degrees) {
        Waypoint rotatedWaypoint = new Waypoint(-yValue, xValue);

        if (degrees == 90) {
            return rotatedWaypoint;
        }

        return rotatedWaypoint.rotateAnticlocwise(degrees - 90);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waypoint waypoint = (Waypoint) o;
        return xValue == waypoint.xValue && yValue == waypoint.yValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xValue, yValue);
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }
}
