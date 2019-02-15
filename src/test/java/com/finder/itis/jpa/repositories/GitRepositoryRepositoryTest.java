package com.finder.itis.jpa.repositories;

import com.finder.itis.jpa.entities.Library;
import com.finder.itis.service.LibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepositoryRepositoryTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private GitRepositoryRepository gitRepositoryRepository;

    @Test
    public void test(){
        List<Library> libraries = libraryService.findAll(PageRequest.of(0, 20));
        Long count = gitRepositoryRepository.countByLibrariesIn(libraries);
        System.out.println();
    }
}