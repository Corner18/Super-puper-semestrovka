package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Post;

public interface PostRepository extends JpaRepository<Post, Long > {
}
