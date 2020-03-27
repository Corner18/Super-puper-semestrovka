package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Likes;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    List<Likes> getAllByUser_Id(Long user_id);
}
