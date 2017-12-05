package com.finder.itis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.finder.itis.jpa.entities.Library;
import com.finder.itis.jpa.repositories.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

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

            // iterate the employees
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                String groupId = getCharacterDataFromElement((Element) element.getElementsByTagName("groupId").item(0));
                String artifactId = getCharacterDataFromElement((Element) element.getElementsByTagName("artifactId").item(0));
                if (groupId != null && artifactId != null) {
                    Library  library = libraryRepository.findByGroupIdAndArtifactId(groupId, artifactId);
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
        return libraryRepository.findAllByGroupIdLikeOrArtifactIdLike(s,s);
    }
}
