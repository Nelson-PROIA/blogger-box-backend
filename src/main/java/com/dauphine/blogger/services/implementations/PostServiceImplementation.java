package com.dauphine.blogger.services.implementations;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the PostService interface.
 * <p>
 * This class provides methods to manage blog posts, including retrieving, creating, updating,
 * and deleting posts.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.eu
 */
@Service
public class PostServiceImplementation implements PostService {

    /**
     * The list of posts managed by this service.
     */
    List<Post> posts;

    /**
     * Constructs a new PostServiceImplementation object and initializes the list of posts.
     */
    public PostServiceImplementation() {
        posts = new ArrayList<>();

        posts.add(new Post(UUID.randomUUID(), "Title A", "Content A", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
        posts.add(new Post(UUID.randomUUID(), "Title B", "Content B", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
        posts.add(new Post(UUID.randomUUID(), "Title C", "Content C", new Timestamp(System.currentTimeMillis()), new Category(UUID.randomUUID(), "null")));
    }

    /**
     * Retrieves all posts.
     *
     * @return A list of all posts
     */
    @Override
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Retrieves posts belonging to a specific category.
     *
     * @param categoryId The ID of the category
     * @return A list of posts belonging to the specified category
     */
    @Override
    public List<Post> getPostsByCategoryId(UUID categoryId) {
        return posts.stream()
                .filter(post -> categoryId.equals(post.getCategory().getId()))
                .toList();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post
     * @return The post with the specified ID, or null if not found
     */
    @Override
    public Post getPost(UUID id) {
        return posts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates a new post with the specified title, content, and category ID.
     *
     * @param title      The title of the post
     * @param content    The content of the post
     * @param categoryId The ID of the category
     * @return The newly created post
     */
    @Override
    public Post createPost(String title, String content, UUID categoryId) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());

        Category category = new Category(UUID.randomUUID(), "null");

        Post post = new Post(UUID.randomUUID(), title, content, createdDate, category);

        posts.add(post);

        return post;
    }

    /**
     * Updates an existing post with the specified ID, title, content, and category ID.
     *
     * @param id         The ID of the post to update
     * @param title      The new title for the post
     * @param content    The new content for the post
     * @param categoryId The ID of the category
     * @return The updated post
     */
    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Category category = new Category(UUID.randomUUID(), "null");

        Post post = getPost(id);

        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);

        return post;
    }

    /**
     * Deletes a post with the specified ID.
     *
     * @param id The ID of the post to delete
     * @return true if the post was deleted successfully, false otherwise
     */
    @Override
    public boolean deletePost(UUID id) {
        return posts.removeIf(post -> id.equals(post.getId()));
    }

}
