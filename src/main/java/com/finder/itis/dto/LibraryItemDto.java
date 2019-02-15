package com.finder.itis.dto;

import lombok.Data;

import java.util.List;

@Data
public class LibraryItemDto {
    private Long id;
    private String artifactId;
    private Long usage;
    private String description;
    private List<Blog> blogs;
}
