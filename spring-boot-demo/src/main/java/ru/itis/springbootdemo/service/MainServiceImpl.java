package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.models.Post;
import ru.itis.springbootdemo.repositories.PostRepository;

import java.util.List;

@Component
public class MainServiceImpl implements MainService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
