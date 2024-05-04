package ru.job4j.socialmedia.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.model.Subscribe;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.SubscribeRepository;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SimpleSubscribeService implements SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Override
    public void subscribe(User userFrom, User userTo) {
        Subscribe subscribe = new Subscribe();
        subscribe.setUserFrom(userFrom);
        subscribe.setUserTo(userTo);
        subscribeRepository.save(subscribe);
    }

    @Override
    public void unsubscribe(User userFrom, User userTo) {
        Optional<Subscribe> subscriber = subscribeRepository.findByUserFromIdAndUserToId(userFrom.getId(), userTo.getId());
        subscriber.ifPresent(subscribeRepository::delete);
    }

    @Override
    public void accept(User userFrom, User userTo) {
        Optional<Subscribe> subscriber = subscribeRepository.findByUserFromIdAndUserToId(userFrom.getId(), userTo.getId());
        subscriber.ifPresent(s -> subscribe(userTo, userFrom));
    }
}
