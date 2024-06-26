package com.example.utokyoprompts.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatMessage (String message) {
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return message + " Current time: " + formattedDateTime;
    }
}
