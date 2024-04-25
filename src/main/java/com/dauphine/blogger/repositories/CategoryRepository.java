package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for managing Category entities.
 * <p>
 * This interface extends JpaRepository to provide CRUD operations for Category entities.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.eu
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
