package crabcombat;

import java.io.*;
import java.util.ArrayList;

public class CrabCombat {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-22-crab-combat/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        ArrayList<Integer> player1Cards = new ArrayList<>();
        ArrayList<Integer> player2Cards = new ArrayList<>();
        int currentPlayer = 1;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("Player 1:") || line.equals("Player 2:")) {
                continue;
            }

            if (line.equals("")) {
                currentPlayer = 2;
                continue;
            }

            if (currentPlayer == 1) {
                player1Cards.add(Integer.parseInt(line));
            } else {
                player2Cards.add(Integer.parseInt(line));
            }
        }

        while (!(player1Cards.size() == 0 || player2Cards.size() == 0)) {
            int player1Card = player1Cards.remove(0);
            int player2Card = player2Cards.remove(0);

            if (player1Card > player2Card) {
                player1Cards.add(player1Card);
                player1Cards.add(player2Card);
            } else {
                player2Cards.add(player2Card);
                player2Cards.add(player1Card);
            }
        }

        long score = 0;

        if (player1Cards.size() > 0) {
            score = calculateScore(player1Cards);
        } else {
            score = calculateScore(player2Cards);
        }

        System.out.println(score);
    }

    private static long calculateScore(ArrayList<Integer> cards) {
        long score = 0;

        for (int i = 0; i < cards.size(); i++) {
            score += (long) cards.get(i) * (cards.size() - i);
        }

        return score;
    }
}
