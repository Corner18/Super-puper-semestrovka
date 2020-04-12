package ru.itis.springbootdemo.service;


import ru.itis.springbootdemo.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    User getUser(Long userId);

    User getUserByCookie(String cookie);

    User getAdmin();
}
