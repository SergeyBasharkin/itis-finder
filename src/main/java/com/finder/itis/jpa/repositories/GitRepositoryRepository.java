package com.finder.itis.jpa.repositories;

import com.finder.itis.jpa.entities.GitRepository;
import com.finder.itis.jpa.entities.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GitRepositoryRepository extends JpaRepository<GitRepository,Long> {
    Page<GitRepository> findAllByLibrariesIn(List<Library> libraries, Pageable pageable);

    Long countByLibrariesIn(List<Library> libraries);

    List<GitRepository> findAllByLibraries(List<Library> libraries);
}
