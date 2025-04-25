package br.edu.unichristus.domain.dto.rental;

import br.edu.unichristus.domain.model.Rental;
import jakarta.persistence.Column;

import java.util.Objects;

public class RentalDTO {
    private Long id;
    private String rentalDate;
    private String returnDate;
    private String status;
    private String notes;
    private Long userId;
    private String userName; // Apenas leitura, preenchido no retorno se quiser exibir


    public RentalDTO(Long id, String rentalDate, String returnDate, String status, String notes) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
        this.notes = notes;
    }

    public RentalDTO() {
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getUserId() {
        return userId;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental rental)) return false;
        return Objects.equals(id, rental.getId()) &&
                Objects.equals(rentalDate, rental.getRentalDate()) &&
                Objects.equals(returnDate, rental.getReturnDate()) &&
                Objects.equals(status, rental.getStatus()) &&
                Objects.equals(notes, rental.getNotes());
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
