package br.edu.unichristus.domain.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String categoryName;
    private String description;

    @Column(unique = true)
    private int categoryCode;

    @Min(value = 1, message = "A popularidade deve ser no mínimo 1")
    @Max(value = 10, message = "A popularidade deve ser no máximo 10")
    private int categoryPopularity;


    public Category(Long id, String categoryName, String description, int categoryCode, int categoryPopularity) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.categoryCode = categoryCode;
        this.categoryPopularity = categoryPopularity;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getCategoryPopularity() {
        return categoryPopularity;
    }

    public void setCategoryPopularity(int categoryPopularity) {
        this.categoryPopularity = categoryPopularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id) &&
                Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(description, category.description) &&
                categoryCode == category.categoryCode &&
                categoryPopularity == category.categoryPopularity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, description, categoryCode, categoryPopularity);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", bookName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", categoryCode=" + categoryCode +
                ", categoryPopularity=" + categoryPopularity +
                '}';
    }

}
