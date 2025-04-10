package com.example.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors().and()
        .csrf((csrf) -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/create-ad", "/update-ad").hasRole("TEACHER")
        .requestMatchers(HttpMethod.DELETE, "/ad/{id}").hasRole("TEACHER")
        .requestMatchers("/post-feedback").hasRole("STUDENT")
        .requestMatchers("/update-info","/update-password").hasAnyRole("TEACHER","STUDENT")
        .anyRequest().permitAll())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .headers((headers) -> headers.frameOptions().sameOrigin());

        return http.build();
    }

    /*
     * create ad, update ad, delete ad são privadas (somente TEACHER)
     * post feedback exclusivo de STUDENT
     * update info e update password são comuns aos STUDENTS E TEACHERS
     * o resto é público
     */

}
