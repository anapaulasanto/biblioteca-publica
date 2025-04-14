package br.edu.unichristus.biblioteca.client.googlebooks;

import java.util.List;

public class Items {
    private VolumeInfo volumeInfo;
    private String id;
    private AcessInfo acessInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public AcessInfo getAcessInfo() {
        return acessInfo;
    }

    public void setAcessInfo(AcessInfo acessInfo) {
        this.acessInfo = acessInfo;
    }
}
