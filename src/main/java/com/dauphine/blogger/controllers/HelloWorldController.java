/**
 * This class contains endpoints for greeting messages.
 * <p>
 * Author: Nelson PROIA
 * Email: nelson.proia@dauphine.com
 */
package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    /**
     * Returns a simple "Hello World!" message.
     *
     * @return "Hello World!" message
     */
    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    /**
     * Returns a personalized greeting message using the provided request parameter.
     *
     * @param name the name to include in the greeting
     * @return personalized greeting message
     */
    @GetMapping("hello")
    public String helloFromRequestParam(@RequestParam String name) {
        return "Hello " + name + " (from request param)!";
    }

    /**
     * Returns a personalized greeting message using the provided path variable.
     *
     * @param name the name to include in the greeting
     * @return personalized greeting message
     */
    @GetMapping("hello/{name}")
    public String helloFromPathVariable(@PathVariable String name) {
        return "Hello " + name + " (from path variable)!";
    }

}
