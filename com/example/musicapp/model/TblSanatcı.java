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

    public TblSanatcı(int id, String sanatcıAd, TblUlke ulke) {
        this.id = id;
        this.sanatcıAd=sanatcıAd;
        this.ulkeID=ulke;
    }
    public TblSanatcı(){}

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

    public String toString() {
        return "SanatçıID: " +id +"\n"
                +"SanatçıAd : " +sanatcıAd+"\n"
                +"UlkeID : " +ulkeID.getId()+"\n"
        +"-------------------------------------------";
    }

}