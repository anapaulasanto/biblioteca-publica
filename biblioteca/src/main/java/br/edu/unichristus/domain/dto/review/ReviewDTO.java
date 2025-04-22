package br.edu.unichristus.domain.dto.review;

import br.edu.unichristus.domain.model.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class ReviewDTO {
    private Long id;

    @JsonProperty("nota")
    private double rating;

    @JsonProperty("comentário")
    private String comment;

    @JsonProperty("data da avaliação")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate reviewDate;
    private String reviewerName;

    public ReviewDTO(Long id, double rating, String comment, LocalDate reviewDate, String reviewerName) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reviewerName = reviewerName;
    }

    public ReviewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Double.compare(review.getRating(), rating) == 0 &&
                Objects.equals(id, review.getId()) &&
                Objects.equals(comment, review.getComment()) &&
                Objects.equals(reviewDate, review.getReviewDate()) &&
                Objects.equals(reviewerName, review.getReviewerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, comment, reviewDate, reviewerName);
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", reviewDate=" + reviewDate +
                ", reviewerName='" + reviewerName + '\'' +
                '}';
    }
}
