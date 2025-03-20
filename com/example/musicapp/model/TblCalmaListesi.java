package com.example.musicapp.model;

import javax.persistence.*;

@Entity
public class TblCalmaListesi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CalmaListesiID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "KullaniciID", nullable = false)
    private com.example.musicapp.model.TblKullanıcı kullaniciID;

    @Column(name = "\"MuzıkTur\"", nullable = false, length = 50)
    private String muzıkTur;

    @Column(name = "CalmaListesi", nullable = false, length = 250)
    private String calmaListesi;

    public TblCalmaListesi(int CalmaListID, TblKullanıcı kullanici, String muzıkTur, String calmaListesi) {
        this.calmaListesi = calmaListesi;
        this.kullaniciID = kullanici;
        this.muzıkTur=muzıkTur;
        this.id=CalmaListID;
    }

    public TblCalmaListesi() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.example.musicapp.model.TblKullanıcı getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(com.example.musicapp.model.TblKullanıcı kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getMuzıkTur() {
        return muzıkTur;
    }

    public void setMuzıkTur(String muzıkTur) {
        this.muzıkTur = muzıkTur;
    }

    public String getCalmaListesi() {
        return calmaListesi;
    }

    public void setCalmaListesi(String calmaListesi) {
        this.calmaListesi = calmaListesi;
    }

    public String toString() {
         return "ÇalmaListesi ID: " + id + "\n" +
                "Kullanıcı ID " + kullaniciID.getId()+ "\n" +
                "Müzik Tür: " + muzıkTur + "\n" +
                "Çalma Listesi: " + calmaListesi ;

    }


}