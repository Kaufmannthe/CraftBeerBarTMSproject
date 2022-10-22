package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.RegistrationService;
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
@RequestMapping("/profile")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
public class UserProfileController {

    private final UserService userService;
    private final RegistrationService registrationService;
    private final OrderService orderService;

    @Autowired
    public UserProfileController(UserService userService, RegistrationService registrationService, OrderService orderService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView profileUser(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("userLogin", userService.getAuthUser(authentication));
        view.addObject("orders", orderService.deliveredOrders(authentication));
        view.setViewName("user/profile");
        return view;
    }

    @GetMapping("/edit")
    public ModelAndView profileEditPage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("user", userService.getAuthUser(authentication));
        view.setViewName("/user/profileEdit");
        return view;
    }

    @PostMapping("/edit")
    public ModelAndView profileEdit(@ModelAttribute("user") User user, Authentication authentication,
                                    @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        registrationService.pictureCheckUser(user, file);
        userService.update(userService.updateAuthUser(authentication, user));
        view.setViewName("redirect:/profile");
        return view;
    }

    @PostMapping("/edit/photo")
    public ModelAndView deletePhoto(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        userService.deletePhoto(authentication);
        view.setViewName("redirect:/profile");
        return view;
    }

    @GetMapping("/edit_password")
    public ModelAndView passwordEditPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/passwordEditor");
        return view;
    }

    @PostMapping("/edit_password")
    public ModelAndView passwordEdit(@ModelAttribute("user") User user, Authentication authentication) {
        ModelAndView view = new ModelAndView();
        userService.updatePassword(authentication, user);
        view.setViewName("redirect:/profile");
        return view;
    }
}
