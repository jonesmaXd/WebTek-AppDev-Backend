package no.ntnu.WebTek.AppDevbackend.controllers;

import no.ntnu.WebTek.AppDevbackend.model.Product;
import no.ntnu.WebTek.AppDevbackend.model.Review;
import no.ntnu.WebTek.AppDevbackend.repository.ReviewRepository;
import no.ntnu.WebTek.AppDevbackend.services.ProductService;
import no.ntnu.WebTek.AppDevbackend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
