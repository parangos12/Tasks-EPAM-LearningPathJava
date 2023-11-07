package com.epam.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.Trainer;
import com.epam.springcore.entity.Training;
import com.epam.springcore.entity.User;
import com.epam.springcore.service.TraineeService;
import com.epam.springcore.service.TrainerService;
import com.epam.springcore.service.TrainingService;

import java.util.Date;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.epam");
        
        // Get instances of the services
        TraineeService traineeService = ctx.getBean(TraineeService.class);
        TrainerService trainerService = ctx.getBean(TrainerService.class);
        TrainingService trainingService = ctx.getBean(TrainingService.class);

        //TraineeService
        Trainee trainee = new Trainee(1L,Optional.of(new Date()),Optional.of("Medellin"),new User(1L,"Pedro","Arango","Pedro.Arango","giqXpd78",true));
        traineeService.createTrainee(trainee);
        trainee.setAddress(Optional.of("Apartadó"));
        System.out.println("Trainee created: " + trainee);    //Testing Create method
        traineeService.updateTrainee(trainee);
        System.out.println("Trainee updated: " + trainee);	//Testing Update Method
        traineeService.deleteTrainee(2L);
        System.out.println("Trainee with ID " + 2L + " deleted."); //Testing Delete method
        Optional<Trainee> selectedTrainee = traineeService.selectTrainee(2L);
        if (selectedTrainee.isPresent()) {
            System.out.println("Selected Trainee: " + selectedTrainee.get());  //Testing Select Method
        } else {
            System.out.println("Trainee with ID " + 2L + " not found.");
        }

        //TrainerService
        Trainer trainer = new Trainer(1L, 1L, new User(1L, "Pedro", "Arango", "Pedro.Arango", "giqXPD78", true));
        trainerService.createTrainer(trainer);
        System.out.println("Trainer created: " + trainer);     //Testing Create method
        trainer.setSpecialization(3L);
        System.out.println("Trainer updated: " + trainer);     //Testing Update Method
        Optional<Trainer> selectedTrainer = trainerService.selectTrainer(3L);
        if (selectedTrainer.isPresent()) {
            System.out.println("Selected Trainer: " + selectedTrainer.get());   //Testing Select Method
        } else {
            System.out.println("Trainer with ID " + 3L + " not found.");
        }

        //TrainingService
        Training training = new Training(1L, 1L, 1L, "Piernas", 1L, new Date(), 2.5f);
        trainingService.createTraining(training);             
        System.out.println("Training created: " + training);          //Testing Create method
        Optional<Training> selectedTraining = trainingService.selectTraining(3L);   
        if (selectedTraining.isPresent()) {
            System.out.println("Selected Training: " + selectedTraining.get());   //Testing Select Method
        } else {
            System.out.println("Training with ID " + 3L + " not found."); 
        }

    }
}
