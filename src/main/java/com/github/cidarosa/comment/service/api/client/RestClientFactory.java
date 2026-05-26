package com.github.cidarosa.comment.service.api.client;

import com.github.cidarosa.comment.service.api.client.exceptions.ModerationClientBadGatewayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClient moderationRestClient() {

        return builder.baseUrl("http://localhost:8081")
                .requestFactory(generateClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw new ModerationClientBadGatewayException();
                })
                .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // existe uma conection - tempo de espera da resposta
        factory.setReadTimeout(Duration.ofSeconds(5));

        // não existe conection, tempo de espera para fazer uma conexão
        factory.setConnectTimeout(Duration.ofSeconds(3));
        return factory;
    }
}
