package com.ma.apigateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.webflux.api.OpenApiResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info().title("MA API GATEWAY").version("v0.0.1").description("""
                Find explore \n
                MA USER SERVICE API List : /user/v3/api-docs \n
                MA ITEM SERVICE API List : /item/v3/api-docs \n
                """);

        return new OpenAPI().info(info);
    };

    // 타 micro-service 의 url을 서치 할 수 있도록 도와준다.
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
