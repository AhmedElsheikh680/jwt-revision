package com.spring.jwt.jwtrevision.controller;


import com.spring.jwt.jwtrevision.jwt.JWTLogin;
import com.spring.jwt.jwtrevision.jwt.ResponseLogin;
import com.spring.jwt.jwtrevision.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    private TokenService tokenService;

    @Autowired
    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public ResponseLogin login(@RequestBody JWTLogin jwtLogin) throws Exception {
        return tokenService.successfulAuthentication(jwtLogin);
    }
}
