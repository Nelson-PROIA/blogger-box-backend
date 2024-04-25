package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Post entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Post entities.
 * <p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface PostRepository extends JpaRepository<Post, UUID> {

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
