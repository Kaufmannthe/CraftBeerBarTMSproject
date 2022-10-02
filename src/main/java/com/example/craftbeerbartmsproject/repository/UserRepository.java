package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
    User findUserByLoginAndPassword(String login, String password);

    User findByEmail(String email);


}
