package tickettranslation;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TicketTranslation {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-16-ticket-translation/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        Map<String, List<List<Integer>>> fieldValidationRules = new HashMap<>();

        while (!(line = bufferedReader.readLine()).equals("")) {
            String field = line.substring(0, line.indexOf(":"));
            List<List<Integer>> validationRules = new ArrayList<>();

            Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                List<Integer> validationRule = new ArrayList<>();
                validationRule.add(Integer.parseInt(matcher.group(1)));
                validationRule.add(Integer.parseInt(matcher.group(2)));
                validationRules.add(validationRule);
            }

            fieldValidationRules.put(field, validationRules);
        }

        System.out.println(fieldValidationRules);

        bufferedReader.readLine();

        List<Integer> yourTickets = Arrays.stream(bufferedReader.readLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(yourTickets);

        bufferedReader.readLine();
        bufferedReader.readLine();

        List<List<Integer>> nearbyTickets = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            List<Integer> nearbyTicket = Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            nearbyTickets.add(nearbyTicket);
        }

        System.out.println(nearbyTickets);

        long errorRate = 0;

        for (List<Integer> nearbyTicket: nearbyTickets) {
            for (int fieldValue: nearbyTicket) {
                boolean isValid = false;

                for (List<List<Integer>> rules: fieldValidationRules.values()) {
                    for (List<Integer> rule: rules) {
                        if (fieldValue >= rule.get(0) && fieldValue <= rule.get(1)) {
                            isValid = true;
                            break;
                        }
                    }
                }

                if (!isValid) {
                    errorRate += fieldValue;
                }
            }
        }

        System.out.println(errorRate);
    }
}
