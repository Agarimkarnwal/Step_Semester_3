import java.util.Arrays;
import java.util.Scanner;

public class PalindromeChecker {
    public static boolean isPalindromeIterative(String text) {
        for (int left = 0, right = text.length() - 1; left < right; left++, right--) {
            if (text.charAt(left) != text.charAt(right)) return false;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = text.toCharArray();
        for (int left = 0, right = reversed.length - 1; left < right; left++, right--) {
            char temp = reversed[left];
            reversed[left] = reversed[right];
            reversed[right] = temp;
        }
        return Arrays.equals(original, reversed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean arrayReversal = isPalindromeArrayReversal(text);

        System.out.println("Iterative: " + (iterative ? "Palindrome" : "Not Palindrome"));
        System.out.println("Recursive: " + (recursive ? "Palindrome" : "Not Palindrome"));
        System.out.println("Array Reversal: " + (arrayReversal ? "Palindrome" : "Not Palindrome"));
        System.out.println("All approaches agree: " + (iterative == recursive && recursive == arrayReversal));
        scanner.close();
    }
}
