package allergenassessment;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AllergenAssessment {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("day-21-allergen-assessment/input.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;

        List<Map<String, List<String>>> foods = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            Pattern foodPattern = Pattern.compile("^([a-z\\s]+)(?: \\(contains (.+)\\))?$");
            Matcher foodMatcher = foodPattern.matcher(line);

            if (!foodMatcher.matches()) {
                continue;
            }

            Map<String, List<String>> food = new HashMap<>();
            List<String> ingredients = Arrays.asList(foodMatcher.group(1).split("\\s"));
            food.put("ingredients", ingredients);

            if (foodMatcher.groupCount() == 2) {
                List<String> allergens = Arrays.asList(foodMatcher.group(2).split(",\\s"));
                food.put("allergens", allergens);
            }

            foods.add(food);
        }
        
        List<String> allergens = foods.stream()
                .map(food -> food.getOrDefault("allergens", List.of()))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<String>> allergensToPotentialIngredients = new HashMap<>();

        allergens.forEach(allergen -> {
            List<String> potentialContainingIngredients = null;

            for (Map<String, List<String>> food : foods) {
                List<String> containedAllergens = food.getOrDefault("allergens", List.of());

                if (!containedAllergens.contains(allergen)) {
                    continue;
                }

                if (potentialContainingIngredients == null) {
                    potentialContainingIngredients = food.get("ingredients");
                } else {
                    potentialContainingIngredients = potentialContainingIngredients.stream()
                            .filter(ingredient -> food.get("ingredients").contains(ingredient))
                            .collect(Collectors.toList());
                }

                allergensToPotentialIngredients.put(allergen, potentialContainingIngredients);
            }
        });

        Map<String, String> allergensToIngredients = new HashMap<>();

        while (allergensToIngredients.size() < allergensToPotentialIngredients.size()) {
            allergensToPotentialIngredients.forEach((allergen, potentialIngredients) -> {
                if (potentialIngredients.size() == 1) {
                    String ingredient = potentialIngredients.get(0);
                    allergensToIngredients.put(allergen, ingredient);

                    allergensToPotentialIngredients.forEach((currentAllergen, currentPotentialIngredients) -> {
                        List<String> filteredPotentialIngredients = new ArrayList<>(currentPotentialIngredients);
                        filteredPotentialIngredients.remove(ingredient);

                        allergensToPotentialIngredients.put(currentAllergen, filteredPotentialIngredients);
                    });
                }
            });
        }

        Collection<String> ingredientsContainingAllergens = allergensToIngredients.values();

        long nonAllergenContainingIngredientInclusionCount = foods.stream()
                .map(food -> food.get("ingredients"))
                .flatMap(Collection::stream)
                .filter(ingredient -> !ingredientsContainingAllergens.contains(ingredient))
                .count();

        System.out.println(nonAllergenContainingIngredientInclusionCount);

        String ingredientsString = allergensToIngredients.keySet()
                .stream()
                .sorted()
                .map(allergensToIngredients::get)
                .collect(Collectors.joining(","));

        System.out.println(ingredientsString);
    }
}
