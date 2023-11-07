package com.epam.springcore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
    /**
     * The unique identifier of the user.
     */
	private Long id;
	
    /**
     * The first name of the user.
     */
	private String firstName;
	
    /**
     * The last name of the user.
     */
	private String lastName;
	
    /**
     * The username of the user.
     */
	private String userName;
	
    /**
     * The password of the user.
     */
	private String password;
	
    /**
     * Indicates whether the user is active.
     */
	private Boolean isActive;
}

