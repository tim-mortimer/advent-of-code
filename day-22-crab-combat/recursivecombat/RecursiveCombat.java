package recursivecombat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecursiveCombat {

    static int gameCount;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-22-crab-combat/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Integer> player1Cards = new ArrayList<>();
        List<Integer> player2Cards = new ArrayList<>();
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

        playGame(player1Cards, player2Cards);
    }

    private static int playGame(List<Integer> player1Cards, List<Integer> player2Cards) {
        System.out.println("=== Start of game:" + ++gameCount + " ===");

        List<List<Integer>> player1Decks = new ArrayList<>();
        List<List<Integer>> player2Decks = new ArrayList<>();

        player1Decks.add(player1Cards);
        player2Decks.add(player2Cards);

        while (!(player1Cards.size() == 0 || player2Cards.size() == 0)) {
            System.out.println("Player 1's deck: " + player1Cards);
            System.out.println("Player 2's deck: " + player2Cards);

            if (currentHandMatchesPreviousHand(player1Decks) ||
                currentHandMatchesPreviousHand(player2Decks)) {

                System.out.println("Automatic win for player 1!");
                System.out.println(calculateScore(player1Cards));

                return 1;
            }

            player1Cards = new ArrayList<>(player1Cards);
            player2Cards = new ArrayList<>(player2Cards);

            int player1Card = player1Cards.remove(0);
            int player2Card = player2Cards.remove(0);

            System.out.println("Player 1 plays: " + player1Card);
            System.out.println("Player 2 plays: " + player2Card);

            if (player1Card <= player1Cards.size() && player2Card <= player2Cards.size()) {
                System.out.println("Playing a sub-game to determine the winner...");

                int winner = playGame(
                        player1Cards.stream().limit(player1Card).collect(Collectors.toList()),
                        player2Cards.stream().limit(player2Card).collect(Collectors.toList())
                );

                System.out.println("Winner of sub-game: " + winner);

                if (winner == 1) {
                    player1Cards.add(player1Card);
                    player1Cards.add(player2Card);
                } else {
                    player2Cards.add(player2Card);
                    player2Cards.add(player1Card);
                }
            } else {
                if (player1Card > player2Card) {
                    player1Cards.add(player1Card);
                    player1Cards.add(player2Card);
                } else {
                    player2Cards.add(player2Card);
                    player2Cards.add(player1Card);
                }
            }

            player1Decks.add(player1Cards);
            player2Decks.add(player2Cards);
        }

        if (player1Cards.size() > 0) {
            System.out.println("Player 1 has full deck! Score:");
            System.out.println(calculateScore(player1Cards));
            return 1;
        } else {
            System.out.println("Player 2 has full deck! Score:");
            System.out.println(calculateScore(player2Cards));
            return 2;
        }
    }

    private static boolean currentHandMatchesPreviousHand(List<List<Integer>> hands) {
        return hands.stream()
            .filter(hand -> hands.indexOf(hand) != hands.size() - 1)
            .anyMatch(hand -> {
                List<Integer> latestHand = hands.get(hands.size() - 1);
                return hand.equals(latestHand);
            });
    }

    private static long calculateScore(List<Integer> cards) {
        long score = 0;

        for (int i = 0; i < cards.size(); i++) {
            score += (long) cards.get(i) * (cards.size() - i);
        }

        return score;
    }
}
