package com.example.musicapp.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public  class TblAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumID", nullable = false)
    private Integer id;

    @Column(name = "AlbumAd", nullable = false, length = 50)
    private String albumAd;

    @Column(name = "Tarih", nullable = false)
    private LocalDate tarih;

    @Column(name = "Tur", nullable = false, length = 50)
    private String tur;


    //empty constructer
    public TblAlbum() {

    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumAd() {
        return albumAd;
    }

    public void setAlbumAd(String albumAd) {
        this.albumAd = albumAd;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String toString() {
        return "AlbumID:"+getId()+"\n"
                +"AlbumAd:"+getAlbumAd()+"\n"
                +"Tarih:"+getTarih()+"\n"
                +"Tur:"+getTur()+"\n";
    }


    //read data successfully;
    public TblAlbum(Integer id, String albumAd, LocalDate tarih, String tur) {
        this.id = id;
        this.albumAd = albumAd;
        this.tarih = tarih;
        this.tur = tur;
    }


    //model class yapısını kullanrak class yapısıyla kolayca album  oluşturur
    public TblAlbum(String albumAd, LocalDate tarih, String tur) {
        this.albumAd = albumAd;
        this.tarih = tarih;
        this.tur = tur;
        System.out.println("Album Tablosuna yeni bir album oluşturuldu!\n" +
                "Album Ad:"+albumAd+
                "\nAlbum Tarih:"+ tarih+
                "\nTür: " +tur);
        //TblAlbum album = new TblAlbum("Reaksion",LocalDate.parse("2018-01-18"),"Aksiyon"); -> Id eksik -> album eklemiyor bu yüzden;
    }

}

