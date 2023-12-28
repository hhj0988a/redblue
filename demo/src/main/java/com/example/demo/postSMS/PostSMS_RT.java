package com.example.demo.postSMS;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

public class PostSMS_RT {
    private static RestTemplate restTemplate; 
                
    private static String url 
    		= "https://{hostname}/api/sendSMS";

    private static UriComponents components 
                        = UriComponentsBuilder.fromUriString(url)
                            .build();

    static {
        HttpComponentsClientHttpRequestFactory factory 
        				= new HttpComponentsClientHttpRequestFactory();
                        
        factory.setConnectTimeout(5000);

        restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters();
    }

    void postTest(String token) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + token);

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("title", "SMS Title 샘플");
        json.put("SMS Title 샘플", "안녕하세요! SMS 샘플 테스트입니다.");
        json.put("targetPhoneNumber", "targetPhoneNumber");

        HttpEntity<JsonNode> requestEntity = new HttpEntity<>(json,headers);
       
        ResponseEntity<Map<String, JsonNode>> result 
            = restTemplate.exchange(components.toUri(),
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Map<String, JsonNode>>() {}
            );

    }
}

