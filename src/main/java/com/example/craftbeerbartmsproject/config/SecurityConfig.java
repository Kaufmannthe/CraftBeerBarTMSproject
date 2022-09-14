package com.example.craftbeerbartmsproject.config;

import com.example.craftbeerbartmsproject.repository.UserRepository;
import com.example.craftbeerbartmsproject.service.impl.BackUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private final UserRepository userRepository;


    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                                        "/", "/shop/product/**", "/shop", "/registration",
                                        "/shop/**","/moderator/product_registration").permitAll()
                                .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout(LogoutConfigurer::permitAll)
        /*.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedException))*/;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new BackUserDetailsServiceImpl(userRepository, passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security,
                                                       UserDetailsService userDetailsService) throws Exception {
        return security.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder()).and().build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

}
