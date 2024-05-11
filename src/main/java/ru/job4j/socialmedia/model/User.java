package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "login не может быть пустым")
    @Size(min = 3,
            max = 12,
            message = "login должен быть не менее 3 и не более 12 символов")
    private String login;

    @NotBlank(message = "email не может быть пустым")
    @Email
    private String email;

    @NotBlank(message = "password не может быть пустым")
    @Size(min = 6,
                max = 15,
                message = "password должен быть не менее 6 и не более 15 символов")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    private void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }
}
