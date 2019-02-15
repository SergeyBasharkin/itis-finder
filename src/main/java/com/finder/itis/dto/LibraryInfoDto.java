package com.finder.itis.dto;

import com.finder.itis.jpa.entities.GitRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class LibraryInfoDto extends LibraryItemDto{
    List<String> tags;
    List<String> articles;
    List<GitRepository> projects;
}
