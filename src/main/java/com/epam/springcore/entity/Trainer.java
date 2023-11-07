package com.epam.springcore.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a trainer entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    /**
     * The unique identifier of the trainer.
     */
	private Long id;
	
    /**
     * The specialization of the trainer.
     */
	private Long specialization;
	
    /**
     * The user associated with the trainer.
     */
	private User user;
}
