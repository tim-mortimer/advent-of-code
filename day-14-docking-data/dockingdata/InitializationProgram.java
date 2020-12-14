package dockingdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializationProgram {

    public Map<Integer, Long> execute(ArrayList<String> instructions) {
        Map<Integer, Long> output = new HashMap<>();
        String mask = null;

        for (String instruction: instructions) {
            if (instruction.startsWith("mask = ")) {
                mask = instruction.substring(7);
            } else {
                if (mask == null) {
                    System.out.println("Error");
                    break;
                }

                Pattern pattern = Pattern.compile("^mem\\[(\\d+)] = (\\d+)$");
                Matcher matcher = pattern.matcher(instruction);
                int memoryLocation;
                int value;

                if (!matcher.find()) {
                    System.out.println("Error");
                    break;
                }

                memoryLocation = Integer.parseInt(matcher.group(1));
                value = Integer.parseInt(matcher.group(2));
                String bitValue = Integer.toBinaryString(value);
                bitValue = "0".repeat(36 - bitValue.length()) + bitValue;

                StringBuilder resultBuilder = new StringBuilder();

                for (int i = 0; i < bitValue.length(); i++) {
                    if (mask.charAt(i) != 'X') {
                        resultBuilder.append(mask.charAt(i));
                    } else {
                        resultBuilder.append(bitValue.charAt(i));
                    }
                }

                String result = resultBuilder.toString();
                output.put(memoryLocation, Long.parseLong(result, 2));
            }
        }

        return output;
    }
}
