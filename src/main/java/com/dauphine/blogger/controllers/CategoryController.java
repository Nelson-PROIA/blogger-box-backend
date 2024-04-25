package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller class containing endpoints for managing blog categories.
 * <p>
 * This controller provides endpoints to perform CRUD operations on blog categories,
 * including retrieving all categories, retrieving a category by ID, creating a new category,
 * updating the name of a category, and deleting an existing category. Additionally,
 * it allows retrieving all posts belonging to a specific category.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
@RestController
@RequestMapping("/v1/categories")
@Tag(
        name = "Categories API",
        description = "Endpoints for managing blog categories"
)
public class CategoryController {

    /**
     * The list of categories managed by this service.
     */
    private final List<Category> categories;

    /**
     * Initializes the controller with some temporary categories.
     */
    public CategoryController() {
        categories = new ArrayList<>();

        categories.add(new Category(UUID.randomUUID(), "Science-Fiction"));
        categories.add(new Category(UUID.randomUUID(), "Sport"));
        categories.add(new Category(UUID.randomUUID(), "Theatre"));
    }

    /**
     * Endpoint for retrieving all categories.
     *
     * @return response containing a list of all categories
     */
    @GetMapping()
    @Operation(
            summary = "Retrieve all categories",
            description = "Endpoint for retrieving all categories"
    )
    public List<Category> getCategories() {
        // TODO
        return null;
    }

    /**
     * Endpoint for retrieving a category by ID.
     *
     * @param id the ID of the category to retrieve
     * @return response containing the category with the specified ID
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a category by ID",
            description = "Endpoint for retrieving a category by ID"
    )
    public Category getCategory(@PathVariable UUID id) {
        // TODO
        return null;
    }

    /**
     * Endpoint for creating a new category.
     *
     * @param category the request body containing category details
     * @return response indicating the success or failure of the operation
     */
    @PostMapping()
    @Operation(
            summary = "Create a new category",
            description = "Endpoint for creating a new category"
    )
    public Category postCategory(@RequestBody CategoryRequest category) {
        // TODO
        return null;
    }

    /**
     * Endpoint for updating the name of a category.
     *
     * @param id       the ID of the category to update
     * @param category the request body containing updated category details
     * @return response indicating the success or failure of the operation
     */
    @PatchMapping("/{id}")
    @Operation(
            summary = "Update the name of a category",
            description = "Endpoint for updating the name of a category"
    )
    public Category patchCategoryName(@PathVariable UUID id, @RequestBody CategoryRequest category) {
        // TODO
        return null;
    }

    /**
     * Endpoint for deleting an existing category.
     *
     * @param id the ID of the category to be deleted
     * @return response indicating the success or failure of the operation
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an existing category",
            description = "Endpoint for deleting an existing category"
    )
    public boolean deletePost(@PathVariable UUID id) {
        // TODO
        return false;
    }

    /**
     * Endpoint for retrieving all posts belonging to a specific category.
     *
     * @param id the ID of the category
     * @return response containing a list of all posts belonging to the specified category
     */
    @GetMapping("/{id}/posts")
    @Operation(
            summary = "Retrieve all posts for a category",
            description = "Endpoint for retrieving all posts belonging to a specific category"
    )
    public List<Post> getPostsForCategory(@PathVariable UUID id) {
        // TODO
        return null;
    }

}
