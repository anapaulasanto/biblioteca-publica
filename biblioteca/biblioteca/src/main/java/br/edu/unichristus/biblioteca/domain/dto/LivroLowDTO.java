package br.edu.unichristus.biblioteca.domain.dto;

import java.util.List;

public class LivroLowDTO {
    private String capa;
    private String titulo;
    private List<String> autores;
    private String data;
    private String linkPdf;

    public LivroLowDTO(String capa, String titulo, List<String> autores, String data, String linkPdf) {
        this.capa = capa;
        this.titulo = titulo;
        this.autores = autores;
        this.data = data;
        this.linkPdf = linkPdf;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }
}
