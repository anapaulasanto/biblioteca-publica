package br.edu.unichristus.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rentalDate;

    private String returnDate;
    private String status; // "active", "returned", "overdue", "renewed"

    @Column(length = 150)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Rental() {
    }

    public Rental(Long id, String rentalDate, String returnDate, String status, String notes) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental rental)) return false;
        return Objects.equals(id, rental.id) &&
                Objects.equals(rentalDate, rental.rentalDate) &&
                Objects.equals(returnDate, rental.returnDate) &&
                Objects.equals(status, rental.status) &&
                Objects.equals(notes, rental.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDate, returnDate, status, notes);
    }

    @Override
    public String toString() {
        return "BookRental{" +
                "id=" + id +
                ", rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}
