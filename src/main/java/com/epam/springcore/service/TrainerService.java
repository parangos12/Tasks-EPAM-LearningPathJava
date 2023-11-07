package com.epam.springcore.service;

import java.util.Optional;

import com.epam.springcore.entity.Trainer;

/**
 * Interface defining the operations related to Trainers.
 */
public interface TrainerService {
	
    /**
     * Create a new Trainer.
     *
     * @param trainer The Trainer object to be created.
     * @return The created Trainer.
     */
	Trainer createTrainer(Trainer trainer);
	
    /**
     * Update an existing Trainer.
     *
     * @param trainer The Trainer object to be updated.
     */
	void updateTrainer(Trainer trainer);
	
    /**
     * Select a Trainer by their ID.
     *
     * @param id The ID of the Trainer to be selected.
     * @return An Optional containing the selected Trainer if found, empty otherwise.
     */
	Optional<Trainer> selectTrainer(Long id);
}
