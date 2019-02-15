package com.finder.itis.jpa.entities;

import com.finder.itis.constants.PackageManager;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "git_repository")
@EntityListeners(AuditingEntityListener.class)
public class GitRepository{

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String fullName;

    private String  description;

    private String language;

    private boolean hasWiki;

    private boolean hasPages;

    private int forks;

    private LocalDateTime updatedAt;

    private String stars;

    @Enumerated(value = EnumType.STRING)
    private PackageManager packageManager;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lib_repository",
            joinColumns = { @JoinColumn(name = "repository_id") },
            inverseJoinColumns = { @JoinColumn(name = "library_id") }
    )
    private Set<Library> libraries = new HashSet<>();

}
