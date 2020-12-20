package jurassicjigsaw;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JurassicJigsaw {

    public static void main(String[] args) throws IOException {
        List<Piece> pieces = readPieces();
        long result = 1;

        for (Piece piece: pieces) {
            if (piece.matchingEdgeCount(pieces) == 2) {
                result *= piece.id();
            }
        }

        System.out.println(result);
    }

    private static List<Piece> readPieces() throws IOException {
        InputStream inputStream = new FileInputStream("day-20-jurassic-jigsaw/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Piece> pieces = new ArrayList<>();
        int id = 0;
        List<List<Character>> pixels = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            Pattern tileIdPattern = Pattern.compile("Tile (\\d+):");
            Matcher tileIdMatcher = tileIdPattern.matcher(line);

            if (tileIdMatcher.matches()) {
                id = Integer.parseInt(tileIdMatcher.group(1));
                continue;
            }

            if (line.equals("")) {
                pieces.add(new Piece(id, pixels));
                pixels = new ArrayList<>();
                continue;
            }

            List<Character> horizontalLine = new ArrayList<>();

            for (char c : line.toCharArray()) {
                horizontalLine.add(c);
            }

            pixels.add(horizontalLine);
        }

        return pieces;
    }
}
