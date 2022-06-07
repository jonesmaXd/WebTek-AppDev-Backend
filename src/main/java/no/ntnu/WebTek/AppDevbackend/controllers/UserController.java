package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller serving endpoints for users
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private AccessUserService userService;

    @GetMapping("/api/user/getAll")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
