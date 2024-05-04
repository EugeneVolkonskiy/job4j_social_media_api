package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.ImageDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;

import java.util.List;

public interface PostService {

    Post save(Post post, ImageDto imageDto, User user);

    int updateTitleAndContent(String title, String content, Long id);

    int deletePostById(Long id);

    List<Post> findByUserId(Long userId);
}
