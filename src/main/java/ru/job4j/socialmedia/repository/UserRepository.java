package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("""
            select u from User u
            where u.login = :login and u.password = :password
            """)
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
