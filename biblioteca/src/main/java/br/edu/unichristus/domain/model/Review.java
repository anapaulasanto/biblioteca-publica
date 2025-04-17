package br.edu.unichristus.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double rating; // a nota é obrigatória

    @Column(length = 450)
    private String comment; // o comentário é opcional

    private LocalDate reviewDate; // data da avaliação

    private String reviewerName; // nome do avaliador (dado sensível)

    public Review(Long id, double rating, String comment, LocalDate reviewDate, String reviewerName) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reviewerName = reviewerName;
    }

    public Review() {
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
        return Double.compare(review.rating, rating) == 0 &&
                Objects.equals(id, review.id) &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(reviewDate, review.reviewDate) &&
                Objects.equals(reviewerName, review.reviewerName);
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
