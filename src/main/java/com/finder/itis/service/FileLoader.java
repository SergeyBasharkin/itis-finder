package com.finder.itis.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.finder.itis.jpa.entities.Library;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FileLoader {

    private final LibraryService libraryService;
    public Set<Library> load(ObjectNode file) {
        String xml = new String(Base64.decodeBase64(file.get("content").asText()));
        return libraryService.parseXml(xml);
    }
}
