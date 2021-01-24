package ua.shareit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 15000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();
        clientFactory.setConnectTimeout(CONNECT_TIMEOUT);
        clientFactory.setReadTimeout(READ_TIMEOUT);
        return new RestTemplate(clientFactory);
    }
}
