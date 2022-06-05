package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller serving endpoints for reviews
 */
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Gets all reviews in the database
     *
     * @return List of all reviews
     */
    @GetMapping("/api/review/getAll")
    public List<Review> getAll() {
        return reviewService.getAllReviews();
    }

    /**
     * Adds a review to the database
     *
     * @param review the review object to be added
     * @return Http 200 ok if product is successfully added, 400 bad request if unsuccessfully,
     * indicating the review had improper values
     */
    @PostMapping("/api/review/addReview")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        String errorMessage = reviewService.createNewReview(review.getProductId(),
                                                            review.getReviewUserName(),
                                                            review.getReviewText(),
                                                            review.getRating());
        ResponseEntity<String> response;
        if (errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * remove a review from the database by its reviewId
     *
     * @param reviewId the id of the review to be deleted
     * @return Http 200 ok if product is successfully added, 400 bad request if unsuccessfully,
     * indicating the review could not be deleted
     */
    @DeleteMapping("/api/review/delete/{reviewId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        String errorMessage = reviewService.deleteReview(reviewId);
        ResponseEntity<String> response;
        if(errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Gets all the reviews related to a product in the database
     *
     * @param productId the product id of the review
     * @return The number of reviews found
     */
    @GetMapping("/api/review/getAllRelated/{productId}")
    public int getAllById(@PathVariable Long productId) {
        return reviewService.getNumberOfReviewsByProduct(productId);
    }

}
