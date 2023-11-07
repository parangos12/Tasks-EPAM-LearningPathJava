package com.epam.springcore.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.TraineesLoader;
import com.epam.springcore.service.TraineeService;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service class for managing Trainee-related operations.
 */
@Service
public class TraineeServiceImpl implements TraineeService{

    private final Map<Long, Trainee> trainees;
    private final List<String> users;
    
    private final Logger logger=LoggerFactory.getLogger(TraineeServiceImpl.class);
    
    /**
     * Constructor for TraineeServiceImpl.
     *
     * @param traineesLoader A component responsible for loading Trainee data.
     */
    @Autowired
    public TraineeServiceImpl(TraineesLoader traineesLoader) {
        this.users=traineesLoader.getUsers().values().stream().map(User::getUserName).collect(Collectors.toList());
        this.trainees = traineesLoader.getTrainees();
        logger.info("Trainees mock data initialized");
    }
    
    /**
     * Create a new Trainee.
     *
     * @param trainee The Trainee object to be created.
     * @return The created Trainee.
     */
    @Override
    public Trainee createTrainee(Trainee trainee) {
    	trainee.getUser().setUserName(generateUserName(trainee));
    	trainee.getUser().setPassword(generateRandomPassword());
        trainees.put(trainee.getId(), trainee);
        users.add(generateUserName(trainee));
        logger.info("Created Trainee: {}",trainee);
        return trainee;
    }
    
    /**
     * Update an existing Trainee.
     *
     * @param trainee The Trainee object to be updated.
     */
    @Override
    public void updateTrainee(Trainee trainee) {
        trainees.put(trainee.getId(), trainee);
        logger.info("Updated Trainee: {}",trainee);
    }
    
    /**
     * Delete a Trainee by their ID.
     *
     * @param id The ID of the Trainee to be deleted.
     */
    @Override
    public void deleteTrainee(Long id) {
        trainees.remove(id);
        logger.info("Updated Trainee with ID: {}",id);
    }
    
    /**
     * Select a Trainee by their ID.
     *
     * @param id The ID of the Trainee to be selected.
     * @return An Optional containing the selected Trainee if found, empty otherwise.
     */
    @Override
    public Optional<Trainee> selectTrainee(Long id) {
    	Trainee selectedTrainee=trainees.get(id);
    	if(selectedTrainee!=null) {
    		logger.info("Select Trainee with ID: {})",id);
    	}else {
    		logger.info("Trainee with ID Not Found: {})",id);
    	}
        return Optional.ofNullable(trainees.get(id));}
        
    /**
     * Generate a unique username for a Trainee.
     *
     * @param trainee The Trainee for which the username is generated.
     * @return The generated username.
     */
   public String generateUserName(Trainee trainee) {
	   String firstName = trainee.getUser().getFirstName(); 
	   String lastName = trainee.getUser().getLastName();
	   String userName = firstName + "." + lastName; 
	   int serialNumber=0;	
	   
	   while(users.contains(userName)) {
		   serialNumber++;
		   userName=firstName + "." + lastName + "_" + serialNumber;
	   }
	return userName;
   }
   
   /**
    * Generate a random password for a Trainee.
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
