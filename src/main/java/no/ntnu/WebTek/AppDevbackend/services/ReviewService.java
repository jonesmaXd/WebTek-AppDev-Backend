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

}
