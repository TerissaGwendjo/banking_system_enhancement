package org.example.banking_system.util;

import java.util.Random;

public class AccountNumberGenerator {
    // Define a constant for the length of the account number
    private static final int ACCOUNT_NUMBER_LENGTH = 12;

    /**
     * Generates a random account number of length ACCOUNT_NUMBER_LENGTH.
     * @return A randomly generated account number as a String.
     */
    public static String generateAccountNumber() {
        // Create an instance of the Random class to generate random numbers
        Random rand = new Random();
        // Use StringBuilder for efficient string concatenation
        StringBuilder accountNumber = new StringBuilder();

        // Generate a random 12-digit account number
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            // Generate a random digit between 0 and 9
            int digit = rand.nextInt(10);
            // Append the digit to the account number
            accountNumber.append(digit);
        }

        // Convert StringBuilder to String and return the generated account number
        return accountNumber.toString();
    }
}
