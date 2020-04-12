package ru.itis.springbootdemo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootdemo.models.Post;

public interface PostRepository extends JpaRepository<Post, Long > {
    @Query("from Post post where " +
            "(upper(post.header) like concat('%', upper(:query), '%') or " +
            "upper(post.user.name) like concat('%', upper(:query), '%') or " +
            "upper(post.text) like concat('%', upper(:query), '%')) ")
    Page<Post> search(@Param("query") String query, Pageable pageable);
}
