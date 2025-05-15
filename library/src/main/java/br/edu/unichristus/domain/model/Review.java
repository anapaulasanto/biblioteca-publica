package br.edu.unichristus.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Double rating; // a nota é obrigatória

    @Column(length = 450)
    private String comment; // o comentário é opcional

    private LocalDate reviewDate; // data da avaliação

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String reviewerName;

    // Relação entre N reviews : 1 livro
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Relação entre N reviews : 1 usuário
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Review(Long id, Double rating, String comment, LocalDate reviewDate, String reviewerName) {
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.reviewerName = user.getName(); // preenchimento automático
        }
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
