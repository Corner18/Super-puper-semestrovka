package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.FileInfo;
import ru.itis.springbootdemo.models.User;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
    Optional<FileInfo> findOneByUserId(Long user_id);
}
