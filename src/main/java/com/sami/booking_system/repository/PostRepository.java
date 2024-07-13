package com.sami.booking_system.repository;

import com.sami.booking_system.projection.PostProjection;
import com.sami.booking_system.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = """
        SELECT p FROM Post p
        WHERE (:search IS NULL OR :search = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :search, '%')))
        """,
            countQuery = """
        SELECT COUNT(p) FROM Post p
        WHERE (:search IS NULL OR :search = '' OR LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :search, '%')))
        """)
    Page<Post> search(String search, Pageable pageable);


    @Query( value = """
    SELECT
    p.id AS postId,
    p.title AS postTitle,
    p.content AS postContent,
    u.id AS authorId,
    u.name AS authorName,
    c.id AS commentId,
    c.content AS commentContent,
    u2.id AS commenterId,
    u2.name AS commenterName
        FROM
       posts p
        JOIN
            users u ON p.author_id = u.id
        LEFT JOIN
            comments c ON c.post_id = p.id
        LEFT JOIN
            users u2 ON c.user_id = u2.id
        WHERE
            p.id = :postId
    """, nativeQuery = true
            )
    Optional<PostProjection> findPostWithAuthorAndComments(@Param("postId") Long postId);

}
