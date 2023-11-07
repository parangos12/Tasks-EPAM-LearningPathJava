package com.epam.springcore.service;

import java.util.Optional;
import com.epam.springcore.entity.Training;

/**
 * Interface defining the operations related to Trainings.
 */
public interface TrainingService {
	
    /**
     * Create a new Training.
     *
     * @param training The Training object to be created.
     * @return The created Training.
     */
	Training createTraining(Training training);
	
    /**
     * Select a Training by its ID.
     *
     * @param id The ID of the Training to be selected.
     * @return An Optional containing the selected Training if found, empty otherwise.
     */
	Optional<Training> selectTraining(Long id);
	
}
