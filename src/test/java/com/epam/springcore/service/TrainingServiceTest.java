package com.epam.springcore.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import com.epam.springcore.repository.TrainingLoader;
import com.epam.springcore.serviceImpl.TrainingServiceImpl;
import com.epam.springcore.entity.Training;

@RunWith(MockitoJUnitRunner.class)
class TrainingServiceTest {

    @InjectMocks
    private TrainingServiceImpl trainingService;

    @Mock
    private TrainingLoader trainingLoader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
    @Test
    void testCreateTraining() {
        // Create a training
        Training training = new Training(1L, 1L, 1L, "Piernas", 1L, new Date(), 2.5f);
        
        // Call the createTraining method
        Training createdTraining = trainingService.createTraining(training);
        
        // Assert that the created training is not null
        assertNotNull(createdTraining);
        
        // Optionally, you can assert that the created training has a non-null ID
        assertNotNull(createdTraining.getId());
    }

    @Test
    void testSelectTraining() {
        // Create a training
        Training training = new Training(1L, 1L, 1L, "Piernas", 1L, new Date(), 2.5f);
        
        // Call the createTraining method
        Training createdTraining = trainingService.createTraining(training);
        
        // Retrieve the training
        Optional<Training> selectedTrainingOptional = trainingService.selectTraining(createdTraining.getId());
        
        assertTrue(selectedTrainingOptional.isPresent());
        
        Training selectedTraining = selectedTrainingOptional.get();
        
        // Assert that the retrieved training matches the created training
        assertEquals(createdTraining, selectedTraining);
    }

}
