package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.ImageDto;
import ru.job4j.socialmedia.model.Image;
import ru.job4j.socialmedia.model.Post;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image save(ImageDto imageDto, Post post);

    Optional<ImageDto> findById(Long id);

    void deleteById(Long id);

    List<Image> findByPostId(Long postId);

    int deleteImageByPostId(Long id);
}
