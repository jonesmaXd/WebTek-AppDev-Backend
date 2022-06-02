package no.ntnu.WebTek.AppDevbackend;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.model.Role;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.repository.ProductRepository;
import no.ntnu.WebTek.AppDevbackend.repository.ReviewRepository;
import no.ntnu.WebTek.AppDevbackend.repository.RoleRepository;
import no.ntnu.WebTek.AppDevbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * A class which inserts some dummy data into the database, when Spring Boot app has started
 */
@Component
public class TestDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    /**
     * This method is called when the application is ready (loaded)
     *
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Optional<User> existingUser = userRepository.findByUsername("Jones");
        Optional<Product> existingProduct = productRepository.findByTitle("Two day course");

        if (existingUser.isEmpty() && existingProduct.isEmpty()) {
            logger.info("Importing test data...");
            //Hashed password: coke
            User Dahle = new User("Dahle", "swag@gmail.com" ,"$2a$12$TXOIt376KS0kuMJz6T4xFeGctfC2YOdXT5ZZXk8OuVrEZwlhplaXu");
            //Hashed password: Morgan
            User Jones = new User("Jones", "KIT@gmail.com" ,"$2a$12$OQ13NatVxbEmX1czwhbUKeRv7ACfShEOP.lvbFcoPB0OYckDlfYyO");
            //Hashed password: knob
            User Ferskken = new User("Ferskken","badrice@gmail.com" ,"$2a$12$dAXOtaEKQoWoNX7kmeciHOyU9lYrP8LMHl723bXh7VqV54dV/v55K");
            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");
            Dahle.addRole(user);
            Dahle.addRole(admin);
            Jones.addRole(user);
            Jones.addRole(admin);
            Ferskken.addRole(user);

            roleRepository.save(user);
            roleRepository.save(admin);

            userRepository.saveAll(List.of(Dahle, Ferskken, Jones));

            Product twoDayCourse = new Product( "Two day course", "For newcomers and inexperienced", "4 hours for two days", 8000);
            Product oneDayCourse = new Product( "One day course", "For experienced who seek a refresh in knowledge", "6 hours for one day", 5000);
            Product consultation = new Product("Consultation", "For specific inquiries", "Up to 1 hour", 1600);

            productRepository.saveAll(List.of(twoDayCourse, oneDayCourse, consultation));

            Review review1Product1 = new Review(twoDayCourse.getId(), Jones.getUsername(), "Amazing course by amazing people", 5, LocalDate.now());
            Review review2Product2 = new Review(oneDayCourse.getId(), Dahle.getUsername(), "Really enjoyed my stay!", 5, LocalDate.now());
            Review review3Product1 = new Review(twoDayCourse.getId(), Jones.getUsername(), "Jeg kunne gjerne betalt 8000kr igjen !!", 5, LocalDate.now());
            reviewRepository.saveAll(List.of(review1Product1, review2Product2, review3Product1));

            logger.info("DONE importing test data");
        } else {
            logger.info("Users already in the database, not importing anything");
        }
    }
}