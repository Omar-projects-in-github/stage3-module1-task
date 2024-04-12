package com.mjc.school.service.exception;

public enum ErrorMeanings {
    AUTHOR_ID_NOT_EXIST("Author with id %d does not exist", "1"),
    NEWS_NOT_EXIST("News with id %d does not exist", "2"),
    TITLE_LENGTH_REQUIREMENT("News title can not be less than %d and more than %d letters. " +
            "News title is %s", "3"),
    CONTENT_LENGTH_REQUIREMENT("News content can not be less than %d and more than %d letters." +
            "News content is %s", "4"),
    CHECK_SHOULD_BE_NUMBER("%s should be number", "5");

    private final String codeMessage;
    private final String codeId;

    ErrorMeanings(String codeMessage, String codeId) {
        this.codeMessage = codeMessage;
        this.codeId = codeId;
    }

    public String getMessage() {
        return "Error meaning: " + codeMessage + "\nCode error identifier: " + codeId;
    }
}
