package jurassicjigsaw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Piece {
    private final int id;
    private final List<List<Character>> pixels;

    public Piece(int id, List<List<Character>> pixels) {
        this.id = id;
        this.pixels = pixels;
    }

    public int matchingEdgeCount(List<Piece> pieces) {
        int count = 0;

        List<List<Character>> edges = List.of(
                row(0),
                row(size() - 1),
                column(0),
                column(size() - 1)
        );

        for (List<Character> edge: edges) {
            boolean hasMatchingEdge = false;

            for (Piece piece: pieces) {
                if (piece.id() == id) {
                    continue;
                }

                if (piece.edgePermutations().contains(edge)) {
                    hasMatchingEdge = true;
                    break;
                }
            }

            if (hasMatchingEdge) {
                count++;
            }
        }

        return count;
    }

    public List<List<Character>> edgePermutations() {
        List<List<Character>> edgePermutations = new ArrayList<>();

        edgePermutations.add(row(0));
        edgePermutations.add(reverse(row(0)));
        edgePermutations.add(row(size() - 1));
        edgePermutations.add(reverse(row(size() - 1)));
        edgePermutations.add(column(0));
        edgePermutations.add(reverse(column(0)));
        edgePermutations.add(column(size() - 1));
        edgePermutations.add(reverse(column(size() - 1)));

        return edgePermutations;
    }

    public int id() {
        return id;
    }
    public int size() {
        return pixels.size();
    }

    public List<Character> row(int rowNumber) {
        return pixels.get(rowNumber);
    }

    public List<Character> column(int columnNumber) {
        return pixels.stream()
                .map(row -> row.get(columnNumber))
                .collect(Collectors.toList());
    }

    public List<Character> reverse(List<Character> listToReverse) {
        List<Character> result = new ArrayList<>();

        for (int i = listToReverse.size() - 1; i >= 0; i--) {
            result.add(listToReverse.get(i));
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(id).append("\n");

        for (List<Character> horizontalLine: pixels) {
            builder.append(horizontalLine).append("\n");
        }

        return builder.toString();
    }
}
