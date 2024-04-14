package com.mjc.school.repository.implementation;

import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public class NewsRepositoryImplementation implements NewsRepository<NewsModel> {
    private List<NewsModel> newsModelData;
    private static NewsDataSource datasource;

    public NewsRepositoryImplementation() {
        this.newsModelData = NewsDataSource.getDataSource().getNewsList();
    }

    @Override
    public NewsModel create(NewsModel model) {
        Long maxId = newsModelData.stream().mapToLong(NewsModel::getId).max().orElse(0);
        model.setId(maxId + 1);
        newsModelData.add(model);
        return model;
    }

    @Override
    public NewsModel readById(Long id) {
        return newsModelData.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public List<NewsModel> readAll() {
        return newsModelData;
    }

    @Override
    public NewsModel update(NewsModel model) {
        NewsModel updatedModel = readById(model.getId());
        updatedModel.setTitle(model.getTitle());
        updatedModel.setContent(model.getContent());
        updatedModel.setLastUpdatedDate(model.getLastUpdatedDate());
        return updatedModel;
    }

    @Override
    public Boolean delete(Long id) {
        return newsModelData.removeIf(x -> x.getId().equals(id));
    }
}
