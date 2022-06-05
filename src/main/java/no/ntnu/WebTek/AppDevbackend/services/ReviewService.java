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

/**
 * Business logic for reviews
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired AccessUserService accessUserService;

    /**
     * Gets all the reviews in the database
     *
     * @return all the reviews in the database
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Creates a new review
     *
     * @param productId the review's productId
     * @param reviewUserName the user name of the review
     * @param reviewText the text/description of the review
     * @param rating the review's rating
     * @return A string, which will be null if the review is successfully created, or
     *  have a message containing the error if it occurs.
     */
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

    /**
     * Gets the number of reviews in the database related to a product
     * @param productId the product's id
     * @return the number of reviews found
     */
    public int getNumberOfReviewsByProduct(Long productId) {
        return reviewRepository.findAllByProductId(productId).size();
    }

    /**
     * Deletes a review from the database
     * @param reviewId the if of the review to be deleted
     * @return A string, which will be null if the review is successfully created, or
     * have a message containing the error if it occurs.
     */
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
