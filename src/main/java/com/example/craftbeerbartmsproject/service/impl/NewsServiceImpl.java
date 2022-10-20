package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.News;
import com.example.craftbeerbartmsproject.model.Roles;
import com.example.craftbeerbartmsproject.repository.NewsRepository;
import com.example.craftbeerbartmsproject.service.NewsService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;
    private final ProducerService producerService;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, UserService userService, ProducerService producerService) {
        this.newsRepository = newsRepository;
        this.userService = userService;
        this.producerService = producerService;
    }

    @Override
    public List<News> findAllByRole(Roles role) {
        return newsRepository.findAllByRole(role);
    }

    @Override
    public List<News> findAllByDataCreated(Date date) {
        return newsRepository.findAllByDataCreated(date);
    }

    @Override
    public Roles findCurrentUserRole(Authentication authentication) {
        if (userService.getAuthUser(authentication) == null
                || userService.getAuthUser(authentication).getRole().stream().findFirst().orElse(null) != Roles.USER) {
            return producerService.findByName(authentication.getName()).getRole().stream().findFirst().orElse(null);
        } else {
            return userService.getAuthUser(authentication).getRole().stream().findFirst().orElse(null);
        }
    }


    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News add(News news, MultipartFile file) throws IOException {
        news.setPicture(saveImage(file));
        return newsRepository.save(news);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }

    @Override
    public News update(News news) {
        return newsRepository.save(news);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String folder = "C:\\CraftBeerBarTMSproject\\src\\main\\resources\\static\\img\\uploaded\\news_picture\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
        return "/img/uploaded/news_picture/" + file.getOriginalFilename();
    }
}
