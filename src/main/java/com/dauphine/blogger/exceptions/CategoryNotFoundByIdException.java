package com.dauphine.blogger.exceptions;

import java.util.UUID;

/**
 * <p>
 * Exception thrown when a category with the specified ID is not found.
 * </p>
 *
 * <p>
 * This exception is used to indicate that the requested category could not be found in the repository.
 * It extends {@link Exception}, so it is an checked exception.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class CategoryNotFoundByIdException extends Exception {

    /**
     * Constructs a new CategoryNotFoundByIdException with the specified category ID.
     *
     * @param id The ID of the category that was not found
     */
    public CategoryNotFoundByIdException(UUID id) {
        super("Category with id " + id + " does not exist!");
    }

}
