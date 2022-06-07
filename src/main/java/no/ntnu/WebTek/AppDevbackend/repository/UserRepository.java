package no.ntnu.WebTek.AppDevbackend.repository;

import no.ntnu.WebTek.AppDevbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for Users used to handle CRUD operations in the SQL database
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUsersByEmail(String email);

    boolean existsUsersByUsername(String username);
}
