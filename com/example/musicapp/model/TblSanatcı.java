package com.example.musicapp.model;

import javax.persistence.*;

@Entity
@Table(name = "\"TblSanatcı\"")
public class TblSanatcı {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"SanatcıID\"", nullable = false)
    private Integer id;

    @Column(name = "\"SanatcıAd\"", nullable = false, length = 50)
    private String sanatcıAd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UlkeID")
    private com.example.musicapp.model.TblUlke ulkeID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSanatcıAd() {
        return sanatcıAd;
    }

    public void setSanatcıAd(String sanatcıAd) {
        this.sanatcıAd = sanatcıAd;
    }

    public com.example.musicapp.model.TblUlke getUlkeID() {
        return ulkeID;
    }

    public void setUlkeID(com.example.musicapp.model.TblUlke ulkeID) {
        this.ulkeID = ulkeID;
    }

}