package com.epam.springcore.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TrainerLoader;
import com.epam.springcore.service.TrainerService;

/**
 * Service class for managing Trainer-related operations.
 */
@Service
public class TrainerServiceImpl implements TrainerService{
	
    private final Map<Long, Trainer> trainers;
    private final List<String> users;
    private final Logger logger=LoggerFactory.getLogger(TrainerServiceImpl.class);
    
    /**
     * Constructor for TrainerServiceImpl.
     *
     * @param trainersLoader A component responsible for loading Trainer data.
     */
    @Autowired
    public TrainerServiceImpl(TrainerLoader trainersLoader) {
        this.users=trainersLoader.getUsers().values().stream().map(User::getUserName).collect(Collectors.toList());
        this.trainers = trainersLoader.getTrainers();
        logger.info("Trainers mock data initialized");
    }
    
    /**
     * Create a new Trainer.
     *
     * @param trainer The Trainer object to be created.
     * @return The created Trainer.
     */
    @Override
    public Trainer createTrainer(Trainer trainer) {
    	trainer.getUser().setUserName(generateUserName(trainer));
    	trainer.getUser().setPassword(generateRandomPassword());
        trainers.put(trainer.getId(), trainer);
        users.add(generateUserName(trainer));
        logger.info("Created Trainer: {}",trainer);
        return trainer;
    }
    
    /**
     * Update an existing Trainer.
     *
     * @param trainer The Trainer object to be updated.
     */
    @Override
    public void updateTrainer(Trainer trainer) {
        trainers.put(trainer.getId(), trainer);
        logger.info("Updated Trainer: {}",trainer);
    }
    
    /**
     * Select a Trainer by their ID.
     *
     * @param id The ID of the Trainer to be selected.
     * @return An Optional containing the selected Trainer if found, empty otherwise.
     */
    @Override
    public Optional<Trainer> selectTrainer(Long id) {
    	Trainer selectedTrainer=trainers.get(id);
    	if(selectedTrainer!=null) {
    		logger.info("Select Trainer with ID: {})",id);
    	}else {
    		logger.info("Trainer with ID Not Found: {})",id);
    	}
        return Optional.ofNullable(trainers.get( id));
    }
    
    /**
     * Generate a unique username for a Trainer.
     *
     * @param trainer The Trainer for which the username is generated.
     * @return The generated username.
     */
    public String generateUserName(Trainer trainer) {
 	   String firstName = trainer.getUser().getFirstName(); 
 	   String lastName = trainer.getUser().getLastName();
 	   String userName = firstName + "." + lastName; 
 	   int serialNumber=0;	
 	   
 	   while(users.contains(userName)) {
 		   serialNumber++;
 		   userName=firstName + "." + lastName + "_" + serialNumber;
 	   }
 	return userName;
    }
    
    /**
     * Generate a random password for a Trainer.
     *
     * @return The generated random password.
     */   
    public String generateRandomPassword() {
 	   int leftLimit = 48; // 0
 	   int rightLimit = 122; // z
 	   int passwordLength = 10;
 	   
 	   Random random = new Random();

 	   return random.ints(leftLimit, rightLimit + 1)
 	       .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
 	       .limit(passwordLength)
 	       .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
 	       .toString();}

}
