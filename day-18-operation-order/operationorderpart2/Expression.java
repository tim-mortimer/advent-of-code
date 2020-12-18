package operationorderpart2;

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

        Pattern multiplicationPattern = Pattern.compile("^(.+) \\* (.+)$");
        Matcher multiplicationMatcher = multiplicationPattern.matcher(expression);

        if (multiplicationMatcher.find()) {
            return Expression.evaluate(multiplicationMatcher.group(1)) * Expression.evaluate(multiplicationMatcher.group(2));
        }

        Pattern additionPattern = Pattern.compile("^(.+) \\+ (\\d+)$");
        Matcher additionMatcher = additionPattern.matcher(expression);

        if (additionMatcher.find()) {
            return Expression.evaluate(additionMatcher.group(1)) + Expression.evaluate(additionMatcher.group(2));
        }

        return Long.parseLong(expression);
    }
}
