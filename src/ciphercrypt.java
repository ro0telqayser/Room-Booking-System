public class ciphercrypt {
    // Encryption method using the Caesar Cipher with a while loop
    public static String encrypt(String text, int shift) {
        // StringBuilder to store the encrypted result
        StringBuilder result = new StringBuilder();
        // Index variable for iterating through the input text
        int i = 0;

        // Loop through each character in the input text
        for (i = 0; i < text.length(); i++) {
            // Retrieve the current character
            char ch = text.charAt(i);
    
            // Check if the character is a letter
            if (Character.isLetter(ch)) {
                // Perform Caesar Cipher shift on letters
                char shifted = (char) (ch + shift);
    
                // Ensure the result stays within the valid range of alphabetic characters
                if (Character.isLowerCase(ch) && shifted > 'z') {
                    shifted = (char) (shifted - 26);
                } else if (Character.isUpperCase(ch) && shifted > 'Z') {
                    shifted = (char) (shifted - 26);
                }
    
                // Append the shifted character to the result
                result.append(shifted);
            } else {
                // If the character is not a letter, append it unchanged
                result.append(ch);
            }
        }

        // Convert the StringBuilder to String and return the encrypted result
        return result.toString();
    }
    public static String decrypt(String text, int shift) {
        // StringBuilder to store the decrypted result
        StringBuilder result = new StringBuilder();
        // Index variable for iterating through the input text
        int i = 0;
    
        // Loop through each character in the input text
        for (i = 0; i < text.length(); i++) {
            // Retrieve the current character
            char ch = text.charAt(i);
    
            // Check if the character is a letter
            if (Character.isLetter(ch)) {
                // Perform Caesar Cipher shift on letters
                char shifted = (char) (ch - shift);
    
                // Ensure the result stays within the valid range of alphabetic characters
                if (Character.isLowerCase(ch) && shifted < 'a') {
                    shifted = (char) (shifted + 26);
                } else if (Character.isUpperCase(ch) && shifted < 'A') {
                    shifted = (char) (shifted + 26);
                }
    
                // Append the shifted character to the result
                result.append(shifted);
            } else {
                // If the character is not a letter, append it unchanged
                result.append(ch);
            }
        }
    
        // Convert the StringBuilder to String and return the decrypted result
        return result.toString();
    }
    

    // Main method for testing the Caesar Cipher
    public static void main(String[] args) {
        // Input text to be encrypted
        String plaintext = "yahia";
        // Caesar Cipher shift value
        int shift = 3;

        // Encrypt the plaintext using the Caesar Cipher
        String ciphertext = encrypt(plaintext, shift);
        String cipherdecyrpt = decrypt(ciphertext, shift);

        // Print the encrypted text to the console
        System.out.println("Decrypted: " + cipherdecyrpt);
        System.out.println("Plaintext: "+ plaintext);
        System.out.println("Encrypted: " + ciphertext);
    }

}
