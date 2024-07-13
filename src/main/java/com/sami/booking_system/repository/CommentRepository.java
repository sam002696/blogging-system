package com.sami.booking_system.repository;

import com.sami.booking_system.projection.CommentProjection;
import com.sami.booking_system.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = """
            SELECT
            	c.content AS content,
            	u.name AS userName
            FROM
            	comments c
            LEFT JOIN users u ON
            	c.user_id = u.id
            """, nativeQuery = true)
    List<CommentProjection> getComments();
}
