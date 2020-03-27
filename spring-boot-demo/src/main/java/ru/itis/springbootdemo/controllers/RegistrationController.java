package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springbootdemo.dto.RegistrationDto;
import ru.itis.springbootdemo.service.RegistrationService;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String getRegistrationPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(RegistrationDto form) {
        registrationService.registration(form);
        return "redirect:/login";
    }
}
