package passportprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PassportProcessing {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-4-passport-processing/input.txt");
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<HashMap<String, String>> passportData = new ArrayList<>();
        HashMap<String, String> passport  = new HashMap<>();

        while((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                passportData.add(passport);
                passport = new HashMap<>();
                continue;
            }

            line = line.replace("\n", "");

            HashMap<String, String> finalPassport = passport;
            Arrays.stream(line.split(" "))
                    .forEach(keyPairString -> {
                        String[] keyPair = keyPairString.split(":");
                        finalPassport.put(keyPair[0], keyPair[1]);
                    });

            passport = finalPassport;
        }

        long validPassportCount = passportData.stream()
                .filter(subject -> {
                    return subject.keySet().size() == 8 ||
                            (subject.keySet().size() == 7 && !subject.containsKey("cid"));
                })
                .count();

        System.out.println(validPassportCount);
    }
}
