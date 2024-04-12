package com.mjc.school.service.validator;

import com.mjc.school.service.constants.ServiceConstants;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorMeanings;
import com.mjc.school.service.exception.IDNotFoundException;
import com.mjc.school.service.exception.ValidatorException;

public class NewsValidator {
    private static NewsValidator instance;

    private NewsValidator() {}

    public static NewsValidator getInstance() {
        if (instance == null)
            instance = new NewsValidator();
        return instance;
    }

    public void checkDTO(NewsDTO dto) {
        int titleLength = dto.getTitle().length();
        int contentLength = dto.getContent().length();
        Long authorId = dto.getAuthorId();
        if (titleLength < ServiceConstants.MIN_TITLE_LENGTH ||
        titleLength > ServiceConstants.MAX_TITLE_LENGTH)
            throw new ValidatorException(String.format(
                    ErrorMeanings.TITLE_LENGTH_REQUIREMENT.getMessage(), ServiceConstants.MIN_TITLE_LENGTH,
                    ServiceConstants.MAX_TITLE_LENGTH, dto.getTitle()
            ));
        if (contentLength < ServiceConstants.MIN_CONTENT_LENGTH ||
        contentLength > ServiceConstants.MAX_CONTENT_LENGTH)
            throw new ValidatorException(String.format(
                    ErrorMeanings.CONTENT_LENGTH_REQUIREMENT.getMessage(), ServiceConstants.MIN_CONTENT_LENGTH,
                    ServiceConstants.MAX_CONTENT_LENGTH, dto.getContent()
            ));
        if (authorId < 0 || authorId > ServiceConstants.MAX_AUTHOR_ID)
            throw new IDNotFoundException(String.format(
                    ErrorMeanings.AUTHOR_ID_NOT_EXIST.getMessage(), authorId)
            );
    }
}
