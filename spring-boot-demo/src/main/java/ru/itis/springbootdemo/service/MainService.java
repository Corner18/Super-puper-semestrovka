package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.models.Post;

import java.util.List;

public interface MainService {
    List<Post> getAll();
}
