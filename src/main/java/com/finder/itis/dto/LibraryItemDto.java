package com.finder.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LibraryItemDto {
    private Long id;
    private String artifactId;
    private Long usage;
    private String description;
    private List<Blog> blogs;
}
