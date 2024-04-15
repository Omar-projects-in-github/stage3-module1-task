package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;

import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.util.Utils.getRandomContentByFilePath;

public class AuthorData {
    private static final String AUTHORS_FILE_NAME = "authors";
    private static AuthorData authorData;
    private List<AuthorModel> authorList;

    private AuthorData() {
        init();
    }

    public static AuthorData getAuthorData() {
        if (authorData == null) {
            authorData = new AuthorData();
        }
        return authorData;
    }

    private void init() {
        authorList = new ArrayList<>();
        for (long i = 1; i <= 20; i++) {
            authorList.add(new AuthorModel(i, getRandomContentByFilePath(AUTHORS_FILE_NAME)));
        }
    }

    public List<AuthorModel> getAuthorList() {
        return authorList;
    }
}
