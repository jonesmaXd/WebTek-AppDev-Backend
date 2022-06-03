package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.repository.UserRepository;
import no.ntnu.WebTek.AppDevbackend.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A simple REST API controller providing different endpoints
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private AccessUserService userService;

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

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }


}
