package com.finder.itis.jpa.repositories;

import com.finder.itis.jpa.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findByGroupIdAndArtifactId(String groupId, String artifactId);
    List<Library> findAllByGroupIdLikeOrArtifactIdLike(String group, String artifact);
}
