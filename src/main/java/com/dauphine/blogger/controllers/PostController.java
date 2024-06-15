package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;
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
 * Controller class containing endpoints for managing blog posts.
 * </p>
 *
 * <p>
 * This controller provides endpoints to perform CRUD operations on blog posts,
 * including creating a new post, updating an existing post, deleting an existing post,
 * and retrieving all posts ordered by creation date.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@RestController
@RequestMapping("/v1/posts")
@Tag(
        name = "Posts API",
        description = "Endpoints for managing blog posts"
)
public class PostController {

    /**
     * Service for managing posts
     */
    private final PostService postService;

    /**
     * Constructor for the PostController class.
     * Initializes the PostController with the specified PostService.
     *
     * @param postService The PostService to be used by the controller
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Endpoint for retrieving all posts ordered by creation date.
     *
     * @param topic (optional) the topic to filter by
     * @return response containing a list of all posts ordered by creation date or filtered by topic
     */
    @GetMapping()
    @Operation(
            summary = "Retrieve all posts ordered by creation date",
            description = "Endpoint for retrieving all posts ordered by creation date"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) String topic) {
        List<Post> posts;

        if (topic != null) {
            posts = postService.getPostsByTopic(topic);
        } else {
            posts = postService.getPosts();
        }

        return ResponseEntity.ok(posts);
    }

    /**
     * Endpoint for creating a new post.
     *
     * @param postRequest the request body containing post details
     * @return response indicating the success or failure of the operation and the created post
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     */
    @PostMapping()
    @Operation(
            summary = "Create a new post",
            description = "Endpoint for creating a new post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<Post> postPost(@RequestBody PostRequest postRequest) throws CategoryNotFoundByIdException {
        final Post post = postService.createPost(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());

        return ResponseEntity.created(URI.create("/v1/posts/" + post.getId())).body(post);
    }

    /**
     * Endpoint for updating an existing post.
     *
     * @param id          the ID of the post to be updated
     * @param postRequest the request body containing updated post details
     * @return response indicating the success or failure of the operation and the updated post
     * @throws CategoryNotFoundByIdException if the specified category ID does not exist
     * @throws PostNotFoundByIdException     if the specified post ID does not exist
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing post",
            description = "Endpoint for updating an existing post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Post> putPost(@PathVariable UUID id, @RequestBody PostRequest postRequest) throws CategoryNotFoundByIdException, PostNotFoundByIdException {
        final Post post = postService.update(id, postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());

        return ResponseEntity.ok(post);
    }

    /**
     * Endpoint for deleting an existing post.
     *
     * @param id the ID of the post to be deleted
     * @return response indicating the success or failure of the operation
     * @throws PostNotFoundByIdException if the specified post ID does not exist
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an existing post",
            description = "Endpoint for deleting an existing post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id) throws PostNotFoundByIdException {
        final Boolean deletionSuccessful = postService.deletePost(id);

        return ResponseEntity.ok(deletionSuccessful);
    }

}
