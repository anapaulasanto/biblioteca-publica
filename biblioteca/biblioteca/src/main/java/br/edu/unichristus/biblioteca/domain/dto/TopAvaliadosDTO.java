package br.edu.unichristus.biblioteca.domain.dto;

public class TopAvaliadosDTO {
    private String idVolume;
    private Double media;

    public TopAvaliadosDTO(String idVolume, Double media) {
        this.idVolume = idVolume;
        this.media = media;
    }

    public String getIdVolume() {
        return idVolume;
    }

    public Double getMedia() {
        return media;
    }
}
