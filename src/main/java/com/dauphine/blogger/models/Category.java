package com.dauphine.blogger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * <p>
 * Represents a category in a blog.
 * This class encapsulates the properties of a blog category, including its unique identifier and name.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@Entity
@Table(name = "category")
public class Category {

    /**
     * The unique identifier of the category.
     */
    @Id
    @Column(name = "id")
    private UUID id;

    /**
     * The name of the category.
     */
    @Column(name = "name")
    private String name;

    /**
     * Default constructor.
     * Initializes id and name to null.
     */
    public Category() {
        this.id = null;
        this.name = null;
    }

    /**
     * Constructs a Category object with the specified ID and name.
     *
     * @param id   The unique identifier of the category
     * @param name The name of the category
     */
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructs a Category object with a random UUID and the specified name.
     *
     * @param name The name of the category
     */
    public Category(String name) {
        this(UUID.randomUUID(), name);
    }

    /**
     * Retrieves the unique identifier of the category.
     *
     * @return The ID of the category
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param id The new ID for the category
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The new name for the category
     */
    public void setName(String name) {
        this.name = name;
    }

}
