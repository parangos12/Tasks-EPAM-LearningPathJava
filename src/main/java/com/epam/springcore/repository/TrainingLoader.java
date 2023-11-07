package com.epam.springcore.repository;

import com.epam.springcore.entity.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;

/**
 * Component responsible for loading training data from a CSV file.
 *
 * This component loads training data from a CSV file, parses the data, and stores it in a map.
 */
@Component
public class TrainingLoader {
    private Map<Long, Training> trainings = new HashMap<>();
    private final Logger logger=LoggerFactory.getLogger(TrainingLoader.class);

    @Value("${csv.training.file.path}")
    private String csvFilePath;
    
    /**
     * Load training data from a CSV file.
     *
     * This method reads data from a CSV file, parses it, and creates Training objects. The loaded
     * data is stored in a map for further use.
     *
     * @throws IOException    If an error occurs while reading the CSV file.
     * @throws ParseException If date parsing from the CSV data fails.
     */
    @PostConstruct
    public void loadTrainingsMock() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String row;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(","); 
                if (data.length == 7) {
                    Long id = Long.parseLong(data[0]);
                    Long traineeID = Long.parseLong(data[1]);
                    Long trainerID = Long.parseLong(data[2]);
                    String trainingName = data[3];
                    Long trainingTypeID = Long.parseLong(data[4]);
                    Date trainingDate = simpleDateFormat(data[5]);
                    float trainingDuration = Float.parseFloat(data[6]);
                    Training training = new Training(id, traineeID, trainerID, trainingName, trainingTypeID, trainingDate, trainingDuration);
                    trainings.put(id, training);
                }
            }
        } catch (IOException | ParseException e) {
        	logger.error("An error ocurred loading the trainer mock");
        }
    }
    /**
     * Parse date from a string.
     *
     * @param dateStr The date in string format (MM/dd/yyyy).
     * @return A Date object representing the parsed date.
     * @throws ParseException If date parsing fails.
     */
	private Date simpleDateFormat(String dateStr) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.parse(dateStr);
	}

    /**
     * Get the map of loaded training data.
     *
     * @return A map containing loaded training data.
     */
    public Map<Long, Training> getTrainings() {
        return trainings;
    }
}
