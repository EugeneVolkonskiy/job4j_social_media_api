package ru.job4j.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {

}
