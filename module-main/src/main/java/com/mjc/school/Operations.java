package com.mjc.school;

import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ErrorMeanings;
import com.mjc.school.service.exception.ValidatorException;

import java.util.Scanner;

public class Operations {
    private final NewsController controller = new NewsController();

    public void getAllNews() {
        controller.readAll().forEach(System.out::println);
    }

    public void getNewsById() {
        System.out.println("Enter news id:");
        Long id = readNumber("News Id");
        NewsDto dto = controller.readById(id);
        System.out.println(dto);
    }

    public void addNews() {
        NewsDto requestDTO = readValues(null);
        System.out.println(controller.create(requestDTO));
    }

    public void updateNews() {
        System.out.println("Enter news id:");
        Long id = readNumber("News Id");
        NewsDto requestDTO = readValues(id);
        System.out.println(controller.update(requestDTO));
    }

    public void deleteNewsById() {
        System.out.println("Enter news id:");
        Long id = readNumber("News Id");
        System.out.println(controller.delete(id));
    }



    private Long readNumber(String type){
        Scanner scr = new Scanner(System.in);
        try {
            return scr.nextLong();
        } catch (Exception e){
            throw new ValidatorException(String.format(ErrorMeanings.CHECK_SHOULD_BE_NUMBER.getMessage(), type));
        }
    }

    private NewsDto readValues(Long id) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter news title:");
        String title = scr.nextLine();
        System.out.println("Enter news content:");
        String content = scr.nextLine();
        System.out.println("Enter author id:");
        Long authorId = readNumber("Author Id");
        return new NewsDto(title, content, authorId);
    }
}
