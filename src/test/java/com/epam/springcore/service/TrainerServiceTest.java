package com.epam.springcore.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.epam.springcore.entity.User;

import com.epam.springcore.entity.Trainer;
import com.epam.springcore.repository.TrainerLoader;
import com.epam.springcore.serviceImpl.TrainerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class TrainerServiceTest {

    @InjectMocks
    private TrainerServiceImpl trainerService;

    @Mock
    private TrainerLoader trainerLoader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	
    @Test
    void testCreateTrainer() {
        // Create a trainer
        Trainer trainer = new Trainer(1L, 1L, new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXPD78", true));
        
        // Call the createTrainer method
        Trainer createdTrainer = trainerService.createTrainer(trainer);
        
        // Assert that the created trainer is not null
        assertNotNull(createdTrainer);
        
        // Optionally, you can assert that the trainer is equal to the created trainer
        assertEquals(trainer, createdTrainer);
        
        // You can also assert that the created trainer has a non-null ID
        assertNotNull(createdTrainer.getId());
    }

	@Test
	void testUpdateTrainer() {
	    // Create a trainer
        Trainer trainer = new Trainer(1L, 1L, new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXPD78", true));
	    
	    // Create the trainer
	    Trainer createdTrainer = trainerService.createTrainer(trainer);
	    
	    // Modify the trainer
	    createdTrainer.getUser().setLastName("Sanchez");
	    
	    // Update the trainer
	    trainerService.updateTrainer(createdTrainer);
	    
	    // Retrieve the updated trainer
	    Optional<Trainer> updatedTrainerOptional = trainerService.selectTrainer(createdTrainer.getId());
	    assertTrue(updatedTrainerOptional.isPresent());
	    
	    Trainer updatedTrainer = updatedTrainerOptional.get();
	    
	    // Assert that the last name has been updated
	    assertEquals("Sanchez", updatedTrainer.getUser().getLastName());
	}

	@Test
	void testSelectTrainer() {
	    // Create a trainer
        Trainer trainer = new Trainer(1L, 1L, new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXPD78", true));
	    
	    // Create the trainer
	    Trainer createdTrainer = trainerService.createTrainer(trainer);
	    
	    // Retrieve the trainer
	    Optional<Trainer> selectedTrainerOptional = trainerService.selectTrainer(createdTrainer.getId());
	    
	    assertTrue(selectedTrainerOptional.isPresent());
	    
	    Trainer selectedTrainer = selectedTrainerOptional.get();
	    
	    // Assert that the retrieved trainer matches the created trainer
	    assertEquals(createdTrainer, selectedTrainer);
	}
	
	
	
	

}
