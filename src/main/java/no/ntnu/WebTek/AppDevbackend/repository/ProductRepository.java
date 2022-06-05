package no.ntnu.WebTek.AppDevbackend.repository;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for products used to handle CRUD operations in the SQL database
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);
}
