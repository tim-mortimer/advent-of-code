package operationorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    public static long evaluate(String expression) {
        Pattern parenthesisPattern = Pattern.compile("\\(([^\\()]+)\\)");
        Matcher parenthesisMatcher = parenthesisPattern.matcher(expression);

        while (parenthesisMatcher.find()) {
            String prioritisedExpression = parenthesisMatcher.group(1);
            String evaluatedPrioritisedExpression = "" + Expression.evaluate(prioritisedExpression);

            expression = expression.substring(0, parenthesisMatcher.start(1) - 1) +
                    evaluatedPrioritisedExpression +
                    expression.substring(parenthesisMatcher.end(1) + 1);

            parenthesisMatcher = parenthesisPattern.matcher(expression);
        }

        Pattern operandPattern = Pattern.compile("^(.+) ([*+]) (\\d+)$");
        Matcher operandMatcher = operandPattern.matcher(expression);

        if (operandMatcher.find()) {
            long firstOperand = Expression.evaluate(operandMatcher.group(1));
            boolean isMultiplication = operandMatcher.group(2).equals("*");
            long secondOperand = Long.parseLong(operandMatcher.group(3));

            if (isMultiplication) {
                return firstOperand * secondOperand;
            }

            return firstOperand + secondOperand;
        }

        return Long.parseLong(expression);
    }
}
