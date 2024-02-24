package com.ma.apigateway.controller;

import com.ma.apigateway.dto.AuthenticationStatusDto;
import com.ma.apigateway.dto.ErrorResponseDto;
import com.ma.apigateway.dto.JwtReqDto;
import com.ma.apigateway.dto.JwtResDto;
import com.ma.apigateway.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReqDto authenticationReq) throws Exception {
        AuthenticationStatusDto status = authenticate(authenticationReq.getUserName(), authenticationReq.getPassWord());

        if(!status.getIsAuthenticated()){
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED" , details);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        final String token  = jwtTokenUtil.generateToken(authenticationReq.getUserName());
        return ResponseEntity.ok(new JwtResDto(token));
    }

    private AuthenticationStatusDto authenticate(String userName, String passWord){
        AuthenticationStatusDto status;
        if(!userName.equals("foo") && !passWord.equals("foo")) {
            status = new AuthenticationStatusDto(false, "Invalid UserName/PassWord");

        }else{
            status = new AuthenticationStatusDto(true, "Authentication Successful");
        }
        return status;
    }

}
