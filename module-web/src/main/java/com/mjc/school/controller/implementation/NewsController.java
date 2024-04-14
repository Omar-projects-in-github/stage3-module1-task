package com.mjc.school.controller.implementation;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.implementation.NewsServiceImplementation;
import com.mjc.school.service.interfaces.NewsService;

import java.util.List;

public class NewsController implements Controller<NewsDto> {
    private final NewsService<NewsDto> newsService = new NewsServiceImplementation();

    @Override
    public NewsDto create(NewsDto model) throws ServiceException {
        return newsService.createNews(model);
    }

    @Override
    public NewsDto readById(Long id) throws ServiceException {
        return newsService.readByIdNews(id);
    }

    @Override
    public List<NewsDto> readAll() {
        return newsService.readAllNews();
    }

    @Override
    public NewsDto update(NewsDto model) throws ServiceException {
        return newsService.updateNews(model);
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        return newsService.deleteNews(id);
    }
}
