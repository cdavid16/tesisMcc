package org.project.mcc.models;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.project.mcc.RestTemplateHelper;
import org.project.mcc.utils.FileUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.project.mcc.utils.GitUtils.getUrlWithParametersAndSecurity;

@Data
@Slf4j
public class PullRequest implements Serializable {

    private static final String STATE_OPEN = "open";
    private static final String COMMENT_API_PATH = "/comments?";
    private static final String FILE_PATH = "/Users/c_david/IdeaProjects/tesisMcc/out/";

    private long id;
    private String url;
    private String title;
    @ToString.Exclude private String body;
    private String state;
    private List<Comment> comments;

    public void setCommentAndExport(final RestTemplateHelper restTemplate) {
        setComments(restTemplate);
        if (CollectionUtils.isNotEmpty(comments)) {
            exportComments();
        } else {
            log.info("PR {} will not be exported since it has no comments", id);
        }
    }

    private void setComments(final RestTemplateHelper restTemplate) {
        log.info("Getting comments...");
        String endpoint = url  + getUrlWithParametersAndSecurity(COMMENT_API_PATH);
        comments = restTemplate.getForArrayAsList(endpoint, Comment[].class);
        log.info("Comments exported...");
    }

    private void exportComments() {
        try {
            FileUtils.exportObjectAsJson(this, FILE_PATH + id + ".json");
            log.info("Export finished for PR {}", id);
        } catch (IOException e) {
            log.error("There was an error while exporting PR: ", e);
        }
    }
}
