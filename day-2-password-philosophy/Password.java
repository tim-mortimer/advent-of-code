public class Password {
    private final int min;
    private final int max;
    private final char c;
    private final String passwordString;

    public Password(int min, int max, char c, String passwordString) {
        this.min = min;
        this.max = max;
        this.c = c;
        this.passwordString = passwordString;
    }

    public boolean isValid() {
        int characterOccurenceCount = countCharacterOccurrence();

        return characterOccurenceCount >= min && characterOccurenceCount <= max;
    }

    public int countCharacterOccurrence() {
        char[] characterArray = passwordString.toCharArray();
        int characterOccurrenceCount = 0;

        for (char value : characterArray) {
            if (value == c) {
                characterOccurrenceCount++;
            }
        }

        return characterOccurrenceCount;
    }
}
