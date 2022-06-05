package no.ntnu.WebTek.AppDevbackend.repository;

import no.ntnu.WebTek.AppDevbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for Roles used to handle CRUD operations in the SQL database
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
