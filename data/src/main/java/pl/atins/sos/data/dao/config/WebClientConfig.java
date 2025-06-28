package pl.atins.sos.data.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Configuration
public class WebClientConfig {

    private final String libraryServiceUrl;
    private final String paymentServiceUrl;

    public WebClientConfig(
            @Value("${library-service.url}") String libraryServiceUrl,
            @Value("${payment-service.url}") String paymentServiceUrl) {
        this.libraryServiceUrl = Objects.requireNonNull(libraryServiceUrl);
        this.paymentServiceUrl = Objects.requireNonNull(paymentServiceUrl);
    }

    @Bean
    WebClient libraryServiceWebClient() {
        return WebClient.builder()
                .baseUrl(libraryServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    WebClient paymentServiceWebClient() {
        return WebClient.builder()
                .baseUrl(paymentServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
