package com.rkumarkravi.shopkro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    // Custom ID generation logic
    public static String generateCustomId(String stateId) {
        String prefix = "SK";

        // Date in DDMMYYHHmmSS format
        String timestamp = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());

        // Random 5-character alphanumeric string
        String randomString = generateRandomString(5);

        // Combine all parts
        return prefix + stateId + timestamp + randomString;
    }

    // Method to generate a random alphanumeric string
    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

}
