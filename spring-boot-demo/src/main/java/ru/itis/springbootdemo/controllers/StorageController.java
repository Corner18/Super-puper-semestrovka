package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.service.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StorageController {

    @Autowired
    private FileStorageService service;

    @GetMapping("/storage")
    public String getStoragePage() {
        return "file_upload";
    }

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.saveFile(file, user);

        return "redirect:/profile";
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }
}
