package com.oznurbaltaci.security;

import com.oznurbaltaci.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class JwtValidator {

    private String secret = "BuYrUn_@2021";

    public User validate(String token){
        Claims body = Jwts.parser()
                .setSigningKey(secret.getBytes(Charset.forName("UTF-8")))
                .parseClaimsJws(token)
                .getBody();

        User user = new User();

        user.setIdentityNumber((Long) body.get("identityNumber"));
        user.setUserId((Long) body.get("userId"));

        return user;


    }

}










