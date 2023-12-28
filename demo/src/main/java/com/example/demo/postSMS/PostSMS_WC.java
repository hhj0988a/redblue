package com.example.demo.postSMS;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import reactor.core.publisher.Mono;

public class PostSMS_WC {
    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl("http://{hostname}")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public PostSMS_WC sendPostSMS_WC(String token){ 
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("title", "SMS Title 샘플");
        json.put("SMS Title 샘플", "안녕하세요! SMS 샘플 테스트입니다.");
        json.put("targetPhoneNumber", "targetPhoneNumber");

        return buildWebClient().post()
            .uri("/api/sendSMS")
            .header("Authorization", "Bearer " + token)
            .body(Mono.just(json.toString()), JSONObject.class)
            .retrieve()
            .bodyToMono(PostSMS_WC.class)
            .block();
    }
}

//rest template와 web client의 차이점은 rest template는 동기식이기 때문에 단순한 요청 처리에 효과적이지만 대량의 요청의 경우 성능이슈가 발생할 수 있으며
//web client는 비동기 식이기 때문에 대량의 요청에도 수월하게 처리가 가능하지만 비동기 처리개념을 익혀야 할 수 있습니다. 
