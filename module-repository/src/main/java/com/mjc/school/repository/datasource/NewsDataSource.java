package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.util.FileUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsDataSource {
    private static final NewsDataSource INSTANCE = new NewsDataSource();
    private List<News> news;
    private final List<Author> authorList = AuthorDataSource.getInstance().getAuthors();
    private final String NEWS_FILE = "news.txt";
    private final String CONTENT_FILE = "content.txt";

    private NewsDataSource() {
        this.news = loadNews(authorList);
    }

    private List<News> loadNews(List<Author> authorList) {
        List<News> news = new ArrayList<>();
        List<String> titles = FileUtil.readFile(NEWS_FILE);
        List<String> contents = FileUtil.readFile(CONTENT_FILE);
        int title_size = titles.size();
        for (int i=0; i<contents.size(); i++){
            long random_number = new Random().nextLong(20);
            news.add(new News((long) i,
                            titles.get(i % title_size),
                            contents.get(i),
                            LocalDateTime.now().minusDays(random_number).withNano(0),
                            LocalDateTime.now().minusDays(random_number).withNano(0),
                            authorList.get((int) random_number).getId()
                    )
            );
        }

        return news;
    }

    public static NewsDataSource getInstance(){
        return INSTANCE;
    }

    public List<News> getNewsList() {
        return news;
    }
}
