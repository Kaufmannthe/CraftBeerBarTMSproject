package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.Roles;
import com.example.craftbeerbartmsproject.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RegistrationService {

    List<Roles> rolesForAdminsUserRegistration();

    void pictureCheckUser(User user, MultipartFile file) throws IOException;

    void pictureCheckProducer(Producer producer, MultipartFile file) throws IOException;
}
