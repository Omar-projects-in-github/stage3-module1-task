package com.mjc.school.service.interfaces;

import com.mjc.school.service.exception.ServiceException;

import java.util.List;

public interface NewsService<T> {
    T createNews(T model) throws ServiceException;

    T readByIdNews(Long id) throws ServiceException;

    List<T> readAllNews();

    T updateNews(T model) throws ServiceException;

    Boolean deleteNews(Long id) throws ServiceException;
}
