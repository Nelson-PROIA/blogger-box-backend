package com.dauphine.blogger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * Web configuration class for the Blogger application.
 * </p>
 *
 * <p>
 * This class configures the Cross-Origin Resource Sharing (CORS) settings for the application.
 * </p>
 *
 * <p>
 * By enabling CORS, the application allows requests from the specified origins to access resources on the server.
 * </p>
 *
 * <p>
 * The configuration is enabled using the {@link EnableWebMvc} annotation.
 * </p>
 *
 * <p>
 * The CORS settings are applied to all endpoints (/**) and allow requests from "<a href="http://localhost:4200">...</a>".
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     *
     * @param registry the {@link CorsRegistry} to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }

}
