package no.ntnu.WebTek.AppDevbackend;

import no.ntnu.WebTek.AppDevbackend.model.Role;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MEMBER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new User(null, "Eirik Dahle", "eirikd", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jones Maaroufi", "jonesma", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Ola Jens", "olaj", "1234", new ArrayList<>()));

			userService.addRoleToUser("eirikd", "ROLE_ADMIN");
			userService.addRoleToUser("jonesma", "ROLE_MEMBER");
			userService.addRoleToUser("olaj", "ROLE_USER");

		};
	}

}
