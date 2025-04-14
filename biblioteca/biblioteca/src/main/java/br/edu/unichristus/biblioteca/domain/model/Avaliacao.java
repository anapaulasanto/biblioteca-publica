package br.edu.unichristus.biblioteca.domain.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="tb_avaliacao_livro")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long nota;

    @Column(length = 200)
    private String comentario;

    @Column(nullable = false)
    private String idVolume;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Long nota, String comentario, String idVolume) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.idVolume = idVolume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdVolume() {
        return idVolume;
    }

    public void setIdVolume(String idVolume) {
        this.idVolume = idVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id) && Objects.equals(nota, avaliacao.nota) && Objects.equals(comentario, avaliacao.comentario) && Objects.equals(idVolume, avaliacao.idVolume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, comentario, idVolume);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", idVolume='" + idVolume + '\'' +
                '}';
    }
}


