package org.project.mcc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.project.mcc.enums.CommentTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private String url;
    @JsonProperty("pull_request_review_id") private String pullRequestReviewId;
    private long id;

    @Override
    public String toString() {
        return "Comment{" +
                "url='" + url + '\'' +
                ", pullRequestReviewId='" + pullRequestReviewId + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", diffHunk='" + diffHunk + '\'' +
                ", path='" + path + '\'' +
                ", commitId='" + commitId + '\'' +
                ", originalCommitId='" + originalCommitId + '\'' +
                ", user=" + user +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", pullRequestUrl='" + pullRequestUrl + '\'' +
                ", authorAssociation='" + authorAssociation + '\'' +
                ", links=" + links +
                ", reactions=" + reactions +
                ", startLine=" + startLine +
                ", originalStartLine=" + originalStartLine +
                ", startSide='" + startSide + '\'' +
                ", line=" + line +
                ", originalLine=" + originalLine +
                ", side='" + side + '\'' +
                ", originalPosition=" + originalPosition +
                ", position=" + position +
                ", subjectType='" + subjectType + '\'' +
                '}';
    }

    @JsonProperty("node_id") private String nodeId;
    @JsonProperty("diff_hunk") private String diffHunk;
    private String path;
    @JsonProperty("commit_id") private String commitId;
    @JsonProperty("original_commit_id") private String originalCommitId;
    private User user;
    private String body;
    @JsonProperty("created_at") private Date createdAt;
    @JsonProperty("updated_at") private Date updatedAt;
    @JsonProperty("html_url") private String htmlUrl;
    @JsonProperty("pull_request_url") private String pullRequestUrl;
    @JsonProperty("author_association") private String authorAssociation;
    @JsonProperty("_links") private Link links;
    private Reaction reactions;
    @JsonProperty("start_line") private Integer startLine;
    @JsonProperty("original_start_line") private Integer originalStartLine;
    @JsonProperty("start_side") private String startSide;
    private int line;
    @JsonProperty("original_line") private int originalLine;
    private String side;
    @JsonProperty("original_position") private int originalPosition;
    private int position;
    @JsonProperty("subject_type") private String subjectType;
    private List<CommentTypeEnum> multiClasses = new ArrayList<>();
    private CommentTypeEnum singleClass = CommentTypeEnum.NOT_DEFINED;
}
