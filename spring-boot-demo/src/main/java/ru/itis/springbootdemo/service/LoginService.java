package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.dto.LoginDto;
import ru.itis.springbootdemo.dto.RegistrationDto;

import java.util.Optional;

public interface LoginService {
    Optional<String> login(LoginDto loginDto);
}
