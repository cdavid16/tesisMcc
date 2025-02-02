package org.project.mcc.enums;

public enum CommentTypeEnum {

    BEST_PRACTICE("Best Practice", "This comment is related when you don't follow the best engineering practices"),
    BUSINESS_DOMAIN("Business Domain", "This is when you are not following business related logic"),
    SUGGESTION("Suggestion", "This is only a suggested change, but not mandatory"),
    DOCUMENTATION("Documentation", "This change is related when you are not following the documentation"),
    QUESTION("Question", "This is related with a question, not a change per se"),
    CODE_STYLE("Code Style", "This type of comment is related with the code style within the team"),
    NOT_DEFINED("Not defined", "Not well defined")
    ;

    private final String commentTypeAsText;
    private final String meaning;


    CommentTypeEnum(String commentTypeAsText, String meaning) {
        this.commentTypeAsText = commentTypeAsText;
        this.meaning = meaning;
    }

}
