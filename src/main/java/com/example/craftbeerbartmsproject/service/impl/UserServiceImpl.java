package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.UserRepository;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User add(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String folder = "C:\\CraftBeerBarTMSproject\\src\\main\\resources\\static\\img\\uploaded\\user_picture\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
        return "/img/uploaded/user_picture/" + file.getOriginalFilename();
    }

    @Override
    public User getAuthUser(Authentication authentication) {
        String userName = authentication.getName();
        return findByLogin(userName);
    }

    @Override
    public User updateAuthUser(Authentication authentication, User user) {
        User authUser = getAuthUser(authentication);
        if (authUser.getPicture() != null) {
            return new User(
                    authUser.getId(), user.getFirstName(), user.getLastName(), authUser.getLogin(), authUser.getPassword(),
                    user.isActive(), authUser.getAge(), user.getAddress(), authUser.getGender(), authUser.getPhoneNumber(),
                    user.getEmail(), authUser.getDataCreated(), authUser.getRole(), authUser.getContactsList(),
                    authUser.getPicture());
        } else {
            authUser.setPicture(user.getPicture());
            return update(authUser);
        }
    }

    @Override
    public void deletePhoto(Authentication authentication) {
        User authUser = getAuthUser(authentication);
        authUser.setPicture(null);
        update(authUser);
    }

    @Override
    public void updatePassword(Authentication authentication, User user) {
        User authUser = getAuthUser(authentication);
        authUser.setPassword(passwordEncoder.encode(user.getPassword()));
        update(authUser);
    }
}
