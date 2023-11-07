package com.epam.springcore.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class responsible for loading application properties.
 *
 * This class is annotated as a Spring Configuration, and it specifies the location of the
 * application properties file using the @PropertySource annotation. The class contains
 * a constructor for initializing the configuration.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class Config {
    private final Logger logger=LoggerFactory.getLogger(Config.class);
    
    /**
     * Constructor for the Config class.
     *
     * This constructor initializes the configuration and logs a message to indicate its
     * initialization.
     */
    public Config() {
    	logger.info("Config class is being initializeded configuring the path of app properties");
    }

}
