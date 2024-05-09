package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLoginAndPassword(String login, String password);

    List<User> findAll();

    boolean update(User user);

    boolean deleteById(Long id);

    List<UserDto> findByUsersId(List<Long> usersId);
}
