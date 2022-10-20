package com.example.craftbeerbartmsproject.config;

import com.example.craftbeerbartmsproject.controller.handler.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsService userDetailsService;

    private final AccessDeniedException accessDeniedException;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
                          UserDetailsService userDetailsService, AccessDeniedException accessDeniedException) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userDetailsService = userDetailsService;
        this.accessDeniedException = accessDeniedException;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(
                        request -> request.antMatchers(
                                        "/", "/shop", "/registration",
                                        "/shop/*", "/about_us", "/news", "/news/*").permitAll()
                                .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll().deleteCookies("JSESSIONID"))
                .exceptionHandling().accessDeniedHandler(accessDeniedException);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
