package com.epam.springcore.entity;

import java.util.Date;
import java.util.Optional;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a trainee entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {
	
    /**
     * The unique identifier of the trainee.
     */
	private Long id;
	
    /**
     * The date of birth of the trainee (optional).
     */
	private Optional<Date> dateOfBirth;
	
    /**
     * The address of the trainee (optional).
     */
	private Optional<String> address;
	
    /**
     * The user associated with the trainee.
     */
	private User user;
	
}
