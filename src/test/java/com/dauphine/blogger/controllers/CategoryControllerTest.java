package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * <p>
 * Unit tests for the CategoryController class.
 * These tests validate the functionality of CategoryController's endpoints for managing categories.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class CategoryControllerTest {

    /**
     * Mocked CategoryService used to simulate the service layer for category operations.
     */
    @Mock
    private CategoryService categoryService;

    /**
     * Mocked PostService used to simulate the service layer for post operations.
     */
    @Mock
    private PostService postService;

    /**
     * The controller being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private CategoryController categoryController;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that getCategories returns all categories.
     */
    @Test
    public void getCategories_ReturnsAllCategories() {
        List<Category> categories = List.of(new Category("Category1"), new Category("Category2"));

        when(categoryService.getCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getCategories(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    /**
     * Tests that getCategories returns filtered categories by name.
     */
    @Test
    public void getCategories_ReturnsFilteredCategories() {
        String categoryName = "Category1";
        List<Category> categories = List.of(new Category(categoryName));

        when(categoryService.getCategoriesByName(categoryName)).thenReturn(categories);

        ResponseEntity<List<Category>> response = categoryController.getCategories(categoryName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    /**
     * Tests that getCategory returns a category when it exists.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void getCategory_ReturnsCategory_WhenCategoryExists() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();
        Category category = new Category("Category");

        when(categoryService.getCategory(categoryId)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    /**
     * Tests that getCategory throws an exception when the category does not exist.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void getCategory_ThrowsException_WhenCategoryDoesNotExist() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.getCategory(categoryId)).thenThrow(new CategoryNotFoundByIdException(categoryId));

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryController.getCategory(categoryId));
    }

    /**
     * Tests that postCategory creates a category when it does not already exist.
     *
     * @throws CategoryAlreadyExistsException if the category already exists
     */
    @Test
    public void postCategory_CreatesCategory_WhenCategoryDoesNotExist() throws CategoryAlreadyExistsException {
        String categoryName = "New Category";
        CategoryRequest categoryRequest = new CategoryRequest(categoryName);
        Category category = new Category(categoryName);

        when(categoryService.createCategory(categoryName)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.postCategory(categoryRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
        assertEquals(URI.create("v1/categories/" + category.getId()), response.getHeaders().getLocation());
    }

    /**
     * Tests that postCategory throws an exception when the category already exists.
     *
     * @throws CategoryAlreadyExistsException if the category already exists
     */
    @Test
    public void postCategory_ThrowsException_WhenCategoryAlreadyExists() throws CategoryAlreadyExistsException {
        String categoryName = "Existing Category";
        CategoryRequest categoryRequest = new CategoryRequest(categoryName);

        when(categoryService.createCategory(categoryName)).thenThrow(new CategoryAlreadyExistsException(categoryName));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryController.postCategory(categoryRequest));
    }

    /**
     * Tests that patchCategoryName updates the category name when the category exists.
     *
     * @throws CategoryNotFoundByIdException  if the category is not found
     * @throws CategoryAlreadyExistsException if the category name already exists
     */
    @Test
    public void patchCategoryName_UpdatesCategory_WhenCategoryExists() throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        UUID categoryId = UUID.randomUUID();
        String newCategoryName = "Updated Category";
        CategoryRequest categoryRequest = new CategoryRequest(newCategoryName);
        Category updatedCategory = new Category(newCategoryName);

        when(categoryService.updateCategoryName(categoryId, newCategoryName)).thenReturn(updatedCategory);

        ResponseEntity<Category> response = categoryController.putCategoryName(categoryId, categoryRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCategory, response.getBody());
    }

    /**
     * Tests that patchCategoryName throws an exception when the category does not exist.
     *
     * @throws CategoryNotFoundByIdException  if the category is not found
     * @throws CategoryAlreadyExistsException if the category name already exists
     */
    @Test
    public void patchCategoryName_ThrowsException_WhenCategoryDoesNotExist() throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        UUID categoryId = UUID.randomUUID();
        String newCategoryName = "Updated Category";
        CategoryRequest categoryRequest = new CategoryRequest(newCategoryName);

        when(categoryService.updateCategoryName(categoryId, newCategoryName)).thenThrow(new CategoryNotFoundByIdException(categoryId));

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryController.putCategoryName(categoryId, categoryRequest));
    }

    /**
     * Tests that deletePost deletes a category when it exists.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void deletePost_DeletesCategory_WhenCategoryExists() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.deleteCategory(categoryId)).thenReturn(true);

        ResponseEntity<Boolean> response = categoryController.deletePost(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Boolean.TRUE, response.getBody());
    }

    /**
     * Tests that deletePost throws an exception when the category does not exist.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void deletePost_ThrowsException_WhenCategoryDoesNotExist() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();

        when(categoryService.deleteCategory(categoryId)).thenThrow(new CategoryNotFoundByIdException(categoryId));

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryController.deletePost(categoryId));
    }

    /**
     * Tests that getPostsForCategory returns posts for a category when it exists.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void getPostsForCategory_ReturnsPosts_WhenCategoryExists() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();
        List<Post> posts = List.of(new Post("Title1", "Content1", new Category("Category")),
                new Post("Title2", "Content2", new Category("Category")));

        when(postService.getPostsByCategoryId(categoryId)).thenReturn(posts);

        ResponseEntity<List<Post>> response = categoryController.getPostsForCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    /**
     * Tests that getPostsForCategory throws an exception when the category does not exist.
     *
     * @throws CategoryNotFoundByIdException if the category is not found
     */
    @Test
    public void getPostsForCategory_ThrowsException_WhenCategoryDoesNotExist() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();

        when(postService.getPostsByCategoryId(categoryId)).thenThrow(new CategoryNotFoundByIdException(categoryId));

        assertThrows(CategoryNotFoundByIdException.class, () -> categoryController.getPostsForCategory(categoryId));
    }

}
