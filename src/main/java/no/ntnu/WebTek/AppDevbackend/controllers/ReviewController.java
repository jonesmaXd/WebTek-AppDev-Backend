package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.datatransferobject.ReviewDto;
import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.repository.ReviewRepository;
import no.ntnu.WebTek.AppDevbackend.services.ProductService;
import no.ntnu.WebTek.AppDevbackend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     *
     */
    @GetMapping("/api/reviews")
    public List<Review> getAll() {
        return reviewService.getAllReviews();
    }

    /**
     *
     */
    @PostMapping("/api/addReview")
    public ResponseEntity<String> addReview(@RequestBody ReviewDto reviewDto) {
        String errorMessage = reviewService.createNewReview(reviewDto.getProductId(),
                                                            reviewDto.getReviewUserName(),
                                                            reviewDto.getReviewText(),
                                                            reviewDto.getRating());
        ResponseEntity<String> response;
        if (errorMessage == null) {
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
