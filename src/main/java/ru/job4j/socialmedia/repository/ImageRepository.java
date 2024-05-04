package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Image;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Modifying(clearAutomatically = true)
    @Query("delete Image where post.id = ?1")
    int deleteImageByPostId(Long id);

    List<Image> findByPostId(Long postId);
}
