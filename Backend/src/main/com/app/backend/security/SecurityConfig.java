package com.app.backend.security;

import org.springframework.beans.factory.annotation.Autowaried;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.autheticationManager;
import org.springframework.security.authetication.dao. DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuratio.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpsSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService CustomUserDetailsService;

    @Autowired
    private JwtAuthenticationFiler jwtAuthenticationFiler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    } 
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    } 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .cors(cors  -> cors.configurationSource(CorsConfigurationSource()))
            .cors(csrf -> csrf.disable())
            .sessionManagement(session -> session.SessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth 
            .requestMatchers(...patterns: "/api/auth/**").
            permitAll()
            .anyRequest().autheticated());

            http.authenticationProvider(authenticationProvider());
            http.addFilterBefore(jwtAuthenticationFiler, beforeFilter: UsernamePasswordAuthenticationFilter.class);
            
            return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(...a: "*"));
        configuration.setAllowedMethods(Arrays.asList(...a:"GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(...a:"*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}

