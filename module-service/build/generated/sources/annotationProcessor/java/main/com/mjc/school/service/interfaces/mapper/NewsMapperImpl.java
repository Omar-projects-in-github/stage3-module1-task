package com.mjc.school.service.interfaces.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTO;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-15T00:10:27+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTO newsToNewsDTO(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsDTO newsDTO = new NewsDTO();

        newsDTO.setId( newsModel.getId() );
        newsDTO.setTitle( newsModel.getTitle() );
        newsDTO.setContent( newsModel.getContent() );
        newsDTO.setAuthorId( newsModel.getAuthorId() );
        newsDTO.setCreatedDate( newsModel.getCreatedDate() );
        newsDTO.setLastUpdatedDate( newsModel.getLastUpdatedDate() );

        return newsDTO;
    }

    @Override
    public NewsModel newsDTOtoNews(NewsDTO newsDTO) {
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

        NewsModel newsModel = new NewsModel( id, title, content, createdDate, lastUpdatedDate, authorId );

        return newsModel;
    }
}
