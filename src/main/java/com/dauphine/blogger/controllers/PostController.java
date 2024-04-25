package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller class containing endpoints for managing blog posts.
 * <p>
 * This controller provides endpoints to perform CRUD operations on blog posts,
 * including creating a new post, updating an existing post, deleting an existing post,
 * and retrieving all posts ordered by creation date.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
@RestController
@RequestMapping("/v1/posts")
@Tag(
        name = "Posts API",
        description = "Endpoints for managing blog posts"
)
public class PostController {

    /**
     * The list of posts managed by this service.
     */
    List<Post> posts;

    /**
     * Initializes the controller with some temporary posts.
     */
    public PostController() {
        posts = new ArrayList<>();

        posts.add(new Post(UUID.randomUUID(), "Title A", "Content A", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
        posts.add(new Post(UUID.randomUUID(), "Title B", "Content B", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
        posts.add(new Post(UUID.randomUUID(), "Title C", "Content C", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
    }

    /**
     * Endpoint for retrieving all posts ordered by creation date.
     *
     * @return response containing a list of all posts ordered by creation date
     */
    @GetMapping()
    @Operation(
            summary = "Retrieve all posts ordered by creation date",
            description = "Endpoint for retrieving all posts ordered by creation date"
    )
    public List<Post> getPosts() {
        // TODO
        return null;
    }

    /**
     * Endpoint for creating a new post.
     *
     * @param post the request body containing post details
     * @return response indicating the success or failure of the operation
     */
    @PostMapping()
    @Operation(
            summary = "Create a new post",
            description = "Endpoint for creating a new post"
    )
    public Post postPost(@RequestBody PostRequest post) {
        // TODO
        return null;
    }

    /**
     * Endpoint for updating an existing post.
     *
     * @param id   the ID of the post to be updated
     * @param post the request body containing updated post details
     * @return response indicating the success or failure of the operation
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing post",
            description = "Endpoint for updating an existing post"
    )
    public Post putPost(@PathVariable UUID id, @RequestBody PostRequest post) {
        // TODO
        return null;
    }

    /**
     * Endpoint for deleting an existing post.
     *
     * @param id the ID of the post to be deleted
     * @return response indicating the success or failure of the operation
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an existing post",
            description = "Endpoint for deleting an existing post"
    )
    public boolean deletePost(@PathVariable UUID id) {
        // TODO
        return false;
    }

}
