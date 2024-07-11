package com.sami.booking_system.repository;

import com.sami.booking_system.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
