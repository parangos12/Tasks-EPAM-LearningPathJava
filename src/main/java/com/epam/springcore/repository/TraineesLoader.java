package com.epam.springcore.repository;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.epam.springcore.entity.Trainee;
import com.epam.springcore.entity.User;

/**
 * Component responsible for loading trainee data from a CSV file.
 *
 * This component loads trainee data from a CSV file, parses the data, and stores it in a
 * map. It utilizes the UsersLoader component to retrieve user information for trainees.
 */
@Component
public class TraineesLoader {
	
    private Map<Long, Trainee> trainees = new HashMap<Long, Trainee>();
    private final Logger logger=LoggerFactory.getLogger(TraineesLoader.class);
    private Map<Long, User> users;
    
	@Value("${csv.trainee.file.path}")
	private String csvFilePath;
	
	private UsersLoader usersLoader;
	
	@Autowired
	public void setUsersLoader(UsersLoader usersLoader) {
		this.usersLoader=usersLoader;}
	
    /**
     * Load trainee data from a CSV file.
     *
     * This method reads data from a CSV file, parses it, and creates Trainee objects.
     * It also associates each Trainee with a User. The loaded data is stored in a map
     * for further use.
     *
     * @throws IOException    If an error occurs while reading the CSV file.
     * @throws ParseException If date parsing from the CSV data fails.
     */
	@PostConstruct
	public void loadTraineesMock() {
		try {
			users = usersLoader.getUsers();
			BufferedReader reader=new BufferedReader(new FileReader(csvFilePath)); 
			String row;
			while((row=reader.readLine())!=null) {
				String[] data=row.split(",");
				if(data.length==4) {
					Long id=Long.parseLong(data[0]);
                    Optional<Date> dateOfBirth = parseDate(data[1]);
                    Optional<String> address = Optional.ofNullable(data[2]);
                    if(id!=null) {
                    Trainee trainee = new Trainee(id, dateOfBirth, address,users.get(id));
                    trainees.put(id, trainee);}
				}
			}
	        } catch (IOException | ParseException e) {
	        	logger.error("An error ocurred loading the trainees mock");
	        }
		}
    /**
     * Parse date from a string.
     *
     * @param dateStr The date in string format (MM/dd/yyyy).
     * @return An Optional<Date> representing the parsed date.
     * @throws ParseException If date parsing fails.
     */
	private Optional<Date> parseDate(String dateStr) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return Optional.of(dateFormat.parse(dateStr));
	}
	
    /**
     * Get the map of loaded trainees.
     *
     * @return A map containing loaded trainee data.
     */
	public Map<Long, Trainee> getTrainees() {
		return trainees;
	}
	
    /**
     * Get the map of users associated with trainees.
     *
     * @return A map containing user data for trainees.
     */
	public Map<Long, User> getUsers() {
		return users;
	}

	}
