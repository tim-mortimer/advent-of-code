package binaryboarding;

public class Range {
    private final int lower;
    private final int upper;

    public Range(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public Range takeLower() {
        return new Range(lower, (lower + upper) / 2);
    }

    public Range takeUpper() {
        return new Range((lower + upper + 1) / 2, upper);
    }

    public int lower() {
        return lower;
    }

    @Override
    public String toString() {
        return "Range{" +
                "lower=" + lower +
                ", upper=" + upper +
                '}';
    }
}
