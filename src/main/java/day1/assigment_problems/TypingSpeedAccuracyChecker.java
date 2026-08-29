public class TypingSpeedAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;

        int length = Math.min(original.length(), typed.length());
        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        int totalCharacters = Math.max(original.length(), typed.length());
        double accuracy = totalCharacters == 0 ? 100.0 : matched * 100.0 / totalCharacters;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, totalCharacters, accuracy);

        if (firstMismatch == -1 && original.length() == typed.length()) {
            System.out.println(" | No Mismatches");
        } else if (firstMismatch != -1) {
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        } else {
            System.out.println(" | Length Mismatch");
        }
    }

    public static void main(String[] args) {
        String original = "hello world";
        String typed = "hello worlt";
        checkTypingAccuracy(original, typed);
    }
}
