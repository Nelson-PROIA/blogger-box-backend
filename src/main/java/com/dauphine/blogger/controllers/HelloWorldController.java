package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * This class contains endpoints for greeting messages.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@RestController
@Tag(
        name = "Hello world API",
        description = "My first 'Hello world' endpoints"
)
public class HelloWorldController {

    /**
     * Returns a simple 'Hello World!' message.
     *
     * @return "Hello World!" message
     */
    @GetMapping("hello-world")
    @Operation(
            summary = "Hello world endpoint",
            description = "Returns 'Hello world!'"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public String helloWorld() {
        return "Hello world!";
    }

    /**
     * Returns a personalized greeting message using the provided request parameter.
     *
     * @param name (required) the name to include in the greeting
     * @return personalized greeting message
     */
    @GetMapping("hello")
    @Operation(
            summary = "Hello by name endpoint (from request param)",
            description = "Returns 'Hello {name}' from request param"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public String helloFromRequestParam(@RequestParam String name) {
        return "Hello " + name + " (from request param)!";
    }

    /**
     * Returns a personalized greeting message using the provided path variable.
     *
     * @param name (required) the name to include in the greeting
     * @return personalized greeting message
     */
    @GetMapping("hello/{name}")
    @Operation(
            summary = "Hello by name endpoint (by path variable)",
            description = "Returns 'Hello {name}' by path variable"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public String helloFromPathVariable(@PathVariable String name) {
        return "Hello " + name + " (by path variable)!";
    }

}
