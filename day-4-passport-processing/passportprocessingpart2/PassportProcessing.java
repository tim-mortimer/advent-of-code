package passportprocessingpart2;

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
                        String key = keyPair[0];
                        String value = keyPair[1];

                        if (validate(key, value)) {
                            finalPassport.put(key, value);
                        }
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

    private static boolean validate(String key, String value) {
        switch (key) {
            case "byr" -> {
                int intValue = Integer.parseInt(value);
                return intValue >= 1920 && intValue <= 2002;
            }
            case "iyr" -> {
                int intValue = Integer.parseInt(value);
                return intValue >= 2010 && intValue <= 2020;
            }
            case "eyr" -> {
                int intValue = Integer.parseInt(value);
                return intValue >= 2020 && intValue <= 2030;
            }
            case "hgt" -> {
                if (!value.matches("^\\d+(cm|in)$")) {
                    return false;
                }

                int intValue = Integer.parseInt(value.substring(0, value.length() - 2));

                if (value.endsWith("cm")) {
                    return intValue >= 150 && intValue <= 193;
                } else {
                    return intValue >= 59 && intValue <= 76;
                }
            }
            case "hcl" -> {
                return value.matches("^#[0-9a-f]{6}$");
            }
            case "ecl" -> {
                return value.matches("^(amb|blu|brn|gry|grn|hzl|oth)$");
            }
            case "pid" -> {
                return value.matches("^[0-9]{9}$");
            }
            case "cid" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
