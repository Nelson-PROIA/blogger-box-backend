package com.dauphine.blogger.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * Unit tests for the HelloWorldController class.
 * These tests validate the functionality of HelloWorldController's endpoints for greeting messages.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
public class HelloWorldControllerTest {

    /**
     * The controller being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private HelloWorldController helloWorldController;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that helloWorld returns the 'Hello world!' message.
     */
    @Test
    public void helloWorld_ReturnsHelloWorld() {
        String result = helloWorldController.helloWorld();
        assertEquals("Hello world!", result);
    }

    /**
     * Tests that helloFromRequestParam returns a personalized greeting message using the request parameter.
     */
    @Test
    public void helloFromRequestParam_ReturnsPersonalizedGreeting() {
        String name = "John";
        String result = helloWorldController.helloFromRequestParam(name);

        assertEquals("Hello John (from request param)!", result);
    }

    /**
     * Tests that helloFromPathVariable returns a personalized greeting message using the path variable.
     */
    @Test
    public void helloFromPathVariable_ReturnsPersonalizedGreeting() {
        String name = "John";
        String result = helloWorldController.helloFromPathVariable(name);

        assertEquals("Hello John (by path variable)!", result);
    }

}
