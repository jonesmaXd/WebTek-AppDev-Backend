package no.ntnu.WebTek.AppDevbackend.datatransferobject;

public class ReviewDto {
    private Long productId;
    private String reviewUserName;
    private String reviewText;
    private int rating;

    public ReviewDto(Long productId, String reviewUserName, String reviewText, int rating) {
        this.productId = productId;
        this.reviewUserName = reviewUserName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }
}
