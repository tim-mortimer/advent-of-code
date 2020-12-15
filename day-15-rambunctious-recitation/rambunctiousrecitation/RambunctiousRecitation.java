package rambunctiousrecitation;

public class RambunctiousRecitation {

    public static void main(String[] args) {
        RecitationGame game = new RecitationGame(new int[] {0, 13, 1, 8, 6, 15});
        System.out.println(game.playUntil(2020));
    }
}
