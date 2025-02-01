package org.project.mcc.utils;

public final class SecurityUtils {

    private SecurityUtils() {}
    public static final String AUTH_KEYWORD = "[AUTHORIZATION]";

    public static String getUrlWithPassword(final String url) {
        if (url.contains(AUTH_KEYWORD)) {
            return url.replaceAll(AUTH_KEYWORD, getAuthKey());
        }
        return url;
    }

    private static String getAuthKey() {
        if (null == System.getenv("AUTH_KEY")) {
            throw new IllegalArgumentException("Authentication is not set, please set AUTH_KEY with the proper value");
        }
        return System.getenv("AUTH_KEY");
    }

}
