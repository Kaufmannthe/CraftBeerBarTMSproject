package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.CourierRepository;
import com.example.craftbeerbartmsproject.repository.ProducerRepository;
import com.example.craftbeerbartmsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProducerRepository producerRepository;

    private final CourierRepository courierRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, ProducerRepository producerRepository, CourierRepository courierRepository) {
        this.userRepository = userRepository;
        this.producerRepository = producerRepository;
        this.courierRepository = courierRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByLogin(username) == null
                && userRepository.findByEmail(username) == null) {
            Producer producer = producerRepository.findByLogin(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(producer.getLogin())
                    .password(producer.getPassword())
                    .authorities(producer.getRole().stream().map(Enum::name).toArray(String[]::new)).build();

        } else if (userRepository.findByLogin(username) == null
                && userRepository.findByEmail(username) != null) {
            User user = userRepository.findByEmail(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getLogin())
                    .password(user.getPassword())
                    .authorities(user.getRole().stream().map(Enum::name).toArray(String[]::new)).build();

        } else if (userRepository.findByLogin(username) == null
                && userRepository.findByEmail(username) == null
                && producerRepository.findByLogin(username) == null) {
            Courier courier = courierRepository.findCourierByLogin(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(courier.getLogin())
                    .password(courier.getPassword())
                    .authorities(courier.getRole().stream().map(Enum::name).toArray(String[]::new)).build();

        } else {
            User user = userRepository.findByLogin(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getLogin())
                    .password(user.getPassword())
                    .authorities(user.getRole().stream().map(Enum::name).toArray(String[]::new)).build();
        }
    }

}
