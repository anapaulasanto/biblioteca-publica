package br.edu.unichristus.domain.dto.book;

import br.edu.unichristus.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class BookLowDTO {
    private String title;
    private List<String> author;
    private String year;
    private String description;
    private List<String> categories;
    private String pdf;

    public BookLowDTO(String title, List<String> author, String year, String description, List<String> categories, String pdf) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.categories = categories;
        this.pdf = pdf;
    }

    public BookLowDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
