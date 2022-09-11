package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User findByLogin(String name);

    User update(User user);

    User add(User user);

    void delete(Long id);

    User findUserByLoginAndPassword(String login, String password);

    String saveImage(MultipartFile file) throws IOException;
}
