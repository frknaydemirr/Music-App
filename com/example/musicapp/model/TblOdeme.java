package com.example.musicapp.model;

import javax.persistence.*;

@Entity
public class TblOdeme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OdemeID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "KullaniciID", nullable = false)
    private TblKullanıcı kullaniciID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblKullanıcı getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(TblKullanıcı kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

}