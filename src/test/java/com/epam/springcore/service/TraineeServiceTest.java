package com.epam.springcore.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.epam.springcore.repository.TraineesLoader;
import com.epam.springcore.serviceImpl.TraineeServiceImpl;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class TraineeServiceTest {
	
    @InjectMocks
    private TraineeServiceImpl traineeService;

    @Mock
    private TraineesLoader traineesLoader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testCreateTrainee() {
	    // Create a trainee
        Trainee trainee = new Trainee(1L,Optional.of(new Date()),Optional.of("Medellin"),new User(1L,"Pedro","Arango","Pedro.Arango","giqXpd78",true));

	    // Create the trainee
        Trainee createdTrainee = traineeService.createTrainee(trainee);
        
        // Assert that created Trainee not null and equal to Trainee.
        assertNotNull(createdTrainee);
        assertEquals(trainee, createdTrainee);

    }

	@Test
	void testUpdateTrainee() {
	    // Create a trainee
	    Trainee trainee = new Trainee(1L, Optional.of(new Date()), Optional.of("Medellin"), new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXpd78", true));
	    
	    // Create the trainee
	    Trainee createdTrainee = traineeService.createTrainee(trainee);
	    
	    // Modify the trainee
	    createdTrainee.getUser().setLastName("Sanchez");
	    
	    // Update the trainee
	    traineeService.updateTrainee(createdTrainee);
	    
	    // Retrieve the updated trainee
	    Optional<Trainee> updatedTraineeOptional = traineeService.selectTrainee(createdTrainee.getId());
	    assertTrue(updatedTraineeOptional.isPresent());
	    
	    Trainee updatedTrainee = updatedTraineeOptional.get();
	    
	    // Assert that the last name has been updated
	    assertEquals("Sanchez", updatedTrainee.getUser().getLastName());

	}

	@Test
	void testDeleteTrainee() {
	    // Create a trainee
	    Trainee trainee = new Trainee(1L, Optional.of(new Date()), Optional.of("Medellin"), new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXpd78", true));
	    
	    // Create the trainee
	    Trainee createdTrainee = traineeService.createTrainee(trainee);
	    
	    // Delete the trainee
	    traineeService.deleteTrainee(createdTrainee.getId());
	    
	    // Attempt to select the deleted trainee
	    Optional<Trainee> deletedTrainee = traineeService.selectTrainee(createdTrainee.getId());
	    
	    // Assert that the deleted trainee is not found
	    assertFalse(deletedTrainee.isPresent());

	}

	@Test
	void testSelectTrainee() {
	    // Create a trainee
	    Trainee trainee = new Trainee(1L, Optional.of(new Date()), Optional.of("Medellin"), new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXpd78", true));
	    
	    // Create the trainee
	    Trainee createdTrainee = traineeService.createTrainee(trainee);
	    
	    // Retrieve the trainee
	    Optional<Trainee> selectedTraineeOptional = traineeService.selectTrainee(createdTrainee.getId());
	    
	    assertTrue(selectedTraineeOptional.isPresent());
	    
	    Trainee selectedTrainee = selectedTraineeOptional.get();
	    
	    // Assert that the retrieved trainee matches the created trainee
	    assertEquals(createdTrainee, selectedTrainee);

	}
} 
