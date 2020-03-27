package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void confirm(String token) {
        Optional<User> userOptional = usersRepository.findByConfirmCode(token);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
        }

    }
}
