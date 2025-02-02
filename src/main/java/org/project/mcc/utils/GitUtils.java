package org.project.mcc.utils;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

import static org.project.mcc.utils.SecurityUtils.AUTH_KEYWORD;
import static org.project.mcc.utils.SecurityUtils.getAuthKey;

@Log4j2
public final class GitUtils {

    private static final String GITHUB_API_VERSION_PARAMETER = "X-GitHub-Api-Version=2022-11-28";
    private static final String ACCEPT_TYPE_PARAMETER = "Accept=application/vnd.github+json";
    private static final String AUTHORIZATION_PARAMETER = "Authorization=" + AUTH_KEYWORD;

    private static final List<String> PARAMETERS = List.of(
            GITHUB_API_VERSION_PARAMETER,
            ACCEPT_TYPE_PARAMETER,
            AUTHORIZATION_PARAMETER
    );

    public static void appendGitHubParameters(String url) {
        PARAMETERS.forEach(parameter -> appendParameter(url, parameter));
    }

    public static HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAuthKey());
        return headers;
    }

    private static void appendParameter(@NonNull String url, final String parameter) {
        if(!url.endsWith("?")) {
            url = url + "&";
        }
        url =  url + parameter;
        log.debug("Parameter {} added to url, result is {}", parameter, url);
    }

    public static String getUrlWithParametersAndSecurity(final String url) {
        appendGitHubParameters(url);
        return SecurityUtils.getUrlWithPassword(url);
    }
}
