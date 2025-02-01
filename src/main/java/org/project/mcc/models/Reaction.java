package org.project.mcc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Reaction {

    private String url;
    @JsonProperty("total_count") private int totalCount;
    private int laugh;
    private int hooray;
    private int confused;
    private int heart;
    private int rocket;
    private int eyes;

    @Override
    public String toString() {
        return "Reaction{" +
                "url='" + url + '\'' +
                ", totalCount=" + totalCount +
                ", laugh=" + laugh +
                ", hooray=" + hooray +
                ", confused=" + confused +
                ", heart=" + heart +
                ", rocket=" + rocket +
                ", eyes=" + eyes +
                '}';
    }
}
