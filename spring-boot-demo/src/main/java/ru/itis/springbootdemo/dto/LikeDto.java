package ru.itis.springbootdemo.dto;

import lombok.Data;

@Data
public class LikeDto {
    private Long user_id;
    private Long post_id;
}
