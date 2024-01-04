package com.hospital.hospital.Security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import Utils.CustomExceptionHandler;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
@Bean
    SecurityFilterChain filterChain (HttpSecurity http, AuthenticationManager authmanager) throws Exception {
    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
    jwtAuthenticationFilter.setAuthenticationManager(authmanager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");
    CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
    return http.csrf().disable()
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(new AntPathRequestMatcher("/hello")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll();
                auth.requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll();
                auth.anyRequest().authenticated();})
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, ex) -> { customExceptionHandler.handleException(ex,response); }))// Usamos el manejador personalizado
            .build();


}


@Bean
AuthenticationManager authenticationManager (HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception
{
 return http.getSharedObject(AuthenticationManagerBuilder.class)
         .userDetailsService(userDetailsService)
         .passwordEncoder(passwordEncoder())
         .and()
         .build();
}
@Bean
PasswordEncoder passwordEncoder()
{
return new BCryptPasswordEncoder();
}



}
