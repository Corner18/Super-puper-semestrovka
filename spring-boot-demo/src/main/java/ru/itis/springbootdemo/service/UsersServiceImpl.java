package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.CookieValue;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.CookieValuesRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return users;
    }

    @Override
    public User getUser(Long userId) {
        return usersRepository.getOne(userId);
    }


    @Override
    public User getUserByCookie(String cookie) {
        Optional<CookieValue> cookieValueOptional = cookieValuesRepository.findByValue(cookie);
        if(cookieValueOptional.isPresent()){
            CookieValue cookieValue = cookieValueOptional.get();
            Long id = cookieValue.getUser().getId();
            User user = usersRepository.getOne(id);
            return user;
        }
        return null;
    }
}
