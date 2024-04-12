package com.mjc.school.repository.interfaces;

import java.util.List;

public interface NewsRepository<T> {
    T create(T model);
    T readById(Long id);
    List<T> readAll();
    T update(T model);
    Boolean delete(Long id);
}
