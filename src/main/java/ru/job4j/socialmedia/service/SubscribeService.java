package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.User;

public interface SubscribeService {

    void subscribe(User userFrom, User userTo);

    void unsubscribe(User userFrom, User userTo);

    void accept(User userFrom, User userTo);
}
