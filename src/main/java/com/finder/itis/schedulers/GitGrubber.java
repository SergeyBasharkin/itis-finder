package com.finder.itis.schedulers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.finder.itis.constants.PackageManager;
import com.finder.itis.jpa.entities.GitRepository;
import com.finder.itis.jpa.entities.Library;
import com.finder.itis.service.FileLoader;
import com.finder.itis.service.LibraryService;
import com.finder.itis.service.RepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.finder.itis.constants.GitURL.url;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.springframework.http.HttpMethod.GET;

@Component
@AllArgsConstructor
@Log
public class GitGrubber {

    private final RestTemplate restTemplate;
    @Qualifier("githubHeaders")
    private final HttpHeaders httpHeaders;
    private final FileLoader fileLoader;
    private final LibraryService libraryService;

    private final RepositoryService repositoryService;

    @Scheduled(initialDelay = 2000,fixedDelay = Integer.MAX_VALUE)
    public void grab(){
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<ObjectNode> response = restTemplate.exchange(url + "/search/repositories?q=language:Java+stars:>20", GET, httpEntity, ObjectNode.class);
        ObjectNode entities = response.getBody();
        if (response.getHeaders().get("X-RateLimit-Remaining").get(0).equals("0")) return;
        ArrayNode arrayNode = (ArrayNode) entities.get("items");
        arrayNode.forEach(jsonNode -> {
            GitRepository gitRepository = new GitRepository();
            ResponseEntity<ArrayNode> filesResponse = restTemplate.exchange(url + "/repos/" + jsonNode.get("full_name").asText()+ "/contents", GET, httpEntity, ArrayNode.class);
            Set<Library> libraries = new HashSet<>();
            for (JsonNode jsonContent: filesResponse.getBody()) {
                if (jsonContent.get("name").asText().equals("pom.xml")){
                    gitRepository.setPackageManager(PackageManager.MAVEN);
                    libraries = fileLoader.load(restTemplate.exchange(jsonContent.get("git_url").asText(),GET,httpEntity,ObjectNode.class).getBody());
                    libraryService.saveAll(libraries);
                }
                if (jsonContent.get("name").asText().equals("build.gradle")){
                    gitRepository.setPackageManager(PackageManager.GRADLE);
                }
            }
            if (gitRepository.getPackageManager() != null) {
                gitRepository.setId(jsonNode.get("id").asLong());
                gitRepository.setName(jsonNode.get("name").asText());
                gitRepository.setFullName(jsonNode.get("full_name").asText());
                gitRepository.setDescription(jsonNode.get("description").asText());
                gitRepository.setLanguage(jsonNode.get("language").asText());
                gitRepository.setHasWiki(jsonNode.get("has_wiki").asBoolean(false));
                gitRepository.setHasPages(jsonNode.get("has_pages").asBoolean(false));
                gitRepository.setForks(jsonNode.get("forks_count").asInt(0));
                gitRepository.setUpdatedAt(LocalDateTime.parse(jsonNode.get("updated_at").asText(), ISO_DATE_TIME));
                gitRepository.setStars(jsonNode.get("stargazers_count").asText());
                gitRepository.setLibraries(libraries);
                repositoryService.save(gitRepository);
            }
        });
    }
}
