package com.mjc.school.service.interfaces.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.interfaces.ModelMapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-15T00:27:10+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class ModelMapperImpl implements ModelMapper {

    @Override
    public NewsDto newsToNewsDTO(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }

        NewsDto newsDto = new NewsDto();

        newsDto.setId( newsModel.getId() );
        newsDto.setTitle( newsModel.getTitle() );
        newsDto.setContent( newsModel.getContent() );
        newsDto.setAuthorId( newsModel.getAuthorId() );
        newsDto.setCreatedDate( newsModel.getCreatedDate() );
        newsDto.setLastUpdatedDate( newsModel.getLastUpdatedDate() );

        return newsDto;
    }

    @Override
    public NewsModel newsDTOtoNews(NewsDto newsDTO) {
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
