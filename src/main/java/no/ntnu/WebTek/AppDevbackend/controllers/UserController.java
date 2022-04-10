package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * A simple REST API controller providing different endpoints
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String home() {
        return "This is a public home page";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userPage() {
        return "This is accessible to all authorized users";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "This is accessible only for ADMIN users";
    }

//    @PostMapping("/login")
//    public ResponseEntity<User> login() {
//        There is not actually need for a method like this because the /authenticate PostMapping
//        authenticates and logs in a user
//    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(user.getUsername() == null || user.getPassword() == null
            || user.getUsername().trim().length() < 1
            || user.getPassword().trim().length() < 1) {
            //Invalid values - 400 BAD REQUEST - try again
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (userRepository.existsUsersByUsername(user.getUsername())) {
            //The user exists - 409 CONFLICT - the username already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            //Everything went well - 201 CREATED - new user added
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        }
    }


}
