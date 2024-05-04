package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Subscribe;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends CrudRepository<Subscribe, Long> {

    @Query("""
            select s.userFrom from Subscribe s
            where s.userTo.id = :userToId
            """)
    List<User> findSubscribers(@Param("userToId") Long userToId);

    @Query("""
            select s1.userTo from Subscribe s1
            where s1.userFrom.id = :userFromId and s1.userTo.id
            in (select s2.userFrom.id from Subscribe s2
                where s2.userTo.id = :userToId)
            """)
    List<User> findAllFriends(@Param("userFromId") Long userFromId, @Param("userToId") Long userToId);

    Optional<Subscribe> findByUserFromIdAndUserToId(Long userFromId, Long userToId);
}
