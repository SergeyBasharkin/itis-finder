package com.finder.itis;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.finder.itis.service.LibraryService;
import com.finder.itis.service.RepositoryService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.finder.itis.constants.GitURL.url;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItisFinderApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("githubHeaders")
    private HttpHeaders httpHeaders;

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private RepositoryService repositoryService;


    @Test
    public void contextLoads() {
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<ObjectNode> objectNode = restTemplate.exchange(url + "/search/repositories?q=language:Java", GET, httpEntity, ObjectNode.class);
        System.out.println(objectNode.getBody());
    }

    @Test
    public void testFileLoader() throws JAXBException, IOException, SAXException, ParserConfigurationException {
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<ObjectNode> filesResponse = restTemplate.exchange("https://api.github.com/repos/iluwatar/java-design-patterns/git/blobs/a60c8e7f994c7628d732daaa6841505d0ca17175", GET, httpEntity, ObjectNode.class);
        String xml = new String(Base64.decodeBase64(filesResponse.getBody().get("content").asText()));
        System.out.println(xml);
        libraryService.parseXml(xml);
    }


    @Test
    public void testLibrary(){
        repositoryService.findAllProjectsByLibraries(libraryService.findByName("org.hibernate"), PageRequest.of(0,20));
    }

}
