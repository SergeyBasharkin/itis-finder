package com.finder.itis.service;

import com.finder.itis.jpa.entities.GitRepository;
import com.finder.itis.jpa.entities.Library;
import com.finder.itis.jpa.repositories.GitRepositoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryService {

    private final GitRepositoryRepository gitRepositoryRepository;

    public void save(GitRepository gitRepository){
        gitRepositoryRepository.save(gitRepository);
    }

    public Page<GitRepository> findAllProjectsByLibraries(List<Library> libraries, Pageable pageable) {
       return gitRepositoryRepository.findAllByLibrariesIn(libraries, pageable);
    }
}
