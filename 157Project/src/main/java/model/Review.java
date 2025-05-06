package model;

public class Review {
    private int reviewId;
    private int productId;
    private int customerId;
    private int rating;
    private String review; // This stores the review text
    private String reviewerName; // From joined customer name

    // Getters and Setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public String getCustomerName() { return reviewerName; }  // FIXED
    public void setCustomerName(String reviewerName) { this.reviewerName = reviewerName; }

    // Optional alias to match JSP if you use getContent()
    public String getContent() { return review; }
    public void setContent(String content) { this.review = content; }
}
