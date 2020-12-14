package dockingdatapart2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializationProgram {

    public Map<Long, Long> execute(ArrayList<String> instructions) {
        Map<Long, Long> output = new HashMap<>();
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
                long value;

                if (!matcher.find()) {
                    System.out.println("Error");
                    break;
                }

                memoryLocation = Integer.parseInt(matcher.group(1));
                value = Integer.parseInt(matcher.group(2));
                String bitMemoryLocation = Integer.toBinaryString(memoryLocation);
                bitMemoryLocation = "0".repeat(36 - bitMemoryLocation.length()) + bitMemoryLocation;

                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < bitMemoryLocation.length(); i++) {
                    if (mask.charAt(i) == '0') {
                        builder.append(bitMemoryLocation.charAt(i));
                    } else {
                        builder.append(mask.charAt(i));
                    }
                }

                String maskedMemoryLocation = builder.toString();

                int xCount = 0;

                for (char c: maskedMemoryLocation.toCharArray()) {
                    if (c == 'X') {
                        xCount++;
                    }
                }

                int combinations = (int) Math.pow(2, xCount);

                for (int i = 0; i < combinations; i++) {
                    String binaryString = Integer.toBinaryString(i);
                    binaryString = "0".repeat(xCount - binaryString.length()) + binaryString;

                    String floatingMemoryLocation = maskedMemoryLocation;

                    for (int j = 0; j < xCount; j++) {
                        floatingMemoryLocation = floatingMemoryLocation.replaceFirst("X", "" + binaryString.charAt(j));
                    }

                    output.put(Long.parseLong(floatingMemoryLocation, 2), value);
                }
            }
        }

        return output;
    }
}
