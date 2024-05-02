package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Subscriber;
import ru.job4j.socialmedia.model.User;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

    @Query("""
            select s.userFrom from Subscriber s
            where s.userTo.id = :userToId
            """)
    List<User> findSubscribers(@Param("userToId") Long userToId);

    @Query("""
            select s1.userTo from Subscriber s1
            where s1.userFrom.id = :userFromId and s1.userTo.id
            in (select s2.userFrom.id from Subscriber s2
                where s2.userTo.id = :userToId)
            """)
    List<User> findAllFriends(@Param("userFromId") Long userFromId, @Param("userToId") Long userToId);
}
