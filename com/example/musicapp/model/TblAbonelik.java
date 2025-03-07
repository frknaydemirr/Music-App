package com.example.musicapp.model;

import javax.persistence.*;

@Entity
public class TblAbonelik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AbonelikID", nullable = false)
    private Integer id;

    @Column(name = "AbonelikTur", nullable = false, length = 20)
    private String abonelikTur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbonelikTur() {
        return abonelikTur;
    }

    public void setAbonelikTur(String abonelikTur) {
        this.abonelikTur = abonelikTur;
    }

}