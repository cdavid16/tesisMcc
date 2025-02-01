package org.project.mcc.models;

import lombok.Data;
import lombok.ToString;

@Data
public class PullRequest {

    private static final String STATE_OPEN = "open";

    private long id;
    private String url;
    private String title;
    @ToString.Exclude private String body;
    private String state;

    public boolean isNotOpen() {
        return !this.state.equalsIgnoreCase(STATE_OPEN);
    }
}
