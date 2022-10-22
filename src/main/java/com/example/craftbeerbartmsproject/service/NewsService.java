package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.News;
import com.example.craftbeerbartmsproject.model.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface NewsService {

    List<News> findAllByRole(Roles role);

    List<News> findAllByDataCreated(Date date);

    List<News> findAll();

    News add(News news, MultipartFile file) throws IOException;

    void delete(News news);

    News update(News news);

    String saveImage(MultipartFile file) throws IOException;
}
