package rambunctiousrecitation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecitationGameTest {

    @Test
    public void example_1() {
        RecitationGame game = new RecitationGame(new int[] {0, 3, 6});
        assertEquals(0, game.playUntil(1));
        assertEquals(3, game.playUntil(2));
        assertEquals(6, game.playUntil(3));
        assertEquals(0, game.playUntil(4));
        assertEquals(3, game.playUntil(5));
        assertEquals(3, game.playUntil(6));
        assertEquals(1, game.playUntil(7));
        assertEquals(0, game.playUntil(8));
        assertEquals(4, game.playUntil(9));
        assertEquals(0, game.playUntil(10));
        assertEquals(436, game.playUntil(2020));
    }

    @Test
    public void example_2() {
        RecitationGame game = new RecitationGame(new int[] {1, 3, 2});
        assertEquals(1, game.playUntil(2020));
    }

    @Test
    public void example_3() {
        RecitationGame game = new RecitationGame(new int[] {2, 1, 3});
        assertEquals(10, game.playUntil(2020));
    }

    @Test
    public void example_4() {
        RecitationGame game = new RecitationGame(new int[] {1, 2, 3});
        assertEquals(27, game.playUntil(2020));
    }

    @Test
    public void example_5() {
        RecitationGame game = new RecitationGame(new int[] {2, 3, 1});
        assertEquals(78, game.playUntil(2020));
    }

    @Test
    public void example_6() {
        RecitationGame game = new RecitationGame(new int[] {3, 2, 1});
        assertEquals(438, game.playUntil(2020));
    }

    @Test
    public void example_7() {
        RecitationGame game = new RecitationGame(new int[] {3, 1, 2});
        assertEquals(1836, game.playUntil(2020));
    }

    @Test
    public void example_8() {
        RecitationGame game = new RecitationGame(new int[] {3, 1, 2, 0});
        assertEquals(1, game.playUntil(6));
    }

    @Test
    public void example_9() {
        RecitationGame game = new RecitationGame(new int[] {0, 3, 6});
        assertEquals(175594, game.playUntil(30_000_000));
    }

    @Test
    public void example_10() {
        RecitationGame game = new RecitationGame(new int[] {1, 3, 2});
        assertEquals(2578, game.playUntil(30_000_000));
    }

    @Test
    public void example_11() {
        RecitationGame game = new RecitationGame(new int[] {2, 1, 3});
        assertEquals(3544142, game.playUntil(30_000_000));
    }

    @Test
    public void example_12() {
        RecitationGame game = new RecitationGame(new int[] {1, 2, 3});
        assertEquals(261214, game.playUntil(30_000_000));
    }

    @Test
    public void example_13() {
        RecitationGame game = new RecitationGame(new int[] {2, 3, 1});
        assertEquals(6895259, game.playUntil(30_000_000));
    }

    @Test
    public void example_14() {
        RecitationGame game = new RecitationGame(new int[] {3, 2, 1});
        assertEquals(18, game.playUntil(30_000_000));
    }

    @Test
    public void example_15() {
        RecitationGame game = new RecitationGame(new int[] {3, 1, 2});
        assertEquals(362, game.playUntil(30_000_000));
    }
}
