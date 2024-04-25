package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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
     * Service for managing categories
     */
    private final CategoryService categoryService;

    /**
     * Service for managing posts
     */
    private final PostService postService;

    /**
     * Constructor for the CategoryController class.
     * <p>
     * Initializes the CategoryController with the specified CategoryService and PostService.
     *
     * @param categoryService The CategoryService to be used by the controller
    * @param postService The PostService to be used by the controller
     */
    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
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
        return categoryService.getCategories();
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
        return categoryService.getCategory(id);
    }

    /**
     * Endpoint for creating a new category.
     *
     * @param categoryRequest the request body containing category details
     * @return response indicating the success or failure of the operation
     */
    @PostMapping()
    @Operation(
            summary = "Create a new category",
            description = "Endpoint for creating a new category"
    )
    public Category postCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest.getCategoryName());

        // TODO

        return category;
    }

    /**
     * Endpoint for updating the name of a category.
     *
     * @param id              the ID of the category to update
     * @param categoryRequest the request body containing updated category details
     * @return response indicating the success or failure of the operation
     */
    @PatchMapping("/{id}")
    @Operation(
            summary = "Update the name of a category",
            description = "Endpoint for updating the name of a category"
    )
    public Category patchCategoryName(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.updateCategoryName(id, categoryRequest.getCategoryName());

        // TODO

        return category;
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
        return categoryService.deleteCategory(id);
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
        return postService.getPostsByCategoryId(id);
    }

}
