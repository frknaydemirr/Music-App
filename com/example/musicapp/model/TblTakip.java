package com.example.musicapp.model;

import javax.persistence.*;

@Entity
public class TblTakip {
    @Id
    @Column(name = "TakipID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TakipEdenID", nullable = false)
    private TblKullanıcı takipEdenID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TakipEdilenID", nullable = false)
    private TblKullanıcı takipEdilenID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblKullanıcı getTakipEdenID() {
        return takipEdenID;
    }

    public void setTakipEdenID(TblKullanıcı takipEdenID) {
        this.takipEdenID = takipEdenID;
    }

    public TblKullanıcı getTakipEdilenID() {
        return takipEdilenID;
    }

    public void setTakipEdilenID(TblKullanıcı takipEdilenID) {
        this.takipEdilenID = takipEdilenID;
    }

}