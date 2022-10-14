package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.RegistrationService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView registerPage() {
        ModelAndView view = new ModelAndView();
        view.addObject("roleList", registrationService.rolesForAdminsUserRegistration());
        view.addObject("user", new User());
        view.setViewName("user/registration");
        return view;
    }

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute("user") User user,
                                     @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        registrationService.pictureCheckUser(user, file);
        userService.add(user);
        view.setViewName("user/landing");
        return view;
    }
}
