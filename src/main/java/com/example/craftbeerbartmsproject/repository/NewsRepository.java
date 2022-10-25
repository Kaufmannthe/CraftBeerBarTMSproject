package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

}
