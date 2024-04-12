package com.mjc.school.controller.interfaces;

import com.mjc.school.service.exception.ServiceException;

import java.util.List;

public interface Controller<T> {
    T create(T entity) throws ServiceException;

    T readById(Long id) throws ServiceException;

    List<T> readAll();

    T update(T entity) throws ServiceException;

    Boolean delete(Long id) throws ServiceException;
}
