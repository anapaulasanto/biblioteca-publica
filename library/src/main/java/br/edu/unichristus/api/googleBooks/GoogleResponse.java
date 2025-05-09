package br.edu.unichristus.api.googleBooks;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "google_response")

public class GoogleResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "google_response_id")


    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}