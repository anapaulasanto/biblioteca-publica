package br.edu.unichristus.biblioteca.domain.dto;

public class AvaliacaoLowDTO {
    private Long nota;
    private String comentario;

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

}
