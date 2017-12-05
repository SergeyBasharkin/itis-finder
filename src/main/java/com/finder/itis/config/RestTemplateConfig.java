package com.finder.itis.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class RestTemplateConfig {

    @Value("${github.email}")
    private String email;

    @Value("${github.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(2000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        restTemplate.getMessageConverters()
                .add(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }


    @Bean(name = "githubHeaders")
    public HttpHeaders setHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        String plainCreds = email + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        httpHeaders.add("Accept", "application/vnd.github.v3+json");
        httpHeaders.set("Authorization", "Basic " + base64Creds);
        return httpHeaders;
    }
}
