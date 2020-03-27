package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.models.Post;

import java.util.List;

public interface FavouritesService {
    List<Post> favs (Long user_id);
}
