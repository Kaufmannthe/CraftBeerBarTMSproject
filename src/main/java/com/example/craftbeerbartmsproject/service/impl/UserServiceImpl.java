package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.UserRepository;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userRepository.getUserByLoginAndPassword(login, password);
    }
}
