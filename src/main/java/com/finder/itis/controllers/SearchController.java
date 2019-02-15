package com.finder.itis.controllers;

import com.finder.itis.jpa.entities.Library;
import com.finder.itis.service.LibraryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class SearchController {

    private final LibraryService libraryService;

    public SearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping({"/"})
    public String main(Model model) {
        model.addAttribute("libraries",libraryService.findAll(PageRequest.of(0,20)));
        return "search";
    }

    @GetMapping({"/"})
    public String profile() {
        return "redirect:/";
    }
}

