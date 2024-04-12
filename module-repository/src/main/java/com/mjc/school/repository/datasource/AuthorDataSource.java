package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDataSource {
    private static final AuthorDataSource INSTANCE = new AuthorDataSource();
    private List<Author> authors;
    private final String AUTHORS_FILE = "author.txt";

    public AuthorDataSource() {
        try {
            this.authors = generateAuthors();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private List<Author> generateAuthors() throws IOException {
        List<Author> authors = new ArrayList<>();
        List<String> authorLines = FileUtil.readFile(AUTHORS_FILE);
        for (String line : authorLines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                Long id = Long.parseLong(parts[0]);
                String name = parts[1];
                authors.add(new Author(id, name));
            }
        }
        return authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public static AuthorDataSource getInstance() {
        return INSTANCE;
    }
}
