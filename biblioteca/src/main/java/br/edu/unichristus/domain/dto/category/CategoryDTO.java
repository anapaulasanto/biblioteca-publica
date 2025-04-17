package br.edu.unichristus.domain.dto.category;

import br.edu.unichristus.domain.model.Category;

import java.util.Objects;

public class CategoryDTO {
    private Long id;
    private String categoryName;
    private String description;
    private int categoryCode;
    private int categoryPopularity;

    public CategoryDTO(Long id, String categoryName, String description, int categoryCode, int categoryPopularity) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.categoryCode = categoryCode;
        this.categoryPopularity = categoryPopularity;
    }

    public CategoryDTO() {
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
        return Objects.equals(id, category.getId()) &&
                Objects.equals(categoryName, category.getCategoryName()) &&
                Objects.equals(description, category.getDescription()) &&
                categoryCode == category.getCategoryCode() &&
                categoryPopularity == category.getCategoryPopularity();
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
