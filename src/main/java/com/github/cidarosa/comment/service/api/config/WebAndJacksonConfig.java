package com.github.cidarosa.comment.service.api.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO )
public class WebAndJacksonConfig {

    @Bean
    JsonMapperBuilderCustomizer jacksonCustomizer(){
        return builder -> builder
                // Mantém o seu indentado (bonito no Postman)
                .enable(SerializationFeature.INDENT_OUTPUT)
                // Tenta desativar a ordenação alfabética forçada
                .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }
}
