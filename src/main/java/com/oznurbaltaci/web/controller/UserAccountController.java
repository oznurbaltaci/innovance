package com.oznurbaltaci.web.controller;

import com.oznurbaltaci.model.TokenModel;
import com.oznurbaltaci.model.request.LoginRequest;
import com.oznurbaltaci.security.JwtGenerator;
import com.oznurbaltaci.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userAccount")
public class UserAccountController {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation("Kullanıcı giriş işlemini gerçekleştirir")
    public ResponseEntity<TokenModel> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
