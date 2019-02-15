package com.finder.itis.service;

import com.finder.itis.dto.LibraryInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Test
    public void test(){
        LibraryInfoDto infoDto = libraryService.findDtoById(1L);
        System.out.println();
    }
}