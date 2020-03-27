package ru.itis.springbootdemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.springbootdemo.dto.LoginDto;
import ru.itis.springbootdemo.models.CookieValue;
import ru.itis.springbootdemo.models.State;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.CookieValuesRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CookieValuesRepository cookieValuesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> login(LoginDto loginDto) {

        Optional<User> userOptional = usersRepository.findByEmail(loginDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String hashPassword = user.getPassword();
            String rawPassword = loginDto.getPassword();
            if (passwordEncoder.matches(rawPassword,hashPassword) && (user.getState().equals(State.CONFIRMED))){
                String newCookie = UUID.randomUUID().toString();

                CookieValue cookieValue = CookieValue.builder()
                        .value(newCookie)
                        .createdAt(LocalDateTime.now())
                        .user(user)
                        .build();

                cookieValuesRepository.save(cookieValue);

                return Optional.of(newCookie);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}


