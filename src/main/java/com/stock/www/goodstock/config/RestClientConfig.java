package com.stock.www.goodstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                //.messageConverters(converters -> converters.add(new MyCustomMessageConverter()))
                //.baseUrl("https://example.com")
                .defaultUriVariables(Map.of("variable", "foo"))
                .defaultHeader("My-Header", "Foo")
                //.requestInterceptor(myCustomInterceptor)
                //.requestInitializer(myCustomInitializer)
                .build();
    }
}
