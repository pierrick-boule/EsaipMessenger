//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package org.esaip.messenger.rest;

import java.util.Collections;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClient_
    implements RestClient
{

    private RestTemplate restTemplate;
    private String rootUrl;

    public RestClient_() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        rootUrl = "http://parlezvous.herokuapp.com";
    }

    @Override
    public String getMessages(String username, String password) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/messages/{username}/{password}"), HttpMethod.GET, requestEntity, String.class, urlVariables).getBody();
    }

    @Override
    public String postMessage(String username, String password, String message) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);
        urlVariables.put("message", message);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/message/{username}/{password}/{message}"), HttpMethod.GET, requestEntity, String.class, urlVariables).getBody();
    }

    @Override
    public String login(String username, String password) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/connect/{username}/{password}"), HttpMethod.GET, requestEntity, String.class, urlVariables).getBody();
    }

    @Override
    public ResponseEntity<String> register(String username, String password) {
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/xhtml+xml")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        return restTemplate.exchange(rootUrl.concat("/subscribe?username={username}&password={password}"), HttpMethod.POST, requestEntity, String.class, urlVariables);
    }

}
