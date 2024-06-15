package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * Controller class containing endpoints for managing blog categories.
 * </p>
 *
 * <p>
 * This controller provides endpoints to perform CRUD operations on blog categories,
 * including retrieving all categories, retrieving a category by ID, creating a new category,
 * updating the name of a category, and deleting an existing category. Additionally,
 * it allows retrieving all posts belonging to a specific category.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
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
     * Initializes the CategoryController with the specified CategoryService and PostService.
     *
     * @param categoryService The CategoryService to be used by the controller
     * @param postService     The PostService to be used by the controller
     */
    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    /**
     * Endpoint for retrieving all categories.
     *
     * @param name (optional) name of the category to filter by
     * @return response containing a list of all categories or filtered categories
     */
    @GetMapping()
    @Operation(
            summary = "Retrieve all categories",
            description = "Endpoint for retrieving all categories"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved categories"),
            @ApiResponse(responseCode = "404", description = "Categories not found")
    })
    public ResponseEntity<List<Category>> getCategories(@RequestParam(required = false) String name) {
        final List<Category> categories = name == null || name.isBlank()
                ? categoryService.getCategories()
                : categoryService.getCategoriesByName(name);

        return ResponseEntity.ok(categories);
    }

    /**
     * Endpoint for retrieving a category by ID.
     *
     * @param id the ID of the category to retrieve
     * @return response containing the category with the specified ID
     * @throws CategoryNotFoundByIdException if the category with the specified ID does not exist
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a category by ID",
            description = "Endpoint for retrieving a category by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Category> getCategory(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        final Category category = categoryService.getCategory(id);

        return ResponseEntity.ok(category);
    }

    /**
     * Endpoint for creating a new category.
     *
     * @param categoryRequest the request body containing category details
     * @return response indicating the success or failure of the operation and the created category
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    @PostMapping()
    @Operation(
            summary = "Create a new category",
            description = "Endpoint for creating a new category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<Category> postCategory(@RequestBody CategoryRequest categoryRequest) throws CategoryAlreadyExistsException {
        final Category category = categoryService.createCategory(categoryRequest.getName());

        return ResponseEntity.created(URI.create("v1/categories/" + category.getId())).body(category);
    }

    /**
     * Endpoint for updating the name of a category.
     *
     * @param id              the ID of the category to update
     * @param categoryRequest the request body containing updated category details
     * @return response indicating the success or failure of the operation and the updated category
     * @throws CategoryNotFoundByIdException  if the category with the specified ID is not found
     * @throws CategoryAlreadyExistsException if a category with the same name already exists
     */
    @PostMapping("/{id}")
    @Operation(
            summary = "Update an existing category",
            description = "Endpoint for updating an existing category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Category> patchCategoryName(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        final Category category = categoryService.updateCategoryName(id, categoryRequest.getName());

        return ResponseEntity.ok(category);
    }

    /**
     * Endpoint for deleting an existing category.
     *
     * @param id the ID of the category to be deleted
     * @return response indicating the success or failure of the operation
     * @throws CategoryNotFoundByIdException if the category with the specified ID is not found
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an existing category",
            description = "Endpoint for deleting an existing category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "409", description = "Constraint violation")
    })
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        final boolean deletionSuccessful = categoryService.deleteCategory(id);

        return ResponseEntity.ok(deletionSuccessful);
    }

    /**
     * Endpoint for retrieving all posts belonging to a specific category.
     *
     * @param id the ID of the category
     * @return response containing a list of all posts belonging to the specified category
     * @throws CategoryNotFoundByIdException if the category with the specified ID is not found
     */
    @GetMapping("/{id}/posts")
    @Operation(
            summary = "Retrieve all posts for a category",
            description = "Endpoint for retrieving all posts belonging to a specific category"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved posts for the category"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<List<Post>> getPostsForCategory(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        final List<Post> posts = postService.getPostsByCategoryId(id);

        return ResponseEntity.ok(posts);
    }

}
