package ru.itis.springbootdemo.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.User;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    // сохраняет файл на сервере
    void saveFile(MultipartFile file, User user);
    // отправляет файл по запросу
    void writeFileToResponse(String fileName, HttpServletResponse response);
    // выводит файл по айди юзера
    String takeUrl(Long userId);
}
