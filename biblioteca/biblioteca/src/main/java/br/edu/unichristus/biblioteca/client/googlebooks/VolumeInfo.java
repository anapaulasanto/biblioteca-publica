package br.edu.unichristus.biblioteca.client.googlebooks;

import java.util.List;

public class VolumeInfo {

    private Image imageLinks;
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String publisher;
    private String description;

    public static class Image {
        private String smallThumbnail;
        private String thumbnail;

        public String getSmallThumbnail() {
            return smallThumbnail;
        }

        public void setSmallThumbnail(String smallThumbnail) {
            this.smallThumbnail = smallThumbnail;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    public Image getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(Image imageLinks) {
        this.imageLinks = imageLinks;
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
