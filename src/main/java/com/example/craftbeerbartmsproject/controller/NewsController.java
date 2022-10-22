package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.News;
import com.example.craftbeerbartmsproject.service.NewsService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;
    private final ProducerService producerService;

    @Autowired
    public NewsController(NewsService newsService, UserService userService, ProducerService producerService) {
        this.newsService = newsService;
        this.userService = userService;
        this.producerService = producerService;
    }

    @GetMapping
    public ModelAndView newsPage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("news", newsService.findAll());
        view.addObject("user", userService.findByLogin(authentication.getName()));
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/user/news");
        return view;
    }

    @GetMapping("/constructor")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ModelAndView createNewsPage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("news", new News());
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/moderator/newsConstructor");
        return view;
    }

    @PostMapping("/constructor")
    public ModelAndView createNews(@ModelAttribute("news") News news, @RequestParam("imageFile") MultipartFile file)
            throws IOException {
        ModelAndView view = new ModelAndView();
        newsService.add(news, file);
        view.setViewName("redirect:/news");
        return view;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    public ModelAndView deleteNews(@PathVariable("id") News news) {
        ModelAndView view = new ModelAndView();
        newsService.delete(news);
        view.setViewName("redirect:/news");
        return view;
    }
}
