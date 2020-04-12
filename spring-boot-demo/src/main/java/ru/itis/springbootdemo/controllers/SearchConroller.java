package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.PostsSearchDto;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchConroller {

    @Autowired
    private SearchService searchService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/posts")
    public String searchProducts(Model model, Authentication authentication, @RequestParam("q") String query,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size) {

        PostsSearchDto postsSearchDto = searchService.searchPost(query, size, page);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("posts", postsSearchDto.getPosts());
        return "posts";
    }

}
