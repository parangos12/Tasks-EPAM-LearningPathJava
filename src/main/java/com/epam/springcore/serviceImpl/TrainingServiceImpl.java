package com.epam.springcore.serviceImpl;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.springcore.entity.Training;
import com.epam.springcore.repository.TrainingLoader;
import com.epam.springcore.service.TrainingService;

/**
 * Service class for managing Training-related operations.
 */
@Service
public class TrainingServiceImpl implements TrainingService{

    private final Map<Long, Training> trainings;
    private final Logger logger=LoggerFactory.getLogger(TrainingServiceImpl.class);
    
    /**
     * Constructor for TrainingServiceImpl.
     *
     * @param trainingLoader A component responsible for loading Training data.
     */
    @Autowired
    public TrainingServiceImpl(TrainingLoader trainingLoader) {
        this.trainings = trainingLoader.getTrainings();
        logger.info("Trainers mock data initialized");

    }
    
    /**
     * Create a new Training.
     *
     * @param training The Training object to be created.
     * @return The created Training.
     */    
    @Override
    public Training createTraining(Training training) {
        trainings.put(training.getId(), training);
        logger.info("Created Trainer: {}",training);
        return training;
    }
    
    /**
     * Select a Training by its ID.
     *
     * @param id The ID of the Training to be selected.
     * @return An Optional containing the selected Training if found, empty otherwise.
     */
    @Override
    public Optional<Training> selectTraining(Long id) {
    	Training selectedTraining=trainings.get(id);
    	if(selectedTraining!=null) {
    		logger.info("Select Training with ID: {})",id);
    	}else {
    		logger.info("Training with ID Not Found: {})",id);
    	}
        return Optional.ofNullable(trainings.get(id));
    }

}
