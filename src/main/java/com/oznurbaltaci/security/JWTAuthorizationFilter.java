package com.oznurbaltaci.security;



import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.oznurbaltaci.security.SecurityConstants.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JwtClaimsModel jwtClaimsModel;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JwtClaimsModel jwtClaimsModel) {
        super(authManager);
        this.jwtClaimsModel = jwtClaimsModel;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String token;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            SecurityContextHolder.getContext().setAuthentication(
                    validate(header.replace(TOKEN_PREFIX, "")));
            token = header.replace(TOKEN_PREFIX, "");

            if(!token.equals("null") && !token.equals("undefined"))
                SecurityContextHolder.getContext().setAuthentication(validate(token));
        }

        chain.doFilter(req, res);
    }


    public UsernamePasswordAuthenticationToken validate(String token) throws InnovanceServiceException {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            jwtClaimsModel.setUserId(Long.parseLong(String.valueOf(body.get("userId"))));
            jwtClaimsModel.setIdentityNumber((Long) body.get("identityNumber"));

            return new UsernamePasswordAuthenticationToken(body.getSubject(), null, new ArrayList<>());
        } catch (Exception e) {
            throw new InnovanceServiceException(ErrorCode.USER_ACCOUNT_NOT_FOUND);
        }
    }

}