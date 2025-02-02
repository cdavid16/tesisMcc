package org.project.mcc.utils;

public final class LanguageUtils {

    private static final int HIGHEST_ASCII_VALUE = 127;

    private LanguageUtils() {}

    public static boolean isAsciiWord(String word) {
        for (char c: word.toCharArray()) {
            if (c > HIGHEST_ASCII_VALUE) {
                return false;
            }
        }
        return true;
    }

}
