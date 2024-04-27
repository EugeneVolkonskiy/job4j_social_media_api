package ru.job4j.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.NewsFeed;

public interface NewsFeedRepository extends CrudRepository<NewsFeed, Long> {

}
