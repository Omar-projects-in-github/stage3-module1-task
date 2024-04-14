package com.mjc.school.service.interfaces.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTO;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T23:36:11+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( news.getId() );
        newsDTO.setTitle( news.getTitle() );
        newsDTO.setContent( news.getContent() );
        newsDTO.setAuthorId( news.getAuthorId() );
        newsDTO.setCreatedDate( news.getCreatedDate() );
        newsDTO.setLastUpdatedDate( news.getLastUpdatedDate() );

        return newsDTO;
    }

    @Override
    public News newsDTOtoNews(NewsDTO newsDTO) {
        if ( newsDTO == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String content = null;
        LocalDateTime createdDate = null;
        LocalDateTime lastUpdatedDate = null;
        Long authorId = null;

        id = newsDTO.getId();
        title = newsDTO.getTitle();
        content = newsDTO.getContent();
        createdDate = newsDTO.getCreatedDate();
        lastUpdatedDate = newsDTO.getLastUpdatedDate();
        authorId = newsDTO.getAuthorId();

        News news = new News( id, title, content, createdDate, lastUpdatedDate, authorId );

        return news;
    }
}
