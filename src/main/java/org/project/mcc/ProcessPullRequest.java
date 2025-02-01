package org.project.mcc;

import lombok.extern.slf4j.Slf4j;
import org.project.mcc.models.Comment;
import org.project.mcc.models.PullRequest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
public class ProcessPullRequest {

    private static final String PULL_REQUEST_API_URL = "https://api.github.com/repos/apache/commons-lang/pulls?state=all&";
    private static final String COMMENT_API_PATH = "/comments?";
    private static final String PARAMETERS = "Authorization=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&X-GitHub-Api-Version=2022-11-28&Accept=application/vnd.github+json";

    private final RestTemplate restTemplate;

    public ProcessPullRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void process() {
        log.info("Calling API: {}{}", PULL_REQUEST_API_URL, PARAMETERS);
        PullRequest[] requests = restTemplate.getForObject(PULL_REQUEST_API_URL + PARAMETERS, PullRequest[].class);
        assert requests != null;
        Arrays.stream(requests).forEach(r -> {
                    Comment[] comments = restTemplate.getForObject(r.getUrl()  + COMMENT_API_PATH + PARAMETERS, Comment[].class);
                    assert comments != null;
                    if(comments.length > 0) {
                        log.info(r.toString());
                        Arrays.stream(comments).map(Comment::getBody).forEach(log::info);
                    }
                }
        );
    }

}
