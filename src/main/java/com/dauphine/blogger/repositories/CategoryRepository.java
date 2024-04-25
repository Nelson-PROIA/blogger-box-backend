package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for managing Category entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Category entities.
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    /**
     * Checks if a category with the specified name exists.
     *
     * @param name The name of the category
     * @return true if a category with the specified name exists, false otherwise
     */
    boolean existsByName(String name);

}

