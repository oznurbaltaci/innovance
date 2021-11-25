package com.oznurbaltaci.security;



import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import com.oznurbaltaci.model.TokenModel;
import com.oznurbaltaci.persistence.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private String jwtSecret = "BuYrUn_@2021";

    public TokenModel generate(User user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getUserId().toString());

        claims.put("userId", user.getUserId());
        claims.put("name", user.getUserId());
        claims.put("surname", user.getUserId());
        claims.put("identityNumber", user.getIdentityNumber());

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes(Charset.forName("UTF-8")))
                .compact();

        return new TokenModel(token);
    }

    public String getTokenSubject(String token) throws InnovanceServiceException {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new InnovanceServiceException(ErrorCode.TOKEN_NOT_FOUND);
        }
    }


}
