package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.service.ConfirmService;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{token}")
    public String getConfirmPage(@PathVariable("token") String token) {
        if( token!= null){
            confirmService.confirm(token);
            return "redirect:/login";
        }
        return "redirect:/registration";
    }
}
