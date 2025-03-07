package com.example.musicapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TblAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumID", nullable = false)
    private Integer id;

    @Column(name = "AlbumAd", nullable = false, length = 50)
    private String albumAd;

    @Column(name = "Tarih", nullable = false)
    private LocalDate tarih;

    @Column(name = "Tur", nullable = false, length = 50)
    private String tur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumAd() {
        return albumAd;
    }

    public void setAlbumAd(String albumAd) {
        this.albumAd = albumAd;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

}