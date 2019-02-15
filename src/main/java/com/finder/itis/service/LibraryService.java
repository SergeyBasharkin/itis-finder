package com.finder.itis.service;

import com.finder.itis.dto.Articles;
import com.finder.itis.dto.Blog;
import com.finder.itis.dto.LibraryInfoDto;
import com.finder.itis.dto.LibraryItemDto;
import com.finder.itis.exceptions.ResourceNotFound;
import com.finder.itis.jpa.entities.Library;
import com.finder.itis.jpa.repositories.GitRepositoryRepository;
import com.finder.itis.jpa.repositories.LibraryRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final GitRepositoryRepository gitRepositoryRepository;

    public Set<Library> parseXml(String xml) {
        Set<Library> libraries = new LinkedHashSet<>();
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("dependency");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                String groupId = getCharacterDataFromElement((Element) element.getElementsByTagName("groupId").item(0));
                String artifactId = getCharacterDataFromElement((Element) element.getElementsByTagName("artifactId").item(0));
                if (groupId != null && artifactId != null) {
                    Library library = libraryRepository.findByGroupIdAndArtifactId(groupId, artifactId);
                    if (library == null) {
                        library = new Library(groupId, artifactId);
                    }
                    library.setClassifier(getCharacterDataFromElement((Element) element.getElementsByTagName("classifier").item(0)));
                    library.setScope(getCharacterDataFromElement((Element) element.getElementsByTagName("scope").item(0)));
                    library.setVersion(getCharacterDataFromElement((Element) element.getElementsByTagName("version").item(0)));
                    libraries.add(library);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libraries;
    }

    public static String getCharacterDataFromElement(Element e) {
        if (e == null) return null;
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    private Library save(Library library) {
        return libraryRepository.save(library);
    }

    public void saveAll(Set<Library> libraries) {
        libraries.forEach(library -> {
            if (!libraryRepository.exists(Example.of(new Library(library.getGroupId(), library.getArtifactId())))) {
                libraryRepository.save(library);
            }
        });
    }

    public List<Library> findByName(String s) {
        return libraryRepository.findAllByGroupIdLikeOrArtifactIdLike(s, s);
    }

    public List<Library> findAll(Pageable pageable) {
        return libraryRepository.findAll(pageable).getContent();
    }

    public List<LibraryItemDto> findAllDto(Pageable pageable) {
        List<Library> libraries = libraryRepository.findAll(pageable).getContent();

        return libraries.stream().map(library -> LibraryItemDto.builder()
                .id(library.getId())
                .artifactId(library.getArtifactId())
                .description(new Faker().gameOfThrones().quote())
                .usage(gitRepositoryRepository.countByLibrariesIn(Collections.singletonList(library)))
                .build()).collect(Collectors.toList());
    }

    public LibraryInfoDto findDtoById(Long id) {
        Library library = libraryRepository.findById(id).orElseThrow(ResourceNotFound::new);

        Faker faker = new Faker();
        LibraryInfoDto libraryInfoDto = new LibraryInfoDto();
        libraryInfoDto.setId(library.getId());
        libraryInfoDto.setUsage(gitRepositoryRepository.countByLibrariesIn(Collections.singletonList(library)));
        libraryInfoDto.setArtifactId(library.getArtifactId());
        libraryInfoDto.setDescription(faker.gameOfThrones().quote());
        List<String> tags = new ArrayList<>();
        for (int i = 0; i <faker.random().nextInt(1, 10); i++) {
            tags.add(faker.cat().name());
        }
        List<Articles> articles = new ArrayList<>();
        for (int i = 0; i <faker.random().nextInt(1, 10); i++) {
            Articles article = new Articles();
            article.setUrl(faker.avatar().image());
            article.setBody(faker.book().title());
            article.setTitle(faker.book().author());
            articles.add(article);
        }
        List<Blog> blogs = new ArrayList<>();
        for (int i = 0; i <faker.random().nextInt(1, 10); i++) {
            Blog blog = new Blog();
            blog.setImg(faker.avatar().image());
            blog.setName(faker.app().name());
            blogs.add(blog);
        }
        libraryInfoDto.setBlogs(blogs);
        libraryInfoDto.setArticles(articles);
        libraryInfoDto.setTags(tags);
        libraryInfoDto.setProjects(gitRepositoryRepository.findAllByLibraries(Collections.singletonList(library)));

        return libraryInfoDto;
    }
    //    public List<Library> search (String query) {
//        return libraryRepository.findAllByGroupIdLikeOrArtifactIdLike("%"+query+"%");
//    }
}
