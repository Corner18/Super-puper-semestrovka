package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.service.FileStorageService;

@Controller
public class UserController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/profile")
    public String getUsersPage(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userDetails != null) {
            User user = userDetails.getUser();
            if (fileStorageService.takeUrl(user.getId()) != null)
            {
                String path = fileStorageService.takeUrl(user.getId()).toString();
                String url = "http://localhost:8080/files/" + path ;
                model.addAttribute("url", url);
            }
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "/login";
        }

    }
}
