package com.finder.itis.controllers;

import com.finder.itis.dto.Blog;
import com.finder.itis.dto.LibraryItemDto;
import com.finder.itis.service.LibraryService;
import com.github.javafaker.Faker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class SearchController {

    private final LibraryService libraryService;

    public SearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping({"/"})
    public String main(Model model, Pageable pageable) {
        Faker faker = new Faker();
        List<LibraryItemDto> libraryItemDtos = libraryService.findAllDto(pageable);
        for (LibraryItemDto libraryItemDto : libraryItemDtos) {
            List<Blog> blogs = new ArrayList<>();
            for (int i = 0; i <faker.random().nextInt(1, 10); i++) {
                Blog blog = new Blog();
                blog.setImg(faker.avatar().image());
                blog.setName(faker.app().name());
                blogs.add(blog);
            }
            libraryItemDto.setBlogs(blogs);
        }
//        ArrayList<LibraryItemDto> libraries = new ArrayList<>();
//        for (int i = 0; i <20 ; i++) {
//            LibraryItemDto libraryItemDto = new LibraryItemDto();
//            libraryItemDto.setArtifactId(faker.app().name());
//            libraryItemDto.setDescription(faker.lorem().sentence());
//            libraryItemDto.setId((long) i);
//            libraryItemDto.setUsage((long) faker.number().numberBetween(10000,50000));
//            ArrayList<Blog> blogs = new ArrayList<>();
//            for (int j = 0; j < 5; j++) {
//                Blog blog = new Blog();
//                blog.setImg(faker.avatar().image());
//                blog.setName(faker.app().name());
//            }
//            libraryItemDto.setBlogs(blogs);
//        }

        model.addAttribute("libraries",libraryItemDtos);

        return "search";
    }

    @GetMapping({"/library/{id}"})
    public String profile(Model model, @PathVariable("id") Long id) {
        model.addAttribute("library",libraryService.findDtoById(id));
        return "details";
    }
}

