package com.finder.itis.dto;

import lombok.Data;

@Data
public class Dependency {
    private String artifactId;
    private String groupId;
    private String classifier;
    private String scope;
    private String version;
}
