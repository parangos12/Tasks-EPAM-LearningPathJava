package com.epam.springcore.service;

import java.util.Optional;

import com.epam.springcore.entity.Trainee;

/**
 * Interface defining the operations related to Trainees.
 */
public interface TraineeService {
	
    /**
     * Create a new Trainee.
     *
     * @param trainee The Trainee object to be created.
     * @return The created Trainee.
     */
	Trainee createTrainee(Trainee trainee);
	
    /**
     * Update an existing Trainee.
     *
     * @param trainee The Trainee object to be updated.
     */
	void updateTrainee(Trainee trainee);
	
    /**
     * Delete a Trainee by their ID.
     *
     * @param id The ID of the Trainee to be deleted.
     */
	void deleteTrainee(Long id);
	
    /**
     * Select a Trainee by their ID.
     *
     * @param id The ID of the Trainee to be selected.
     * @return An Optional containing the selected Trainee if found, empty otherwise.
     */
	Optional<Trainee> selectTrainee(Long id);
	
}
