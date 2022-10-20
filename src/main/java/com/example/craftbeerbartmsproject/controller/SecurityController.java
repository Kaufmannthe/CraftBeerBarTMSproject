package com.example.craftbeerbartmsproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/security")
@PreAuthorize("hasAuthority('ADMIN')")
public class SecurityController {
    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal){
        return principal.toString();
    }

}
