package com.example.musicapp.model;

import javax.persistence.*;

@Entity
public class TblUlke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UlkeID", nullable = false)
    private Integer id;

    @Column(name = "UlkeAd", nullable = false, length = 50)
    private String ulkeAd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUlkeAd() {
        return ulkeAd;
    }

    public void setUlkeAd(String ulkeAd) {
        this.ulkeAd = ulkeAd;
    }

    public String toString(){
        return "ÜlkeID: " +id +"\n"
                +"ÜlkeAd: " +ulkeAd ;

    }

}