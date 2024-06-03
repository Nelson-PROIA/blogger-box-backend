package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Repository interface for managing Post entities.
 * This interface extends JpaRepository to provide CRUD operations for Post entities.
 * <p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface PostRepository extends JpaRepository<Post, UUID> {

    /**
     * Finds posts by title or content containing a keyword, ignoring case.
     *
     * @param keyword The keyword to search for in post titles or content (case-insensitive)
     * @return A list of posts containing the specified keyword in their title or content
     */
    @Query("""
                SELECT p
                FROM Post p
                WHERE UPPER(p.title) LIKE UPPER(CONCAT('%', :keyword, '%')) OR UPPER(p.content) LIKE UPPER(CONCAT('%', :keyword, '%'))
            """)
    List<Post> findByTitleOrContentContainingKeyword(@Param("keyword") String keyword);

    /**
     * Retrieves all posts ordered by their creation date.
     *
     * @return A list of all posts ordered by creation date
     */
    List<Post> findAllByOrderByCreatedDate();

    /**
     * Retrieves all posts belonging to a specific category.
     *
     * @param categoryId The ID of the category
     * @return A list of posts belonging to the specified category
     */
    List<Post> findAllByCategoryId(UUID categoryId);

}
