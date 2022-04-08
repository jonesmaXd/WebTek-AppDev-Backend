package no.ntnu.WebTek.AppDevbackend;

import no.ntnu.WebTek.AppDevbackend.model.Role;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.service.UserService;
import no.ntnu.WebTek.AppDevbackend.service.UserServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final Logger logger = LoggerFactory.getLogger("DummyInit");

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {

				userService.saveRole(new Role(1L, "ROLE_USER"));
				userService.saveRole(new Role(2L, "ROLE_ADMIN"));

				userService.saveUser(new User(3L, "Eirik Dahle", "eirikd", "1234", new ArrayList<>()));
				userService.saveUser(new User(4L, "Jones Maaroufi", "jonesma", "5678", new ArrayList<>()));

				userService.addRoleToUser("eirikd", "ROLE_ADMIN");
				userService.addRoleToUser("jonesma", "ROLE_USER");

		};
	}
}
