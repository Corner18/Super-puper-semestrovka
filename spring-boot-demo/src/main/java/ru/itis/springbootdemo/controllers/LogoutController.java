package ru.itis.springbootdemo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String getUsersPage(Authentication authentication) {
        authentication.setAuthenticated(false);
        return "login";
    }
}
