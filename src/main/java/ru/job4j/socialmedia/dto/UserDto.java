package ru.job4j.socialmedia.dto;

import lombok.Data;
import ru.job4j.socialmedia.model.Post;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String login;
    private List<Post> posts = new ArrayList<>();
}
