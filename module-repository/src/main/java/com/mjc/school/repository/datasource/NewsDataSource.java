package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.News;
import com.mjc.school.repository.util.FileUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsDataSource {
    private static final NewsDataSource INSTANCE = new NewsDataSource();
    private List<News> news;
    private final String NEWS_FILE = "news.txt";

    public NewsDataSource() {
        try {
            this.news = generateNews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<News> generateNews() throws IOException {
        List<News> newsList = new ArrayList<>();
        List<String> newsLines = FileUtil.readFile(NEWS_FILE);
        for (String line : newsLines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                long id = Long.parseLong(parts[0]);
                String title = parts[1];
                String content = parts[2];
                LocalDateTime createDate = LocalDateTime.parse(parts[3]);
                LocalDateTime lastUpdateDate = LocalDateTime.parse(parts[4]);
                long authorId = Long.parseLong(parts[5]);
                newsList.add(new News(id, title, content, createDate, lastUpdateDate, authorId));
            }
        }
        return newsList;
    }

    public List<News> getNews() {
        return news;
    }

    public static NewsDataSource getInstance(){
        return INSTANCE;
    }
}
