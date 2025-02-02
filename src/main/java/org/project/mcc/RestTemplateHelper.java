package org.project.mcc;

import org.project.mcc.models.PullRequest;
import org.project.mcc.utils.CollectionUtils;
import org.project.mcc.utils.GitUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.project.mcc.utils.GitUtils.getUrlWithParametersAndSecurity;

public class RestTemplateHelper {

    private final RestTemplate restTemplate;

    public RestTemplateHelper(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T getForObject(String url, Class<T> returnedType) {
        HttpEntity<String> request = new HttpEntity<>(GitUtils.buildHttpHeaders());
        ResponseEntity<T> response = this.restTemplate.exchange(url, HttpMethod.GET, request, returnedType);
        return response.getBody();
    }

    public <T> List<T> getForArrayAsList(String url, Class<T[]> returnedType) {
        return CollectionUtils.castArrayToList(getForObject(url, returnedType));
    }
}
