package com.epam.springcore.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.springcore.entity.User;

/**
 * Component responsible for loading user data from a CSV file.
 *
 * This component loads user data from a CSV file, parses the data, and stores it in a map.
 */
@Component
public class UsersLoader {
	
    private Map<Long, User> users = new HashMap<>();
    private final Logger logger=LoggerFactory.getLogger(UsersLoader.class);

	@Value("${csv.user.file.path}")
	private String csvUserFilePath;
	
    /**
     * Load user data from a CSV file.
     *
     * This method reads data from a CSV file, parses it, and creates User objects. The loaded
     * data is stored in a map for further use.
     *
     * @throws IOException If an error occurs while reading the CSV file.
     */
	@PostConstruct
    public void loadUsersMock() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvUserFilePath));
            String row;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length == 6) {
                    Long id = Long.parseLong(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String userName = data[3];
                    String password = data[4];
                    Boolean isActive = Boolean.parseBoolean(data[5]);
                    User user = new User(id, firstName, lastName, userName, password, isActive);
                    users.put(id, user);
                }
            }
        } catch (IOException e) {
            logger.error("An error occurred loading the users mock");
        }
    }
	
    /**
     * Get the map of loaded user data.
     *
     * @return A map containing loaded user data.
     */
	public Map<Long, User> getUsers() {
		return users;
	}

    
}
