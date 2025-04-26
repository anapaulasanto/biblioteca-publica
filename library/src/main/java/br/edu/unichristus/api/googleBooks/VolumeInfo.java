package br.edu.unichristus.api.googleBooks;

import java.util.List;

public class VolumeInfo {

    private String title;
    private List<String> authors;
    private int publishedDate;
    private industryIdentifiers isbn;

    public static class industryIdentifiers {
        private String type;
        private String identifier;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(int publishedDate) {
        this.publishedDate = publishedDate;
    }

    public industryIdentifiers getIsbn() {
        return isbn;
    }

    public void setIsbn(industryIdentifiers isbn) {
        this.isbn = isbn;
    }
}
