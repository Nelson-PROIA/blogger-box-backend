package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the CategoryService interface.
 * Provides methods to manage categories, including retrieving, creating, updating,
 * and deleting categories.
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@Service
public class CategoryServiceImplementation implements CategoryService {

    /**
     * Repository for managing categories.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a CategoryServiceImplementation object with the specified CategoryRepository.
     *
     * @param categoryRepository The repository for managing categories
     */
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories
     */
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return The category with the specified ID, or null if not found
     * @throws RuntimeException if the category with the specified ID does not exist
     */
    @Override
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " does not exist!"));
    }

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category
     * @return The newly created category
     * @throws RuntimeException if a category with the same name already exists
     */
    @Override
    public Category createCategory(String name) {
        final boolean alreadyExistsByName = categoryRepository.existsByName(name);

        if (alreadyExistsByName) {
            throw new RuntimeException("Category with name " + name + " already exists!");
        }

        Category category = new Category(name);

        return categoryRepository.save(category);
    }

    /**
     * Updates the name of a category with the specified ID.
     *
     * @param id   The ID of the category to update
     * @param name The new name for the category
     * @return The updated category, or null if the category with the specified ID was not found
     */
    @Override
    public Category updateCategoryName(UUID id, String name) {
        Category category = getCategory(id);

        category.setName(name);

        return categoryRepository.save(category);
    }

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteCategory(UUID id) {
        boolean deleted = categoryRepository.existsById(id);

        categoryRepository.deleteById(id);

        return deleted;
    }

}
