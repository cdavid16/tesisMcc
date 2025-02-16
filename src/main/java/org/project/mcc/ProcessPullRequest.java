package org.project.mcc;

import lombok.extern.slf4j.Slf4j;
import org.project.mcc.models.PullRequest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    private static final String PULL_REQUEST_API_URL = "/pulls";
    private static final String PULL_REQUEST_API_PARAMETERS = "?state=all&";


    private final RestTemplateHelper restTemplate;

    public ProcessPullRequest(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplateHelper(restTemplate);
    }

    public void processRepositories() {
        REPOSITORIES_LIST.forEach(this::processRepository);
    }

    private void processRepository(final String repository) {
        String endpoint = API_URL + repository + PULL_REQUEST_API_URL + PULL_REQUEST_API_PARAMETERS;
        log.info("Calling API: {} for repository {}", endpoint, repository);
        String url = getUrlWithParametersAndSecurity(endpoint);
        List<PullRequest> prList = restTemplate.getForArrayAsList(url, PullRequest[].class);
        prList.forEach(pr -> pr.setCommentAndExport(restTemplate));
        log.info("Finished...");
    }

    public PullRequest processPr(final String repository, final String prId) {
        String endpoint = API_URL + repository + PULL_REQUEST_API_URL  + "/" + prId;
        log.info("Calling API: {} for repository {} and PrId {}", endpoint, repository, prId);
        String url = getUrlWithParametersAndSecurity(endpoint);
        log.debug("Url: {}", url);
        PullRequest pr = restTemplate.getForObject(url, PullRequest.class);
        pr.setCommentAndExport(restTemplate);
        log.info("Pr Finished...");
        return pr;
    }


}
