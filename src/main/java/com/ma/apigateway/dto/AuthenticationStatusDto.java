package com.ma.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationStatusDto {
    private Boolean isAuthenticated;
    private String message;
}
