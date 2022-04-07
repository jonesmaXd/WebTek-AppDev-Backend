package no.ntnu.WebTek.AppDevbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.ntnu.WebTek.AppDevbackend.model.Role;
import no.ntnu.WebTek.AppDevbackend.model.User;
import no.ntnu.WebTek.AppDevbackend.repository.RoleRepository;
import no.ntnu.WebTek.AppDevbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Override
    public User saveUser(User user) {

        log.info("Saving new user {} to database", user.getName());
        return userRepo.save(user);

    }

    @Override
    public Role saveRole(Role role) {

        log.info("Saving new role {} to database", role.getName());
        return roleRepo.save(role);

    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

        log.info("Assigning role {} to user {}", roleName, userName);
        User user = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String userName) {

        log.info("Getting user {}", userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {

        log.info("Getting all users {}");
        return userRepo.findAll();
    }
}
