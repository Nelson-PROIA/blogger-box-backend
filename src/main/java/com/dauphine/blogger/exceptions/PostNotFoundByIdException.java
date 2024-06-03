package com.dauphine.blogger.exceptions;

import java.util.UUID;

/**
 * <p>
 * Exception thrown when a post with the specified ID is not found.
 * </p>
 *
 * <p>
 * This exception is used to indicate that the requested post could not be found in the repository.
 * It extends {@link Exception}, so it is an checked exception.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class PostNotFoundByIdException extends Exception {

    /**
     * Constructs a new PostNotFoundByIdException with the specified post ID.
     *
     * @param id The ID of the post that was not found
     */
    public PostNotFoundByIdException(UUID id) {
        super("Post with id " + id + " does not exist!");
    }

}
