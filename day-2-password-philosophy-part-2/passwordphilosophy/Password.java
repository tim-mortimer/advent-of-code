package passwordphilosophy;

public class Password {
    private final int position1;
    private final int position2;
    private final char c;
    private final String passwordString;

    public Password(int postition1, int position2, char c, String passwordString) {
        this.position1 = postition1;
        this.position2 = position2;
        this.c = c;
        this.passwordString = passwordString;
    }

    public boolean isValid() {
        char[] charArray = passwordString.toCharArray();

        boolean position1Held = charArray[position1 - 1] == c;
        boolean position2Held = charArray[position2 - 1] == c;

        if (position1Held && position2Held) {
            return false;
        }

        return position1Held || position2Held;
    }
}
