package com.hewen.utils;

import java.util.Random;

public class VerificationCodeGenerator {

    public static String generateVerificationCode(int length) {
        // 所有可能的字符
        String chars = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}

