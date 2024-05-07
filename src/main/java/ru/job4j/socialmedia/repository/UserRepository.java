package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select u from User u
            where u.login = :login and u.password = :password
            """)
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Query("""
            update User u
            set u.login = :#{#user.login},
            u.email = :#{#user.email},
            u.password = :#{#user.password}
            where u.id = :#{#user.id}
            """)
    int update(@Param("user") User user);

    @Modifying(clearAutomatically = true)
    @Query("delete from User u where u.id=:id")
    int delete(@Param("id") Long id);
}
