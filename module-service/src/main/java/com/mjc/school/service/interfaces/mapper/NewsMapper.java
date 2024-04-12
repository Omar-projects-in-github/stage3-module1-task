package com.mjc.school.service.interfaces.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTO;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO newsToNewsDTO(News news);

    News newsDTOtoNews(NewsDTO newsDTO);
}