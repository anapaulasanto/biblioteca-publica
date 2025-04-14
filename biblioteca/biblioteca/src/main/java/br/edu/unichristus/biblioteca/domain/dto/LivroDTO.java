package br.edu.unichristus.biblioteca.domain.dto;

import java.util.Objects;

public class LivroDTO {
    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private String descricao;
    private String data;

    public LivroDTO() {
    }

    public LivroDTO(String id, String titulo, String autor, String editora, String descricao, String data) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.descricao = descricao;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroDTO livroDTO = (LivroDTO) o;
        return Objects.equals(id, livroDTO.id) && Objects.equals(titulo, livroDTO.titulo) && Objects.equals(autor, livroDTO.autor) && Objects.equals(editora, livroDTO.editora) && Objects.equals(descricao, livroDTO.descricao) && Objects.equals(data, livroDTO.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, editora, descricao, data);
    }

    @Override
    public String toString() {
        return "LivroDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
