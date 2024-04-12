package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final String AUTHOR_FILE_PATH = "src/main/resources/author.txt";
    private static final String NEWS_FILE_PATH = "src/main/resources/content.txt";

    public List<Author> loadAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(AUTHOR_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Long id = Long.parseLong(parts[0]);
                    String name = parts[1];
                    authors.add(new Author(id, name));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return authors;
    }

    public List<News> loadNews(List<Author> authors) {
        List<News> newsList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(NEWS_FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Long id = Long.parseLong(parts[0]);
                    String title = parts[1];
                    String content = parts[2];
                    LocalDateTime createDate = LocalDateTime.parse(parts[3],
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    LocalDateTime lastUpdateTime = LocalDateTime.parse(parts[4],
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    Long authorId = Long.parseLong(parts[5]);
                    Author author = findAuthorById(authors, authorId);
                    if (author != null)
                        newsList.add(new News(id, title, content, createDate, lastUpdateTime, authorId));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newsList;
    }

    public Author findAuthorById(List<Author> authors, Long id) {
        for (Author author : authors) {
            if (author.getId().equals(id))
                return author;
        }
        return null;
    }
}