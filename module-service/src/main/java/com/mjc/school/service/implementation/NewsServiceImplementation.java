package com.mjc.school.service.implementation;

import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.NewsRepositoryImplementation;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ArgumentValidException;
import com.mjc.school.service.exception.ErrorMeanings;
import com.mjc.school.service.exception.IDNotFoundException;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.interfaces.NewsService;
import com.mjc.school.service.interfaces.mapper.NewsMapper;
import com.mjc.school.service.validator.NewsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NewsServiceImplementation implements NewsService<NewsDTO> {
    private final NewsRepository<NewsModel> newsRepository;
    private final NewsValidator newsValidator = NewsValidator.getInstance();

    public NewsServiceImplementation() {
        newsRepository = new NewsRepositoryImplementation();
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) throws ServiceException {
        try {
            newsValidator.checkDTO(newsDTO);
            NewsModel createNewsModel = newsRepository.create(NewsMapper.INSTANCE.newsDTOtoNews(newsDTO));
            return NewsMapper.INSTANCE.newsToNewsDTO(createNewsModel);
        } catch (ArgumentValidException | IDNotFoundException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public NewsDTO readByIdNews(Long id) throws ServiceException {
        try {
            isNewsExist(id);
            NewsModel newsModel = newsRepository.readById(id);
            return NewsMapper.INSTANCE.newsToNewsDTO(newsModel);
        } catch (ArgumentValidException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public List<NewsDTO> readAllNews() {
        List<NewsDTO> news = new ArrayList<>();
        for(NewsModel element : newsRepository.readAll()) {
            news.add(NewsMapper.INSTANCE.newsToNewsDTO(element));
        }
        return news;
    }

    private void isNewsExist(Long id) throws ServiceException {
        Optional<NewsModel> newsModel = NewsDataSource.getDataSource().getNewsList()
                .stream()
                .filter(el -> Objects.equals(el.getId(), id))
                .findFirst();
        if(newsModel.isEmpty()) {
            throw new ServiceException(String.format(
                    ErrorMeanings.NEWS_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) throws ServiceException{
        try {
            newsValidator.checkDTO(newsDTO);
            isNewsExist(newsDTO.getId());
            NewsModel updateNewsModel = newsRepository.update(NewsMapper.INSTANCE.newsDTOtoNews(newsDTO));
            return NewsMapper.INSTANCE.newsToNewsDTO(updateNewsModel);
        } catch (ArgumentValidException | IDNotFoundException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteNews(Long id) throws ServiceException {
        try {
            isNewsExist(id);
            newsRepository.delete(id);
            return true;
        } catch (ArgumentValidException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }
}
