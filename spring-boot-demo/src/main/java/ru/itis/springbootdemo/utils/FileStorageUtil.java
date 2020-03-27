package ru.itis.springbootdemo.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.FileInfo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageUtil {

    @Value("${storage.path}")
    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }

    // сохраняет файл на диск
    @SneakyThrows
    public void copyToStorage(MultipartFile file, String storageFileName) {
        Files.copy(file.getInputStream(), Paths.get(storagePath, storageFileName));
    }

    // принимает на вход файл в формате Multipart
    // сохраняет его в БД
    public FileInfo convertFromMultipart(MultipartFile file) {
        String originalFileName = file.getOriginalFilename(); // получаем название файла
        String type = file.getContentType();  // вытаскиваем контент-тайп (MIME)
        long size = file.getSize();  // размер файла
        String storageName = createStorageName(originalFileName); // создаем имя файла
        String fileUrl = getUrlOfFile(storageName); // получаем url файла по которому он будет доступен внутри системы
        return FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(storageName)
                .url(fileUrl)
                .size(size)
                .type(type)
                .build();
    }

    private String getUrlOfFile(String storageFileName) {
        // путь к папке с файлами на диске + название файла
        return storagePath + "/" + storageFileName;
    }

    // создает уникальное имя файла на диске с его расширением
    private String createStorageName(String originalFileName) {
        // получаем расширение файла по его имени
        String extension = FilenameUtils.getExtension(originalFileName);
        // генерируем случайную строку
        String newFileName = UUID.randomUUID().toString();
        // новое имя файла - UUID + . + расширение файла
        return newFileName + "." + extension;
    }
}
