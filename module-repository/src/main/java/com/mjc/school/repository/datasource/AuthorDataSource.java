package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDataSource {
    private static final AuthorDataSource INSTANCE = new AuthorDataSource();
    private List<AuthorModel> authorModels;
    private final String AUTHORS_FILE = "author.txt";

    public AuthorDataSource() {
        try {
            this.authorModels = generateAuthors();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private List<AuthorModel> generateAuthors() throws IOException {
        List<AuthorModel> authorModels = new ArrayList<>();
        List<String> authorLines = FileUtil.readFile(AUTHORS_FILE);
        for (String line : authorLines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                Long id = Long.parseLong(parts[0]);
                String name = parts[1];
                authorModels.add(new AuthorModel(id, name));
            }
        }
        return authorModels;
    }

    public List<AuthorModel> getAuthors() {
        return authorModels;
    }

    public static AuthorDataSource getInstance() {
        return INSTANCE;
    }
}
