package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springbootdemo.dto.CommentDto;
import ru.itis.springbootdemo.dto.LikeDto;
import ru.itis.springbootdemo.models.Comment;
import ru.itis.springbootdemo.models.Post;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.PostRepository;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.service.FileStorageService;
import ru.itis.springbootdemo.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/post/{post_id}")
    public String getPostPage(Model model, Authentication authentication, @PathVariable("post_id") Long post_id, HttpServletRequest request) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            HttpSession session = request.getSession();
            session.setAttribute("post_id", post_id);

            model.addAttribute("comments", postService.userComment(post_id));
            model.addAttribute("user", userDetails.getUser());
            model.addAttribute("post", postService.getOne(post_id).get());
            return "post";
        } else {
            return "redirect:/login";
        }


    }

    @PostMapping("/post")
    public String makeComment(Authentication authentication, CommentDto form, HttpServletRequest request) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            form.setUser_id(userDetails.getUser().getId());
            form.setPost_id(Long.parseLong(String.valueOf(request.getSession().getAttribute("post_id"))));
            postService.makeComment(form);
        }
        String id = String.valueOf(request.getSession().getAttribute("post_id"));
        return "redirect:/post/" + id;
    }

    @PostMapping("/like")
    public String makeLike(Authentication authentication, HttpServletRequest request, LikeDto form) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            form.setPost_id(Long.parseLong(String.valueOf(request.getSession().getAttribute("post_id"))));
            form.setUser_id(userDetails.getUser().getId());
            postService.makeLike(form);
        }
        String id = String.valueOf(request.getSession().getAttribute("post_id"));
        return "redirect:/post/" + id;
    }

}
