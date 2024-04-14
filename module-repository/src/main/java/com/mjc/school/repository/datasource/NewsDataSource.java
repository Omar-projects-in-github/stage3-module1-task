package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.util.FileUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsDataSource {
    private static final NewsDataSource dataSource = new NewsDataSource();
    private List<NewsModel> newsModels;
    private final List<AuthorModel> authorModelList = AuthorDataSource.getInstance().getAuthors();
    private final String NEWS_FILE = "news.txt";
    private final String CONTENT_FILE = "content.txt";

    private NewsDataSource() {
        this.newsModels = loadNews(authorModelList);
    }

    private List<NewsModel> loadNews(List<AuthorModel> authorModelList) {
        List<NewsModel> newsModels = new ArrayList<>();
        List<String> titles = FileUtil.readFile(NEWS_FILE);
        List<String> contents = FileUtil.readFile(CONTENT_FILE);
        int title_size = titles.size();
        for (int i=0; i<contents.size(); i++){
            long random_number = new Random().nextLong(20);
            newsModels.add(new NewsModel((long) i,
                            titles.get(i % title_size),
                            contents.get(i),
                            LocalDateTime.now().minusDays(random_number).withNano(0),
                            LocalDateTime.now().minusDays(random_number).withNano(0),
                            authorModelList.get((int) random_number).getId()
                    )
            );
        }

        return newsModels;
    }

    public static NewsDataSource getDataSource(){
        return dataSource;
    }

    public List<NewsModel> getNewsList() {
        return newsModels;
    }
}
