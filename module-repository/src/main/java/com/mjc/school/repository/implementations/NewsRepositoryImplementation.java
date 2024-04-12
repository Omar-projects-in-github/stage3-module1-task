package com.mjc.school.repository.implementations;

import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.model.News;

import java.util.List;

public class NewsRepositoryImplementation implements NewsRepository<News> {
    private List<News> newsData;

    public NewsRepositoryImplementation() {
        this.newsData = NewsDataSource.getInstance().getNews();
    }

    @Override
    public News create(News model) {
        Long maxId = newsData.stream().mapToLong(News::getId).max().orElse(0);
        model.setId(maxId + 1);
        newsData.add(model);
        return model;
    }

    @Override
    public News readById(Long id) {
        return newsData.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<News> readAll() {
        return newsData;
    }

    @Override
    public News update(News model) {
        News updatedModel = readById(model.getId());
        updatedModel.setTitle(model.getTitle());
        updatedModel.setContent(model.getContent());
        updatedModel.setLastUpdateDate(model.getLastUpdateDate());
        return updatedModel;
    }

    @Override
    public Boolean delete(Long id) {
        return newsData.removeIf(x -> x.getId().equals(id));
    }
}
