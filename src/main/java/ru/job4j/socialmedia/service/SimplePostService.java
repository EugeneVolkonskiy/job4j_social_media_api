package ru.job4j.socialmedia.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.ImageDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.PostRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SimplePostService implements PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;

    @Override
    public Post save(Post post, ImageDto imageDto, User user) {
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        imageService.save(imageDto, post);
        return savedPost;
    }

    @Override
    public int updateTitleAndContent(String title, String content, Long id) {
        return postRepository.updateTitleAndContent(title, content, id);
    }

    @Override
    public int deletePostById(Long id) {
        imageService.deleteById(id);
        return postRepository.deletePostById(id);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
