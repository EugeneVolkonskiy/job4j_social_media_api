package ru.job4j.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);

    List<Post> findByCreatedBetween(LocalDateTime start, LocalDateTime end);

    Page<Post> findByOrderByCreatedDesc(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            update Post p set p.title = :title and p.content = :content
            where p.id = :id
            """, nativeQuery = true)
    int updateTitleAndContent(@Param("title") String title, @Param("content") String content, @Param("id") Long id);


    @Modifying(clearAutomatically = true)
    @Query("delete Post p where p.id = ?1")
    int deletePostById(Long id);

    @Query("""
            select p from Post p
            where p.user.id in (
                select s.userFrom.id from Subscribe s
                where s.userTo.id = :userToId)
            order by p.created desc
            """)
    List<Post> findAllByCreated(@Param("userToId") Long userToId, Pageable pageable);
}
