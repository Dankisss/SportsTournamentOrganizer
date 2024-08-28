package bg.fmi.javaweb.sportstournamentorganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//TODO:
//1. Provide requests for adding and removing tournaments, teams, matches
//2. Implement user login and sign in
//3. Implement email sender module
//4. Implement JWT provider
//5. Provide connection to React App

@SpringBootApplication
public class SportsTournamentOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsTournamentOrganizerApplication.class, args);

	}

}
