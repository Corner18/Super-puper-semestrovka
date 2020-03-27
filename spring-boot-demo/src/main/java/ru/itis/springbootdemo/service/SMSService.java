package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.models.User;

public interface SMSService {
    void sendSms(User user, String text);
}
