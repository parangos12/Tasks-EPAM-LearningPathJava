package com.epam.springcore.entity;

import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a training entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {
	
    /**
     * The unique identifier of the training.
     */
	private Long id;
	
    /**
     * The ID of the trainee associated with the training.
     */
	private Long traineeID;
	
    /**
     * The ID of the trainer associated with the training.
     */
	private Long trainerID;
	
    /**
     * The name of the training.
     */
	private String trainingName;
	
    /**
     * The ID of the training type.
     */
	private Long trainingTypeID;
	
    /**
     * The date of the training.
     */
	private Date trainingDate;
	
    /**
     * The duration of the training (in hours).
     */
	private float trainingDuration;
}
