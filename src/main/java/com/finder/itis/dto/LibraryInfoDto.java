package com.finder.itis.dto;

import com.finder.itis.jpa.entities.GitRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryInfoDto extends LibraryItemDto{
    List<String> tags;
    List<Articles> articles;
    List<GitRepository> projects;
}
