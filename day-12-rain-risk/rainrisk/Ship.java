package rainrisk;

import java.util.Objects;

public class Ship {
    private final int orientation;
    private final int xPosition;
    private final int yPosition;

    public Ship(int orientation, int xPosition, int yPosition) {
        this.orientation = orientation;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Ship() {
        this(0, 0 ,0);
    }

    public int orientation() {
        return 0;
    }

    public int xPosition() {
        return xPosition;
    }

    public int yPosition() {
        return yPosition;
    }

    public Ship moveNorth(int distance) {
        return new Ship(orientation, xPosition, yPosition + distance);
    }

    public Ship moveEast(int distance) {
        return new Ship(orientation, xPosition + distance, yPosition);
    }

    public Ship moveSouth(int distance) {
        return new Ship(orientation, xPosition, yPosition - distance);
    }

    public Ship moveWest(int distance) {
        return new Ship(orientation, xPosition - distance, yPosition);
    }

    public Ship turnLeft(int degrees) {
        return new Ship((orientation + degrees) % 360, xPosition, yPosition);
    }

    public Ship turnRight(int degrees) {
        return new Ship((orientation - degrees + 360) % 360, xPosition, yPosition);
    }

    public Ship moveForward(int distance) {
        if (orientation == 0) {
            return moveEast(distance);
        } else if (orientation == 90) {
            return moveNorth(distance);
        } else if (orientation == 180) {
            return moveWest(distance);
        } else {
            return moveSouth(distance);
        }
    }

    public int manhattanDistance() {
        return Math.abs(xPosition) + Math.abs(yPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return orientation == ship.orientation && xPosition == ship.xPosition && yPosition == ship.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, xPosition, yPosition);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "orientation=" + orientation +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }

    public Ship followWaypoint(Waypoint waypoint, int times) {
        int newXPosition = xPosition + times * waypoint.xValue();
        int newYPosition = yPosition + times * waypoint.yValue();
        return new Ship(orientation, newXPosition, newYPosition);
    }
}
