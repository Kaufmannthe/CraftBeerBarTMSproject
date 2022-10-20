package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.News;
import com.example.craftbeerbartmsproject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    List<News> findAllByRole(Roles role);

    List<News> findAllByDataCreated(Date date);
}
