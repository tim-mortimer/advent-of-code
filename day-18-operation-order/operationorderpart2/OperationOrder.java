package operationorderpart2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationOrder {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-18-operation-order/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<String> expressions = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            expressions.add(line);
        }

        long result = expressions.stream()
                .map(Expression::evaluate)
                .reduce(0L, Long::sum);

        System.out.println(result);
    }
}
