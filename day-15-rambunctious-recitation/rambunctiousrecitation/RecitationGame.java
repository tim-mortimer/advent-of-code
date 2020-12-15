package rambunctiousrecitation;

import java.util.ArrayList;

public class RecitationGame {
    private final int[] startingNumbers;

    public RecitationGame(int[] startingNumbers) {
        this.startingNumbers = startingNumbers;
    }

    public int playUntil(int round) {
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            if (i < startingNumbers.length) {
                results.add(startingNumbers[i]);
            } else if (results.indexOf(results.get(i - 1)) == i - 1) {
                results.add(0);
            } else {
                for (int j = 2; j <= results.size(); j++) {
                    if (results.get(results.size() - j).equals(results.get(i - 1))) {
                        results.add(j - 1);
                        break;
                    }
                }
            }
        }

        return results.get(round - 1);
    }
}
