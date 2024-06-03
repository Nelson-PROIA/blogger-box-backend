package com.dauphine.blogger.exceptions;

/**
 * <p>
 * Exception thrown when attempting to create a category with a name that already exists.
 * </p>
 * <p>
 * This exception is used to indicate that a category with the specified name already exists in the repository,
 * preventing the creation of a duplicate category.
 * It extends {@link Exception}, so it is an checked exception.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class CategoryAlreadyExistsException extends Exception {

    /**
     * Constructs a new CategoryAlreadyExistsException with the specified category name.
     *
     * @param name The name of the category that already exists
     */
    public CategoryAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists!");
    }

}
