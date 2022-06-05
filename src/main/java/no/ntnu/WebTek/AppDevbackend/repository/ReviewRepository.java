package no.ntnu.WebTek.AppDevbackend.repository;

import no.ntnu.WebTek.AppDevbackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Review used to handle CRUD operations in the SQL database
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProductId(Long id);


}
