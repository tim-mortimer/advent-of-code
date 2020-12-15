package rambunctiousrecitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecitationGame {
    private final int[] startingNumbers;

    public RecitationGame(int[] startingNumbers) {
        this.startingNumbers = startingNumbers;
    }

    public int playUntil(int round) {
        ArrayList<Integer> results = new ArrayList<>();
        Map<Integer, List<Integer>> locationCache = new HashMap<>();
        int result = -1;

        for (int i = 0; i < round; i++) {
            boolean lastResultWasFirstInstanceOfLastResult = false;

            if (locationCache.containsKey(result)) {
                lastResultWasFirstInstanceOfLastResult = locationCache.get(result).get(0).equals(locationCache.get(result).get(1));
            }

            if (i < startingNumbers.length) {
                result = startingNumbers[i];
                locationCache.put(result, List.of(i, i));
            } else if (lastResultWasFirstInstanceOfLastResult) {
                int lastIndexOfZero = i;

                if (locationCache.containsKey(0)) {
                    lastIndexOfZero = locationCache.get(0).get(1);
                }

                result = 0;
                locationCache.put(0, List.of(lastIndexOfZero, i));
            } else {
                int locationOfPreviousResult = locationCache.get(result).get(0);
                int difference = i - 1 - locationOfPreviousResult;

                int previousLocationOfDifference = i;
                if (locationCache.get(difference) != null) {
                    previousLocationOfDifference = locationCache.get(difference).get(1);
                }

                result = difference;
                locationCache.put(difference, List.of(previousLocationOfDifference, i));
            }
        }


        return result;
    }
}
