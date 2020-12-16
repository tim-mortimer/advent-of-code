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

        bufferedReader.readLine();

        List<Integer> yourTicket = Arrays.stream(bufferedReader.readLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        bufferedReader.readLine();
        bufferedReader.readLine();

        List<List<Integer>> nearbyTickets = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            List<Integer> nearbyTicket = Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            nearbyTickets.add(nearbyTicket);
        }

        long errorRate = 0;
        List<List<Integer>> validTickets = new ArrayList<>();

        for (List<Integer> nearbyTicket: nearbyTickets) {
            boolean ticketIsValid = true;

            for (Integer fieldValue : nearbyTicket) {
                boolean fieldValueIsValid = false;

                for (String field : fieldValidationRules.keySet()) {
                    List<List<Integer>> rules = fieldValidationRules.get(field);

                    for (List<Integer> rule : rules) {
                        if (fieldValue >= rule.get(0) && fieldValue <= rule.get(1)) {
                            fieldValueIsValid = true;
                            break;
                        }
                    }
                }

                if (!fieldValueIsValid) {
                    ticketIsValid = false;
                    errorRate += fieldValue;
                }
            }

            if (ticketIsValid) {
                validTickets.add(nearbyTicket);
            }
        }

        System.out.println(errorRate);

        List<Map<String, List<Integer>>> validTicketFieldIndices = new ArrayList<>();

        for (List<Integer> validTicket: validTickets) {
            Map<String, List<Integer>> validFieldIndices = new HashMap<>();

            for (int i = 0; i < validTicket.size(); i++) {
                int value = validTicket.get(i);

                for (String field: fieldValidationRules.keySet()) {
                    List<List<Integer>> rules = fieldValidationRules.get(field);
                    List<Integer> validIndices = new ArrayList<>();

                    if (validFieldIndices.containsKey(field)) {
                        validIndices = validFieldIndices.get(field);
                    }

                    for (List<Integer> rule: rules) {
                        if (value >= rule.get(0) && value <= rule.get(1)) {
                            validIndices.add(i);
                            break;
                        }
                    }

                    validFieldIndices.put(field, validIndices);
                }
            }

            validTicketFieldIndices.add(validFieldIndices);
        }

        Map<String, List<Integer>> reducedValidFieldIndices = validTicketFieldIndices.get(0);

        for (Map<String, List<Integer>> validFieldIndices: validTicketFieldIndices) {
            for (String field: validFieldIndices.keySet()) {
                List<Integer> previousValidFieldIndices = reducedValidFieldIndices.get(field);
                List<Integer> currentValidFieldIndices = validFieldIndices.get(field);

                List<Integer> intersectionOfFieldIndices = previousValidFieldIndices.stream()
                        .filter(currentValidFieldIndices::contains)
                        .collect(Collectors.toList());

                reducedValidFieldIndices.put(field, intersectionOfFieldIndices);
            }
        }

        Map<String, Integer> fieldIndices = new HashMap<>();

        while (fieldIndices.keySet().size() < reducedValidFieldIndices.keySet().size()) {
            for (String field: reducedValidFieldIndices.keySet()) {
                List<Integer> validIndices = reducedValidFieldIndices.get(field);

                if (validIndices.size() != 1) {
                    continue;
                }

                int index = validIndices.get(0);
                fieldIndices.put(field, index);

                reducedValidFieldIndices.replaceAll(
                        (f, v) -> reducedValidFieldIndices.get(f)
                                .stream()
                                .filter(currentIndex -> currentIndex != index)
                                .collect(Collectors.toList())
                );
            }
        }

        long result = 1;

        for (String field: fieldIndices.keySet()) {
            if (field.startsWith("departure")) {
                result *= yourTicket.get(fieldIndices.get(field));
            }
        }

        System.out.println(result);
    }
}
