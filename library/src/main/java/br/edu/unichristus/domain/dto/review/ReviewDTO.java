package br.edu.unichristus.domain.dto.review;

import br.edu.unichristus.domain.model.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class ReviewDTO {
    @Schema(hidden = true)
    private Long id;

    @JsonProperty("nota")
    @NotNull(message = "Nota é obrigatória")
    private Double rating;

    @JsonProperty("comentario")
    private String comment;

    @JsonProperty("data da avaliação")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate reviewDate;
    private String reviewerName;

    private Long bookId;
    private Long userId;

    public ReviewDTO(Long id, Double rating, String comment, LocalDate reviewDate, String reviewerName, Long bookId, Long userId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reviewerName = reviewerName;
        this.bookId = bookId;
        this.userId = userId;
    }

    public ReviewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
