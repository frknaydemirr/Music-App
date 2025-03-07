package com.example.musicapp.model;

import javax.persistence.*;

@Entity
@Table(name = "\"TblKullanıcı\"")
public class TblKullanıcı {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KullaniciID", nullable = false)
    private Integer id;

    @Column(name = "KullaniciAd", nullable = false, length = 50)
    private String kullaniciAd;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Sifre", nullable = false, length = 50)
    private String sifre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UlkeID", nullable = false)
    private com.example.musicapp.model.TblUlke ulkeID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AbonelikID", nullable = false)
    private TblAbonelik abonelikID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKullaniciAd() {
        return kullaniciAd;
    }

    public void setKullaniciAd(String kullaniciAd) {
        this.kullaniciAd = kullaniciAd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public com.example.musicapp.model.TblUlke getUlkeID() {
        return ulkeID;
    }

    public void setUlkeID(com.example.musicapp.model.TblUlke ulkeID) {
        this.ulkeID = ulkeID;
    }

    public TblAbonelik getAbonelikID() {
        return abonelikID;
    }

    public void setAbonelikID(TblAbonelik abonelikID) {
        this.abonelikID = abonelikID;
    }

}