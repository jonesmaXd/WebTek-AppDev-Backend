package no.ntnu.WebTek.AppDevbackend.services;

import no.ntnu.WebTek.AppDevbackend.model.Role;
import no.ntnu.WebTek.AppDevbackend.repository.RoleRepository;
import no.ntnu.WebTek.AppDevbackend.repository.UserRepository;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.security.AccessUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides AccessUserDetails needed for authentication
 */
@Service
public class AccessUserService implements UserDetailsService {

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new AccessUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User " + username + "not found");
        }
    }

    public boolean doesUserExist (String username) {
        return userRepository.existsUsersByUsername(username);
    }

    public boolean doesEmailExist (String email) {
        return userRepository.existsUsersByEmail(email);
    }

    /**
     * Try to create a new user
     *
     * @param username Username of the new user
     * @param password Plaintext password of the new user
     * @return null when user created, error message on error
     */
    public String tryCreateNewUser(String username, String email ,String password) {
        String errorMessage;
        if ("".equals(username) || "".equals(email)) {
            errorMessage = "Username or email can't be empty";
        } else if (doesUserExist(username)) {
            errorMessage = "Username already taken";

        } else if(doesEmailExist(email)) {
            errorMessage = "email already taken";
        }
        else {
            errorMessage = checkPasswordRequirements(password);
            if (errorMessage == null) {
                createUser(username, email, password);
            }
        }
        return errorMessage;
    }

    /**
     * Get the user which is authenticated for the current session
     *
     * @return User object or null if no user has logged in
     */
    public User getSessionUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElse(null);
    }

    /**
     * Create a new user in the database
     *
     * @param username Username of the new user
     * @param password Plaintext password of the new user
     */
    private void createUser(String username, String email , String password) {
        Role userRole = roleRepository.findOneByName("ROLE_USER");
        if (userRole != null) {
            User user = new User(username, email, createHash(password));
            user.addRole(userRole);
            userRepository.save(user);
        }
    }

    private String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check if password matches the requirements
     *
     * @param password A password to check
     * @return null if all OK, error message on error
     */
    private String checkPasswordRequirements(String password) {
        String errorMessage = null;
        if (password == null || password.length() == 0) {
            errorMessage = "Password can't be empty";
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            errorMessage = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
        }
        return errorMessage;
    }

    /**
     * Gets all users
     *
     * @return all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}