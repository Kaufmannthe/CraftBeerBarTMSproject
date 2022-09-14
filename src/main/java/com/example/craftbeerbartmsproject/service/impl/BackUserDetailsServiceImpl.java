package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BackUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public BackUserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .disabled(user.isActive())
                .authorities(user.getRole().stream().map(Enum::name).toArray(String[]::new)).build();
    }

}
