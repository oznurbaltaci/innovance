package com.oznurbaltaci.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("singleton")
public class JwtClaimsModel {
    private Long userId;
    private Long identityNumber;
}