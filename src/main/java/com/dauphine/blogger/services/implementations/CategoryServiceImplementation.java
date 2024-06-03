package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Implementation of the CategoryService interface.
 * Provides methods to manage categories, including retrieving, creating, updating, and deleting categories.
 * </p>
 *
 * <p>
 * All methods in this class may throw {@link CategoryNotFoundByIdException} if the specified category ID does not exist.
 * For methods that involve creating or updating categories, they may also throw {@link CategoryAlreadyExistsException} if a category with the same name already exists.
 * </p>
 *
 * <p>
 * This class is responsible for implementing the business logic associated with category management.
 * It interacts with the underlying data source through CategoryRepository.
 * </p>
 *
 * <p>
 * Author: Nelson PROIA <nelson.proia@dauphine.eu>
 * </p>
 */
@Service
public class CategoryServiceImplementation implements CategoryService {

    /**
     * Repository for managing category entities.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a new CategoryServiceImplementation object with the specified CategoryRepository.
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
     * Retrieves categories by name.
     *
     * @param name The name of the categories to retrieve
     * @return A list of categories with the specified name
     */
    @Override
    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findAllByName(name);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return The category with the specified ID
     * @throws CategoryNotFoundByIdException if the category with the specified ID does not exist
     */
    @Override
    public Category getCategory(UUID id) throws CategoryNotFoundByIdException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the new category
     * @return The newly created category
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    @Override
    public Category createCategory(String name) throws CategoryAlreadyExistsException {
        final boolean alreadyExistsByName = categoryRepository.existsByName(name);

        if (alreadyExistsByName) {
            throw new CategoryAlreadyExistsException(name);
        }

        Category category = new Category(name);

        return categoryRepository.save(category);
    }

    /**
     * Updates the name of a category with the specified ID.
     *
     * @param id   The ID of the category to update
     * @param name The new name for the category
     * @return The updated category
     * @throws CategoryNotFoundByIdException  if the category with the specified ID does not exist
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    @Override
    public Category updateCategoryName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        final Category category = getCategory(id);

        final boolean alreadyExistsByName = categoryRepository.existsByName(name);

        if (alreadyExistsByName) {
            throw new CategoryAlreadyExistsException(name);
        }

        category.setName(name);

        return categoryRepository.save(category);
    }

    /**
     * Deletes the category with the specified ID.
     *
     * @param id The ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     * @throws CategoryNotFoundByIdException if the category with the specified ID does not exist
     */
    @Override
    public boolean deleteCategory(UUID id) throws CategoryNotFoundByIdException {
        final boolean exists = categoryRepository.existsById(id);

        if (!exists) {
            throw new CategoryNotFoundByIdException(id);
        }

        categoryRepository.deleteById(id);

        return true;
    }

}
