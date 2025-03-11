import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblSarkı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblAbonelik;
import com.example.musicapp.model.TblAlbum;


//tbl kullanıcı model sınıfını içeri aktardık!!!

import javax.swing.*;
import java.sql.Connection;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EntryPage entry = new EntryPage();
                entry.setVisible(true);

            }

        });


        //her sayfaya geri gel butonu ekle!->unutma
       Connection conn = DataConnection.connect();
//      KullaniciDAO kullaniciDAO = new KullaniciDAO();
//      TblKullanıcı yeniKullanici = new TblKullanıcı();
//      yeniKullanici.setKullaniciAd("Furkan Aydemir");
//      yeniKullanici.setEmail("aydemirfurkan1372@hotmail.com");
//      yeniKullanici.setSifre("1234567891445");
//      TblAbonelik yeniAbonelik = new TblAbonelik();
//      yeniAbonelik.setId(1);
//      yeniKullanici.setAbonelikID(yeniAbonelik)      ;
//      TblUlke yeniUlke = new TblUlke();
//      yeniUlke.setId(2);
//      yeniKullanici.setUlkeID(yeniUlke);
//      kullaniciDAO.createUser(yeniKullanici);
//        TblKullanıcı kullanici = new TblKullanıcı();
//        kullanici.setId(10); // Güncellenecek kullanıcının ID'sini gir
//        kullanici.setKullaniciAd("YeniAd");
//        kullanici.setEmail("yenimail@example.com");
//        kullanici.setSifre("yeniSifre");

        // Yeni ülke ve abonelik bilgilerini oluştur
//        TblUlke ulke = new TblUlke();
//        ulke.setId(2); // Yeni ülke ID
//        kullanici.setUlkeID(ulke);
//
//        TblAbonelik abonelik = new TblAbonelik();
//        abonelik.setId(2); // Yeni abonelik ID
//        kullanici.setAbonelikID(abonelik);

        // Kullanıcıyı güncelle
//        KullaniciDAO.updateUser(kullanici);
        TblSarkı sarki= new TblSarkı();
        SarkiDAO sarkidao = new SarkiDAO();
        sarki.setSarkıAd("Yeniden");
        sarki.setSure(10);
        sarki.setDinlenmeSayi(1200);
        sarki.setTarih(LocalDate.parse("2020-05-05"));
        //foreign key set etme;
        TblAlbum album = new TblAlbum();
        album.setId(1);
        sarki.setAlbum(album);
        SarkiDAO.CreateSong(sarki);









    }
}
