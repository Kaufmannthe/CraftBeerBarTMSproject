package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User findByLogin(String name);

    User update(User user);

    User add(User user);

    void delete(Long id);

    User getUserByLoginAndPassword(String login, String password);
}
