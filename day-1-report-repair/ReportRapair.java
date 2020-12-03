import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRapair {

    public static void main(String[] args) throws IOException {
        Path path = new File("day-1-report-repair/input.txt").toPath();
        List<Integer> integerList = Files.newBufferedReader(path).lines()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        boolean result = false;

        for (int i = 0; i < integerList.size(); i++) {

            for (int j = 0; j < integerList.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (integerList.get(i) + integerList.get(j) == 2020) {
                    System.out.println(integerList.get(i) * integerList.get(j));
                    result = true;
                    break;
                }
            }

            if (result) {
                break;
            }
        }
    }
}
