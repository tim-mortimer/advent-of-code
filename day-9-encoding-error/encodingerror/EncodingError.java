package encodingerror;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EncodingError {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-9-encoding-error/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Long> preamble = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            preamble.add(Long.parseLong(line));
        }

        for (int i = 25; i < preamble.size(); i++) {
            boolean isValid = false;
            long numberUnderInspection = preamble.get(i);

            for (int j = i - 25; j < i; j++) {
                for (int k = i - 25; k < i; k++) {
                    if (j == k) {
                        continue;
                    }

                    if (preamble.get(j) + preamble.get(k) == numberUnderInspection) {
                        isValid = true;
                        break;
                    }
                }
            }

            if (!isValid) {
                System.out.println(numberUnderInspection);
                break;
            }
        }
    }
}
