package ru.itis.springbootdemo.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.FileInfo;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.FileInfoRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveFile(MultipartFile file, User user) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(file);
        fileInfo.setUser(user);
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
        user.setAvatar("http://localhost:8080/files/" + fileInfo.getStorageFileName());
        usersRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findOneByStorageFileName(fileName);
        response.setContentType(file.getType());
        InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @Override
    public String takeUrl(Long userId) {
        Optional<FileInfo> fileInfoOptional = fileInfoRepository.findOneByUserId(userId);
        if (fileInfoOptional.isPresent()) {
            String storageName = fileInfoOptional.get().getStorageFileName();
            return storageName;
        }
        return null;
    }
}
