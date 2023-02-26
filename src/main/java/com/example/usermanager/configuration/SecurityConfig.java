package com.example.usermanager.configuration;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.ParseException;
import com.okta.spring.boot.oauth.Okta;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.USER;

//import static org.springframework.security.oauth2.jwt.NimbusJwtDecoder.*;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //Change to http.authorizeHttpRequests
        http.authorizeRequests(configurer ->
                        configurer
                                .requestMatchers("/getSetting**")
                                .authenticated()
                                .requestMatchers("/setSetting**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();
        // add CORS filters
        http.cors();

        // add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        Okta.configureResourceServer401ResponseBody(http);
        http.csrf().disable();
        return http.build();

    }
}
