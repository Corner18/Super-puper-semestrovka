package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.PostsSearchDto;
import ru.itis.springbootdemo.models.Post;
import ru.itis.springbootdemo.repositories.PostRepository;

@Component
public class SearchServiceImpl implements SearchService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostsSearchDto searchPost(String query, Integer size, Integer page) {
        if(size == null) size = 10;
        if(page == null) page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> pageResult = postRepository.search(query,pageRequest);
        return PostsSearchDto.builder()
                .pageCount(pageResult.getTotalPages())
                .posts(pageResult.getContent())
                .build();

    }
}
