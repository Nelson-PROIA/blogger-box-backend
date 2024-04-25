package com.dauphine.blogger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Blogger Box backend", // Title of the application
                description = "Blogger Box backend endpoints and APIs", // Description of the application
                contact = @Contact(name = "Nelson", email = "nelson.proia@dauphine.eu"), // Contact information
                version = "1.0.0" // Version of the application
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

