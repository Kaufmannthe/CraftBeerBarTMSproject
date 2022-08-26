package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
    User getUserByLoginAndPassword(String login, String password);

}
