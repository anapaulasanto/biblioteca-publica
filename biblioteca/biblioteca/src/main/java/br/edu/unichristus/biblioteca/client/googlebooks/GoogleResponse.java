package br.edu.unichristus.biblioteca.client.googlebooks;

import java.util.List;

public class GoogleResponse {
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
