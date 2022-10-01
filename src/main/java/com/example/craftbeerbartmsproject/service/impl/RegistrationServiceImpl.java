package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.Roles;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.RegistrationService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final ProducerService producerService;

    @Autowired
    public RegistrationServiceImpl(UserService userService, ProducerService producerService) {
        this.userService = userService;
        this.producerService = producerService;
    }

    @Override
    public List<Roles> rolesForAdminsUserRegistration() {
        return List.of(Roles.USER, Roles.ADMIN);
    }

    @Override
    public void pictureCheckUser(User user, MultipartFile file) throws IOException {
        if (!Objects.equals(file.getOriginalFilename(), "")) {
            user.setPicture(userService.saveImage(file));
        }
    }

    @Override
    public void pictureCheckProducer(Producer producer, MultipartFile file) throws IOException {
        if (!Objects.equals(file.getOriginalFilename(), "")) {
            producer.setPicture(producerService.saveImage(file));
        }
    }
}
