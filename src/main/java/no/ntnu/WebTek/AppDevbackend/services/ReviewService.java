package no.ntnu.WebTek.AppDevbackend.services;

import no.ntnu.WebTek.AppDevbackend.validators.StringValidators;
import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired AccessUserService accessUserService;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public String createNewReview(Long productId, String reviewUserName, String reviewText, int rating) {
        String errorMessage = null;
        if (!accessUserService.doesUserExist(reviewUserName)) {
            errorMessage = "User does not exist.";
        }
        else if(StringValidators.isStringInvalid(reviewText)) {
            errorMessage = "Unvalid review description";
        }
        else {
            Review review = new Review(productId, reviewUserName, reviewText, rating, LocalDate.now());
            reviewRepository.save(review);
        }
        return errorMessage;
    }

    public int getNumberOfReviewsByProduct(Long id) {
        return reviewRepository.findAllByProductId(id).size();
    }

    public String deleteReview1(Long reviewId) {
        String errorMessage = null;
        try {
            Review review = reviewRepository.getById(reviewId);
            System.out.println(review);
            reviewRepository.delete(review);
            return errorMessage;
        }
       catch (EntityNotFoundException e) {
            return errorMessage = "Review does not exist";
       }
    }

    public String deleteReview(Long reviewId) {
        String errorMessage = null;

        if(reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            errorMessage = "Review does not exist";
        }
        return errorMessage;
    }
}
