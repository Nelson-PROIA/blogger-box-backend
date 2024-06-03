package com.dauphine.blogger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Main class to run the Blogger Box backend application.
 * </p>
 *
 * <p>
 * This class is annotated with {@link SpringBootApplication} to indicate that it's a Spring Boot application.
 * Additionally, it uses OpenAPI annotations to provide metadata about the application, such as title, description,
 * contact information, and version.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blogger Box backend",
                description = "Blogger Box backend endpoints and APIs",
                contact = @Contact(name = "Nelson", email = "nelson.proia@dauphine.eu"),
                version = "1.0.0"
        )
)
public class BloggerBoxApplication {

    /**
     * Main method to run the application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BloggerBoxApplication.class, args);
    }

}
