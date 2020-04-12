package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.service.MessageService;

@Controller
public class AdminConroller {

    @Autowired
    private MessageService messageService;

    @GetMapping("/admin/{receiver}")
    public String getDialogues(Model model, Authentication authentication, @PathVariable("receiver") String receiver){
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("user", user);
            model.addAttribute("admin", user.getEmail());
            model.addAttribute("messages", messageService.getDialogue(user.getEmail(), receiver));
        }
        return "support";
    }

    @GetMapping("/admin")
    public String getDialoguesList(Model model, Authentication authentication){
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("user", user);
            model.addAttribute("dialogues", messageService.getEmailsForAdminPage(user.getEmail()));
        }
        return "dialogues";
    }

}
