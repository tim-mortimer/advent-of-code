import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PasswordPhilosophy {

    public static void main(String[] args) throws IOException {
        Path path = new File("day-2-password-philosophy/input.txt").toPath();
        long validPasswordCount = Files.newBufferedReader(path).lines()
                .map(password -> {
                    int colonIndex = password.indexOf(":");
                    String ruleString = password.substring(0, colonIndex);
                    String rangeString = ruleString.substring(0, ruleString.length() - 2);
                    int hyphenIndex = rangeString.indexOf("-");

                    return new Password(
                            Integer.parseInt(rangeString.substring(0, hyphenIndex)),
                            Integer.parseInt(rangeString.substring(hyphenIndex + 1)),
                            ruleString.charAt(ruleString.length() - 1),
                            password.substring(colonIndex + 2)
                    );
                })
                .filter(Password::isValid)
                .count();

        System.out.println(validPasswordCount);
    }
}
