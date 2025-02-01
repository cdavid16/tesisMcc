package org.project.mcc;

import lombok.extern.slf4j.Slf4j;
import org.project.mcc.models.Comment;
import org.project.mcc.models.PullRequest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.project.mcc.utils.GitUtils.getUrlWithParametersAndSecurity;

@Slf4j
public class ProcessPullRequest {

    private static final String PULL_REQUEST_API_URL = "https://api.github.com/repos/apache/commons-lang/pulls?state=all&";
    private static final String COMMENT_API_PATH = "/comments?";

    private final RestTemplate restTemplate;

    public ProcessPullRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void process() {
        log.info("Calling API: {}", PULL_REQUEST_API_URL);
        PullRequest[] requests = restTemplate.getForObject(getUrlWithParametersAndSecurity(PULL_REQUEST_API_URL), PullRequest[].class);
        assert requests != null;
        Arrays.stream(requests).forEach(r -> {
                    Comment[] comments = restTemplate.getForObject(r.getUrl()  + getUrlWithParametersAndSecurity(COMMENT_API_PATH), Comment[].class);
                    assert comments != null;
                    if(comments.length > 0) {
                        log.info(r.toString());
                        Arrays.stream(comments).map(Comment::getBody).forEach(log::info);
                    }
                }
        );
    }

}
