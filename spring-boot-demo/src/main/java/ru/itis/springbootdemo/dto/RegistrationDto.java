package ru.itis.springbootdemo.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}

