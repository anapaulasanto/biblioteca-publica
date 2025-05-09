package br.edu.unichristus.api.googleBooks;

import jakarta.persistence.*;

@Entity
@Table(name = "items")

public class Items {
    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volume_info_id")
    private VolumeInfo volumeInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_info_id")
    private AccessInfo accessInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo acessInfo) {
        this.accessInfo = acessInfo;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}