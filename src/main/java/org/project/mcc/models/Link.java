package org.project.mcc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Link {
    private LinkUrl self;
    private LinkUrl html;
    @JsonProperty("pull_request") private LinkUrl pullRequest;

    @Override
    public String toString() {
        return "Link{" +
                "self=" + self +
                ", html=" + html +
                ", pullRequest=" + pullRequest +
                '}';
    }

    @Data
    private static class LinkUrl {
        private String href;

        @Override
        public String toString() {
            return "LinkUrl{" +
                    "href='" + href + '\'' +
                    '}';
        }
    }


}

