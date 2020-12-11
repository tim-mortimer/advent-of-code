package seatingsystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeatingSystemTest {

    @Test
    public void applying_the_rules_to_a_one_by_one_seating_area_containing_a_vacant_seat() {
        SeatingArea seatingArea = new SeatingArea(List.of(List.of('L')));
        SeatingArea ruleResult = seatingArea.applyRules();
        SeatingArea expectedResult = new SeatingArea(List.of(List.of('#')));
        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_one_by_one_seating_area_containing_an_occupied_seat() {
        SeatingArea seatingArea = new SeatingArea(List.of(List.of('#')));
        SeatingArea ruleResult = seatingArea.applyRules();
        SeatingArea expectedResult = new SeatingArea(List.of(List.of('#')));
        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_one_by_one_seating_area_containing_floor_space() {
        SeatingArea seatingArea = new SeatingArea(List.of(List.of('.')));
        SeatingArea ruleResult = seatingArea.applyRules();
        SeatingArea expectedResult = new SeatingArea(List.of(List.of('.')));
        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_two_by_one_seating_area_containing_vacant_seats() {
        SeatingArea seatingArea = new SeatingArea(List.of(List.of('L', 'L')));
        SeatingArea ruleResult = seatingArea.applyRules();
        SeatingArea expectedResult = new SeatingArea(List.of(List.of('#', '#')));
        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_two_by_one_seating_area_containing_an_occupied_seat() {
        SeatingArea seatingArea = new SeatingArea(List.of(List.of('#', 'L')));
        SeatingArea ruleResult = seatingArea.applyRules();
        SeatingArea expectedResult = new SeatingArea(List.of(List.of('#', 'L')));
        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_two_by_two_seating_area_containing_vacant_seats() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('L', 'L'),
                List.of('L', 'L')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', '#'),
                List.of('#', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_two_by_two_seating_area_containing_an_occupied_seat() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', 'L'),
                List.of('L', 'L')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L'),
                List.of('L', 'L')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_vacant_seats() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('L', 'L', 'L'),
                List.of('L', 'L', 'L')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', '#', '#'),
                List.of('#', '#', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_an_upper_centre_occupied_seat() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('L', '#', 'L'),
                List.of('L', 'L', 'L')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('L', '#', 'L'),
                List.of('L', 'L', 'L')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_occupied_seats_left_and_right() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('#', 'L', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('#', 'L', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_occupied_seats_to_the_left() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', 'L', 'L'),
                List.of('#', 'L', 'L')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('#', 'L', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_an_occupied_seat_with_4_adjacent_occupied_seats() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', '#', '#'),
                List.of('#', 'L', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('#', 'L', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void applying_the_rules_to_a_three_by_two_seating_area_containing_two_occupied_seats_each_with_5_adjacent_occupied_seats() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', '#', '#'),
                List.of('#', '#', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('#', 'L', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void a_vacant_seat_with_adjacent_vacant_seats_and_floor_space_becomes_occupied() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('L', 'L', '#'),
                List.of('L', '.', '#'),
                List.of('#', '#', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules();

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', 'L', '#'),
                List.of('L', '.', '#'),
                List.of('#', '#', '#')
        ));
    }

    @Test
    public void a_vacant_seat_with_no_visible_occupied_seats_becomes_occupied() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', '.', '#', '.'),
                List.of('.', '.', '.', '#'),
                List.of('.', 'L', '.', '.'),
                List.of('.', '.', '.', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules(new VisibleOccupiedSeatCountingStrategy());

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', '.', '#', '.'),
                List.of('.', '.', '.', '#'),
                List.of('.', '#', '.', '.'),
                List.of('.', '.', '.', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void a_vacant_seat_with_a_visible_occupied_seat_remains_unoccupied() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('#', '#', '#', '.'),
                List.of('.', '.', '.', '#'),
                List.of('.', 'L', '.', '.'),
                List.of('.', '.', '.', '#')
        ));

        SeatingArea ruleResult = seatingArea.applyRules(new VisibleOccupiedSeatCountingStrategy());

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('#', '#', '#', '.'),
                List.of('.', '.', '.', '#'),
                List.of('.', 'L', '.', '.'),
                List.of('.', '.', '.', '#')
        ));

        assertEquals(expectedResult, ruleResult);
    }

    @Test
    public void an_occupied_seat_surrounded_by_four_visible_occupied_seats_remains_occupied() {
        SeatingArea seatingArea = new SeatingArea(List.of(
                List.of('.', '#', '.', '#'),
                List.of('#', '.', '.', '.'),
                List.of('.', '#', '.', '.'),
                List.of('.', '#', '.', '.')
        ));

        SeatingArea ruleResult = seatingArea.applyRules(new VisibleOccupiedSeatCountingStrategy());

        SeatingArea expectedResult = new SeatingArea(List.of(
                List.of('.', '#', '.', '#'),
                List.of('#', '.', '.', '.'),
                List.of('.', '#', '.', '.'),
                List.of('.', '#', '.', '.')
        ));

        assertEquals(expectedResult, ruleResult);
    }
}
