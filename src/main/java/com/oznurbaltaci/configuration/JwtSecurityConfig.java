package com.oznurbaltaci.configuration;


;
import com.oznurbaltaci.security.JWTAuthorizationFilter;
import com.oznurbaltaci.security.JwtClaimsModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtClaimsModel jwtClaimsModel;

    public JwtSecurityConfig(JwtClaimsModel jwtClaimsModel) {
        this.jwtClaimsModel = jwtClaimsModel;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v3/api-docs", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/userAccount/login", "/user/create").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtClaimsModel));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/csrf/*", "/v2/api-docs", "/configuration/ui", "/swagger-resources/", "/configuration/", "/swagger-ui.html", "/webjars/*");

    }
}




