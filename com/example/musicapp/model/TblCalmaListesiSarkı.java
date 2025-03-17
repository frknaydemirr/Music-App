package com.example.musicapp.model;

import javax.persistence.*;

@Entity
@Table(name = "\"TblCalmaListesiSarkı\"")
public class TblCalmaListesiSarkı {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "çalmaListesiId", nullable = false)
    private Integer çalmaListesiId;

    @Column(name = "sarkıId", nullable = false)
    private Integer sarkıId;

    private  Integer oldsarkiId;

    private  Integer oldcalmaListesiId;

    // Boş Constructor (Hibernate için gerekli)
    public TblCalmaListesiSarkı() {}

    // Dolu Constructor
    public TblCalmaListesiSarkı(int çalmaListesiId, int sarkıId) {
        this.çalmaListesiId = çalmaListesiId;
        this.sarkıId = sarkıId;
    }

    // Getter - Setter Metotları
    public Integer getÇalmaListesiId() {
        return çalmaListesiId;
    }

    public void setÇalmaListesiId(Integer çalmaListesiId) {
        this.çalmaListesiId = çalmaListesiId;
    }

    public Integer getSarkıId() {
        return sarkıId;
    }

    public void setSarkıId(Integer sarkıId) {
        this.sarkıId = sarkıId;
    }

    public void setoldSarkıId(Integer sarkıId) {
        this.oldsarkiId = sarkıId;
    }

    public Integer getoldSarkıId() {
        return oldsarkiId;
    }

    public Integer getoldcalmaListesiId() {
        return oldcalmaListesiId;
    }

    public void setOldcalmaListesiId(Integer oldcalmaListesiId) {
        this.oldcalmaListesiId = oldcalmaListesiId;
    }

    public TblCalmaListesiSarkı(int calmaListesiId , int sarkıId, int oldcalmaListesiId, int oldsarkiId) {
        this.çalmaListesiId = çalmaListesiId;
        this.sarkıId = sarkıId;
        this.oldcalmaListesiId=oldcalmaListesiId;
        this.oldsarkiId=oldsarkiId;
    }

    @Override
    public String toString() {
        return "Çalma Listesi ID: " + çalmaListesiId + "\n" +
                "Şarkı ID: " + sarkıId;
    }
}
