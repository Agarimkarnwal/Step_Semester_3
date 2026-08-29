public class ReverseCustomerName {
    public static String reverseCustomerName(String customerName) {
        char[] characters = customerName.toCharArray();
        for (int left = 0, right = characters.length - 1; left < right; left++, right--) {
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        String customerName = "Sunil";
        String reversedName = reverseCustomerName(customerName);
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);
    }
}
