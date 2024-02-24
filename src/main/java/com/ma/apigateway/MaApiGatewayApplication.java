package com.ma.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaApiGatewayApplication.class, args);
    }

}
