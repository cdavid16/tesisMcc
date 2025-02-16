package org.project.mcc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.project.mcc.RestTemplateHelper;
import org.project.mcc.aws.AwsUtils;
import org.project.mcc.utils.FileUtils;
import org.project.mcc.utils.LanguageUtils;

import java.io.IOException;
import java.io.Serializable;
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
    private List<Comment> commentsList;

    public boolean isNotOpen() {
        return !this.state.equalsIgnoreCase(STATE_OPEN);
    }

    public void setCommentAndExport(final RestTemplateHelper restTemplate) {
        setCommentsUsingRestTemplate(restTemplate);
        if (CollectionUtils.isNotEmpty(commentsList)) {
            exportComments();
        } else {
            log.info("PR {} will not be exported since it has no comments", id);
        }
    }

    private void setCommentsUsingRestTemplate(final RestTemplateHelper restTemplate) {
        log.info("Getting comments...");
        String endpoint = url  + getUrlWithParametersAndSecurity(COMMENT_API_PATH);
        commentsList = restTemplate.getForArrayAsList(endpoint, Comment[].class).stream().filter(comment -> LanguageUtils.isAsciiWord(comment.getBody())).collect(Collectors.toList());
        log.info("Comments exported...");
    }

    private void exportComments() {
        try {
            final String filePath = FILE_PATH + id + ".json";
            FileUtils.exportObjectAsJson(this, filePath);
            AwsUtils.saveFileInS3(filePath);
            FileUtils.deleteFile(filePath);
            log.info("Export finished for PR {}", id);
        } catch (IOException e) {
            log.error("There was an error while exporting PR: ", e);
        }
    }
}
