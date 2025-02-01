package org.project.mcc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {

    private String login;
    private long id;
    @JsonProperty("node_id") private String nodeId;
    @JsonProperty("avatar_url") private String avatarUrl;
    @JsonProperty("gravatar_id") private String gravatarId;
    private String url;
    @JsonProperty("html_url") private String htmlUrl;
    @JsonProperty("followers_url") private String followersUrl;
    @JsonProperty("following_url") private String followingUrl;
    @JsonProperty("gists_url") private String gistsUrl;
    @JsonProperty("starred_url") private String starredUrl;
    @JsonProperty("subscriptions_url") private String subscriptionsUrl;
    @JsonProperty("organizations_url") private String organizationsUrl;
    @JsonProperty("repos_url") private String reposUrl;
    @JsonProperty("events_url") private String eventsUrl;
    @JsonProperty("received_events_url") private String receivedEventsUrl;
    private String type;
    @JsonProperty("user_view_type") private String userViewType;
    @JsonProperty("site_admin") private boolean siteAdmin;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", type='" + type + '\'' +
                ", userViewType='" + userViewType + '\'' +
                ", siteAdmin=" + siteAdmin +
                '}';
    }
}
