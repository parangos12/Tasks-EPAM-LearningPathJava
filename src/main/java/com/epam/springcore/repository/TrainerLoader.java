package com.epam.springcore.repository;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.User;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.annotation.PostConstruct;

/**
 * Component responsible for loading trainer data from a CSV file.
 *
 * This component loads trainer data from a CSV file, parses the data, and stores it in a
 * map. It utilizes the UsersLoader component to retrieve user information for trainers.
 */
@Component
public class TrainerLoader {
	
    private Map<Long, Trainer> trainers = new HashMap<Long, Trainer>();
    private final Logger logger=LoggerFactory.getLogger(TrainerLoader.class);    
    private Map<Long, User> users;

    @Value("${csv.trainer.file.path}")
    private String csvTrainerFilePath;

	private UsersLoader usersLoader;
	
	@Autowired
	public void setUsersLoader(UsersLoader usersLoader) {
		this.usersLoader=usersLoader;}

    /**
     * Load trainer data from a CSV file.
     *
     * This method reads data from a CSV file, parses it, and creates Trainer objects.
     * It also associates each Trainer with a User. The loaded data is stored in a map
     * for further use.
     *
     * @throws IOException If an error occurs while reading the CSV file.
     */
    @PostConstruct
    public void loadTrainersMock() {
        try {
			users = usersLoader.getUsers();
            BufferedReader reader = new BufferedReader(new FileReader(csvTrainerFilePath));
            String row;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length == 3) {
                    Long id = Long.parseLong(data[0]);
                    Long specialization = Long.parseLong(data[1]);
                    Long userId = Long.parseLong(data[2]);
                    Trainer trainer = new Trainer(id, specialization,this.users.get(id));
                    trainers.put(id, trainer);
                }
            }
        } catch (IOException e) {
        	logger.error("An error ocurred loading the trainer mock");
        }
    }
    
    /**
     * Get the map of loaded trainers.
     *
     * @return A map containing loaded trainer data.
     */
    public Map<Long, Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Get the map of users associated with trainers.
     *
     * @return A map containing user data for trainers.
     */
	public Map<Long, User> getUsers() {
		return users;
	}
}


