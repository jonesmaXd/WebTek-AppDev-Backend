package no.ntnu.WebTek.AppDevbackend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long productId;
    private String reviewUserName;
    private String reviewText;
    private int rating;
    private LocalDate date;

    public Review(Long productId, String reviewUserName, String reviewText, int rating, LocalDate date) {
        this.productId = productId;
        this.reviewUserName = reviewUserName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.date = date;
    }

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
