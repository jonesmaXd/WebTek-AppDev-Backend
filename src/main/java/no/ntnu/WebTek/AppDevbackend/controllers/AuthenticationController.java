package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.datatransferobject.SignupDto;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.datatransferobject.AuthenticationRequest;
import no.ntnu.WebTek.AppDevbackend.datatransferobject.AuthenticationResponse;
import no.ntnu.WebTek.AppDevbackend.security.JwtUtil;
import no.ntnu.WebTek.AppDevbackend.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for authentication
 */
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessUserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * HTTP POST request to /authenticate
     *
     * @param authenticationRequest The request JSON object containing username and password
     * @return OK + JWT token; Or UNAUTHORIZED
     */
    @PostMapping("/api/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * This method processes data received from the sign-up form (HTTP POST)
     *
     * @return Name of the template for the result page
     */
    @PostMapping("/api/signup")
    public ResponseEntity<String> signupProcess(@RequestBody SignupDto signupData) {
        String errorMessage = userService.tryCreateNewUser(signupData.getUsername(), signupData.getEmail(), signupData.getPassword());
        ResponseEntity<String> response;
        if (errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

//
//    /**
//     *
//     * @param user
//     * @return
//     */
//    @PostMapping("/api/register")
//    public ResponseEntity<User> register(@RequestBody User user) {
//        if(user.getUsername() == null || user.getPassword() == null
//                || user.getUsername().trim().length() < 1
//                || user.getPassword().trim().length() < 1) {
//            //Invalid values - 400 BAD REQUEST - try again
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } else if (userService.doesUserExist(user.getUsername())) {
//            //The user exists - 409 CONFLICT - the username already exists
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        } else {
//            //Everything went well - 201 CREATED - new user added
//            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
//        }
//    }
}
