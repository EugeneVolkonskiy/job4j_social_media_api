package ru.job4j.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Subscriber;

public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

}
