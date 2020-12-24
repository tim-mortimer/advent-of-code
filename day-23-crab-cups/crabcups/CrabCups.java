package crabcups;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CrabCups {

    public static void main(String[] args) {
        List<Integer> circle = new ArrayList<>(List.of(5, 8, 3, 9, 7, 6, 2, 4, 1));
        int currentCupIndex = 0;
        List<Integer> result = makeMove(circle, currentCupIndex, 100);

        int oneIndex = result.indexOf(1);
        List<Integer> output = new ArrayList<>(result.subList(oneIndex + 1, result.size()));
        output.addAll(result.subList(0, oneIndex));
        System.out.println(output.stream().map(x -> "" + x).collect(Collectors.joining("")));
    }

    public static List<Integer> makeMove(List<Integer> circle, int currentCupIndex, int times) {
        if (times == 0) {
            return new ArrayList<>(circle);
        }

        int currentCupLabel = circle.get(currentCupIndex);

        System.out.println("Move: " + (10 + 1 - times));
        System.out.println("Circle: " + circle);
        System.out.println("Current Cup Index: " + currentCupIndex);
        System.out.println("Current Cup Label: " + currentCupLabel);

        int cup1 = circle.remove((circle.indexOf(currentCupLabel) + 1) % circle.size());
        int cup2 = circle.remove((circle.indexOf(currentCupLabel) + 1) % circle.size());
        int cup3 = circle.remove((circle.indexOf(currentCupLabel) + 1) % circle.size());

        System.out.println("Remove: " + List.of(cup1, cup2, cup3));

        int destinationCupLabel = currentCupLabel - 1;

        int lowestCupLabel = circle.stream().min(Comparator.naturalOrder()).get();
        int highestCupLabel = circle.stream().max(Comparator.naturalOrder()).get();

        while (!circle.contains(destinationCupLabel)) {
            if (destinationCupLabel < lowestCupLabel) {
                destinationCupLabel = highestCupLabel;
            } else {
                destinationCupLabel--;
            }
        }

        System.out.println("Destination: " + destinationCupLabel);
        int destinationCupIndex = circle.indexOf(destinationCupLabel);
        circle.add(destinationCupIndex + 1, cup1);
        circle.add(destinationCupIndex + 2, cup2);
        circle.add(destinationCupIndex + 3, cup3);

        int newCurrentCupIndex = (circle.indexOf(currentCupLabel) + 1) % circle.size();

        return makeMove(new ArrayList<>(circle), newCurrentCupIndex, --times);
    }
}
