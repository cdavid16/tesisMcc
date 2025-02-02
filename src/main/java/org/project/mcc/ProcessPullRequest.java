package org.project.mcc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.project.mcc.models.PullRequest;
import org.project.mcc.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.project.mcc.utils.GitUtils.getUrlWithParametersAndSecurity;

@Slf4j
public class ProcessPullRequest {

    private static final List<String> REPOSITORIES_LIST = List.of(
            "/apache/commons-lang",
            "/iluwatar/java-design-patterns",
            "/doocs/advanced-java",
            "/spring-projects/spring-boot",
            "/elastic/elasticsearch",
            "/spring-projects/spring-framework",
            "/google/guava",
            "/netty/netty",
            "/xkcoding/spring-boot-demo",
            "/alibaba/easyexcel",
            "/apache/kafka"
    );

    private static final String API_URL = "https://api.github.com/repos";
    private static final String PULL_REQUEST_API_URL = "/pulls?state=all&";


    private final RestTemplateHelper restTemplate;

    public ProcessPullRequest(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplateHelper(restTemplate);
    }

    public void process() {
        REPOSITORIES_LIST.forEach(this::processPr);
    }

    private void processPr(final String repository) {
        String endpoint = API_URL + repository + PULL_REQUEST_API_URL;
        log.info("Calling API: {} for repository {}", endpoint, repository);
        String url = getUrlWithParametersAndSecurity(endpoint);
        List<PullRequest> prList = restTemplate.getForArrayAsList(url, PullRequest[].class);
        prList.forEach(pr -> pr.setCommentAndExport(restTemplate));
        log.info("Finished...");
    }

}
