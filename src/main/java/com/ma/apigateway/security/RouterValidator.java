package com.ma.apigateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    //보호되는 엔드포인트의 목록을 가져오는데에 목적을 둔다.

    //열려있는 endpoint
    public static final List<String> openApiEndPoints = List.of(
            "/user/auth/register",
            "/user/auth/login",
            "/eureka",
            "/api-docs"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openApiEndPoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
