package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Repository interface for managing Category entities.
 * This interface extends JpaRepository to provide CRUD operations for Category entities.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    /**
     * Finds all categories by name, ignoring case.
     *
     * @param name The name of the category (case-insensitive)
     * @return A list of categories with names containing the specified name
     */
    @Query("""
                SELECT c
                FROM Category c
                WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))
            """)
    List<Category> findAllByName(@Param("name") String name);

    /**
     * Checks if a category with the specified name exists.
     *
     * @param name The name of the category
     * @return true if a category with the specified name exists, false otherwise
     */
    boolean existsByName(String name);

}
