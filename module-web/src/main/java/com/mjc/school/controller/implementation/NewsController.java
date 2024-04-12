package com.mjc.school.controller.implementation;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.implementation.NewsServiceImplementation;
import com.mjc.school.service.interfaces.NewsService;

import java.util.List;

public class NewsController implements Controller<NewsDTO> {
    private final NewsService<NewsDTO> newsService = new NewsServiceImplementation();

    @Override
    public NewsDTO create(NewsDTO model) throws ServiceException {
        return newsService.createNews(model);
    }

    @Override
    public NewsDTO readById(Long id) throws ServiceException {
        return newsService.readByIdNews(id);
    }

    @Override
    public List<NewsDTO> readAll() {
        return newsService.readAllNews();
    }

    @Override
    public NewsDTO update(NewsDTO model) throws ServiceException {
        return newsService.updateNews(model);
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        return newsService.deleteNews(id);
    }
}
