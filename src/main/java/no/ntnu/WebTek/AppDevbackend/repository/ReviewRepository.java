package no.ntnu.WebTek.AppDevbackend.repository;

import no.ntnu.WebTek.AppDevbackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewUserName(String userName);

    List<Review> findAllByProductId(Long id);
}
