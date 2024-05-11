package ru.job4j.socialmedia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.job4j.socialmedia.model.Post;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "login не может быть пустым")
    @Size(min = 3,
            max = 12,
            message = "login должен быть не менее 3 и не более 12 символов")
    private String login;

    private List<Post> posts = new ArrayList<>();
}
