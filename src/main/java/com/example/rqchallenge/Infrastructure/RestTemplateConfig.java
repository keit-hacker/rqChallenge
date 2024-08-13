package com.example.rqchallenge.Infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class RestTemplateConfig {

    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        UriTemplateHandler uriTemplateHandler = new RootUriTemplateHandler(BASE_URL);

        return builder
                .requestFactory(this::clientHttpRequestFactory)
                .uriTemplateHandler(uriTemplateHandler)
                .build();
    }

    private SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return factory;
    }
}