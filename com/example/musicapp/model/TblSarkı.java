package com.example.musicapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "\"TblSarkı\"")
public class TblSarkı {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"SarkıID\"", nullable = false)
    private Integer id;

    @Column(name = "\"SarkıAd\"", nullable = false, length = 50)
    private String sarkıAd;

    @Column(name = "Tarih", nullable = false)
    private LocalDate tarih;

    @Column(name = "Sure", nullable = false)
    private Integer sure;

    @Column(name = "DinlenmeSayi", nullable = false)
    private Integer dinlenmeSayi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlbumId")
    private TblAlbum album;
    //if(tblsarkı.album.sarkiId == SARKItablosu.sarkıid){ sil album ve şarkıları}

    //read -> için kullandığım const
    public TblSarkı(int id, String sarkıAd, LocalDate tarih, int sure, int dinlenmeSayi, TblAlbum album) {
        this.id = id;
        this.sarkıAd=sarkıAd;
        this.tarih=tarih;
        this.sure=sure;
        this.dinlenmeSayi=dinlenmeSayi;
        this.album= album;
    }

    //diğer işlem için kullanığım const
public TblSarkı(){}



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSarkıAd() {
        return sarkıAd;
    }

    public void setSarkıAd(String sarkıAd) {
        this.sarkıAd = sarkıAd;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public Integer getSure() {
        return sure;
    }

    public void setSure(Integer sure) {
        this.sure = sure;
    }

    public Integer getDinlenmeSayi() {
        return dinlenmeSayi;
    }

    public void setDinlenmeSayi(Integer dinlenmeSayi) {
        this.dinlenmeSayi = dinlenmeSayi;
    }

    public TblAlbum getAlbum() {
        return album;
    }

    public void setAlbum(TblAlbum album) {
        this.album = album;
    }

    public String toString() {
        return "Şarkı ID: " + id + "\n" +
                "Şarkı Adı: " + sarkıAd + "\n" +
                "Tarih: " + tarih + "\n" +
                "Süre: " + sure + " dakika\n" +
                "Dinlenme Sayısı: " + dinlenmeSayi + "\n" +
                "Albüm: " + (album != null ? album.getId() : "Bilinmiyor") + "\n";
    }


}