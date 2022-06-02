package no.ntnu.WebTek.AppDevbackend.services;

import no.ntnu.WebTek.AppDevbackend.validators.StringValidators;
import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public String createNewReview(Long productId, String reviewUserName, String reviewText, int rating) {
        String errorMessage = null;
        if(StringValidators.isStringInvalid(reviewText)) {
            errorMessage = "invalid review description";
        } else {
            Review review = new Review(productId, reviewUserName, reviewText, rating, LocalDate.now());
            reviewRepository.save(review);
        }
        return errorMessage;
    }

}
