package handheldhalting;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandheldHalting {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-8-handheld-halting/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Map<String, String>> operations = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("\\s");

            Map<String, String> operation = new HashMap<>();
            operation.put("instruction", split[0]);
            operation.put("value", split[1]);
            operation.put("executed", "false");

            operations.add(operation);
        }

        long accumulator = 0;
        int i = 0;

        while (true) {
            Map<String, String> operation = operations.get(i);
            String instruction = operation.get("instruction");
            int value = Integer.parseInt(operation.get("value"));
            boolean executed = Boolean.parseBoolean(operation.get("executed"));

            if (executed) {
                System.out.println(accumulator);
                break;
            }

            if (instruction.equals("acc")) {
                accumulator += value;
                i += 1;
            } else if (instruction.equals("jmp")) {
                i += value;
            } else {
                i += 1;
            }

            operation.put("executed", "true");
        }
    }
}
