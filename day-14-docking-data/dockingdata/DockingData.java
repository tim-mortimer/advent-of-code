package dockingdata;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class DockingData {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-14-docking-data/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        ArrayList<String> initializationProgramInstructions = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            initializationProgramInstructions.add(line);
        }

        InitializationProgram initializationProgram = new InitializationProgram();
        Map<Integer, Long> output = initializationProgram.execute(initializationProgramInstructions);

        long sumOfMemoryValues = 0;

        for (int key: output.keySet()) {
            sumOfMemoryValues += output.get(key);
        }

        System.out.println(sumOfMemoryValues);
    }
}
