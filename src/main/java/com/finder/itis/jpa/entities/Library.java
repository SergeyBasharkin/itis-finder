package com.finder.itis.jpa.entities;

import com.finder.itis.jpa.entities.parents.IdentityEntity;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"groupId", "artifactId"})})
@Entity
@Data
@EqualsAndHashCode(callSuper = false, of = {"groupId","artifactId"})
@NoArgsConstructor
public class Library extends IdentityEntity {

    private String groupId;

    private String artifactId;

    private String version;

    private String classifier;

    private String scope;


    @ManyToMany(mappedBy = "libraries")
    private Set<GitRepository> gitRepositories = new HashSet<>();

    public Library(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }
}



