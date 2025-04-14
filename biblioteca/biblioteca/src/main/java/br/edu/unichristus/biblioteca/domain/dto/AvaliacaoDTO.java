package br.edu.unichristus.biblioteca.domain.dto;

import br.edu.unichristus.biblioteca.domain.model.Avaliacao;
import java.util.Objects;

public class AvaliacaoDTO {
    private String idVolume;
    private Long id;
    private Long nota;
    private String comentario;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Long id, Long nota, String comentario, String idVolume) {
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




}
