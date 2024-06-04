package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.implementations.CategoryServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>
 * Unit tests for the CategoryServiceImplementation class.
 * These tests validate the functionality of the service methods and ensure correct interaction with the CategoryRepository.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class CategoryServiceImplementationTest {

    /**
     * Mocked CategoryRepository used to simulate interactions with the database.
     */
    @Mock
    private CategoryRepository categoryRepository;

    /**
     * The service implementation being tested, with the mocked repository injected.
     */
    @InjectMocks
    private CategoryServiceImplementation categoryService;

    /**
     * Setup method to initialize mocks and the service implementation before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to verify that getCategories returns an empty list when no categories exist.
     */
    @Test
    public void getCategories_ReturnsEmptyList_WhenNoCategoriesExist() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<Category> categories = categoryService.getCategories();

        assertTrue(categories.isEmpty());
    }

    /**
     * Test to verify that getCategoriesByName returns an empty list when no categories with the specified name exist.
     */
    @Test
    public void getCategoriesByName_ReturnsEmptyList_WhenNoCategoriesExist() {
        String name = "NonexistentCategory";

        when(categoryRepository.findAllByName(name)).thenReturn(Collections.emptyList());

        List<Category> categories = categoryService.getCategoriesByName(name);

        assertTrue(categories.isEmpty());
    }

    /**
     * Test to verify that createCategory creates a new category when the category does not already exist.
     *
     * @throws CategoryAlreadyExistsException if the category already exists
     */
    @Test
    public void createCategory_CreatesCategory_WhenCategoryDoesNotExist() throws CategoryAlreadyExistsException {
        String categoryName = "NewCategory";
        Category category = new Category(categoryName);

        when(categoryRepository.existsByName(categoryName)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(categoryName);

        assertNotNull(createdCategory);
        assertEquals(categoryName, createdCategory.getName());
    }

    /**
     * Test to verify that createCategory throws an exception when the category already exists.
     */
    @Test
    public void createCategory_ThrowsException_WhenCategoryAlreadyExists() {
        String existingCategoryName = "ExistingCategory";

        when(categoryRepository.existsByName(existingCategoryName)).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(existingCategoryName));
    }

    /**
     * Test to verify that updateCategoryName updates the category name when the category exists and the new name is unique.
     *
     * @throws CategoryNotFoundByIdException  if the category is not found by ID
     * @throws CategoryAlreadyExistsException if a category with the new name already exists
     */
    @Test
    public void updateCategoryName_UpdatesCategoryName_WhenCategoryExistsAndNewNameIsUnique() throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        UUID categoryId = UUID.randomUUID();
        String newName = "UpdatedName";
        Category existingCategory = new Category("ExistingCategory");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.existsByName(newName)).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        Category updatedCategory = categoryService.updateCategoryName(categoryId, newName);

        assertEquals(newName, updatedCategory.getName());
    }

    /**
     * Test to verify that updateCategoryName throws an exception when the category does not exist.
     */
    @Test
    public void updateCategoryName_ThrowsException_WhenCategoryDoesNotExist() {
        UUID nonExistentCategoryId = UUID.randomUUID();
        String newName = "UpdatedName";

        when(categoryRepository.findById(nonExistentCategoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryService.updateCategoryName(nonExistentCategoryId, newName));
    }

    /**
     * Test to verify that updateCategoryName throws an exception when the new name already exists.
     */
    @Test
    public void updateCategoryName_ThrowsException_WhenNewNameAlreadyExists() {
        UUID categoryId = UUID.randomUUID();
        String existingName = "ExistingName";
        String newName = "ExistingName";
        Category existingCategory = new Category(existingName);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.existsByName(newName)).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.updateCategoryName(categoryId, newName));
    }

    /**
     * Test to verify that deleteCategory deletes the category when the category exists.
     *
     * @throws CategoryNotFoundByIdException if the category is not found by ID
     */
    @Test
    public void deleteCategory_DeletesCategory_WhenCategoryExists() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();

        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(categoryId);

        boolean deleted = categoryService.deleteCategory(categoryId);

        assertTrue(deleted);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    /**
     * Test to verify that deleteCategory throws an exception when the category does not exist.
     */
    @Test
    public void deleteCategory_ThrowsException_WhenCategoryDoesNotExist() {
        UUID nonExistentCategoryId = UUID.randomUUID();

        when(categoryRepository.existsById(nonExistentCategoryId)).thenReturn(false);

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryService.deleteCategory(nonExistentCategoryId));
    }

}

