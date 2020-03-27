package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.CookieValue;

import java.util.Optional;

public interface CookieValuesRepository extends JpaRepository<CookieValue, Long> {
    Optional<CookieValue> findByValue(String value);
}
