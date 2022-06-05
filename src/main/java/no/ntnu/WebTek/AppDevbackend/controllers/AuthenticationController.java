package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.datatransferobject.SignupDto;
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
 * Controller responsible for authentication and signing up
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
        String errorMessage = userService.tryCreateNewUser(signupData.getUsername(),
                                                            signupData.getEmail(),
                                                            signupData.getPassword());
        ResponseEntity<String> response;
        if (errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
