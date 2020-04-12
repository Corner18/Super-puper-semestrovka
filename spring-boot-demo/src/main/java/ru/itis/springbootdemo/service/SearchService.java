package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.dto.PostsSearchDto;

public interface SearchService {
    PostsSearchDto searchPost(String query, Integer size, Integer page);
}
