package ru.itis.springbootdemo.service;

import ru.itis.springbootdemo.dto.MessageDto;
import ru.itis.springbootdemo.models.Message;

import java.util.List;
import java.util.Set;

public interface MessageService {
    void save(MessageDto messageDto);
    List<MessageDto> getDialogue(String email1, String email2);
    Set<String> getEmailsForAdminPage(String adminEmail);
}
