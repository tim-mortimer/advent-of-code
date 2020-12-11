package seatingsystem;

import java.util.List;

interface OccupiedSeatCountingStrategy {

    int count(List<List<Character>> seatingGrid, int rowNumber, int columnNumber);

    int peopleTolerance();
}
