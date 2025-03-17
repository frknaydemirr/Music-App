import com.example.musicapp.model.*;


//tbl kullanıcı model sınıfını içeri aktardık!!!

import javax.swing.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

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
//

//        //Şarkı create etme
//        TblAlbum album = new TblAlbum();
//        album.setId(1);
//        sarki.setAlbum(album);
//        SarkiDAO.CreateSong(sarki);


        //Album create etme
//        TblAlbum album = new TblAlbum();
//        AlbumDAO albumdao = new AlbumDAO();
//        album.setAlbumAd("kaç kadeh kırıldı");
//        album.setTur("Acıklı");
//        album.setTarih(LocalDate.parse("2025-11-03"));
//        albumdao.CreateAlbum(album);


//Sarkı create etme:
//        TblSarkı sarki= new TblSarkı();
//        SarkiDAO sarkidao = new SarkiDAO();
//        sarki.setSarkıAd("Ay");
//        sarki.setSure(8);
//        sarki.setDinlenmeSayi(200);
//       sarki.setTarih(LocalDate.parse("2020-05-05"));
//       TblAlbum album = new TblAlbum();
//       album.setId(1);
//       sarki.setAlbum(album);
//       sarkidao.CreateSong(sarki);


        //Album delete etme:
//        TblAlbum album = new TblAlbum();
//        album.setId(6);
//        AlbumDAO albumDAO = new AlbumDAO();
//        albumDAO.DeleteAlbum(album);


        //Album update etme:
//        TblAlbum album = new TblAlbum();
//        album.setId(5);
//        album.setTur("Rap");
//        album.setAlbumAd("Çoşma Kopma");
//        album.setTarih(LocalDate.parse("2020-05-05"));
//        AlbumDAO dao = new AlbumDAO();
//        dao.UpdateAlbum(album);

        //album Create etme -> iki yolla(boş constructer & elle metotla ) -> boş constructer sıkıntılı veri tabanına düşmüyor kontrol ET!!!
//        AlbumDAO albumDAO = new AlbumDAO();
//        TblAlbum album=new TblAlbum(); //boş constructerla model class -> create data ile de oluşturulabilir;
//        album.setAlbumAd("Romatizmi zirvede yaşa");
//        album.setTur("Romatik");
//        album.setTarih(LocalDate.parse("2018-01-18"));
//        albumDAO.CreateAlbum(album);
//        AlbumDAO dao = new AlbumDAO();
//        ArrayList<TblAlbum> albumList = dao.getSarki();
//        System.out.println(albumList.size());



        //Sanatcı silme & create etme -> model classla
//        TblSanatcı sanatci=new TblSanatcı();
//        SanatciDAO sanatciDAO=new SanatciDAO();
//        TblUlke ulke=new TblUlke();
//        ulke.setId(1);
//        sanatci.setSanatcıAd("Ajda Pekkan");
//        sanatci.setUlkeID(ulke);
//        sanatciDAO.CreateSanatci(sanatci);
//        TblSanatcı sanatcı =new TblSanatcı();
//        sanatcı.setId(6);
//        SanatciDAO sanatciDAO = new SanatciDAO();
//        sanatciDAO.DeleteSanatci(sanatcı);

        //Sanatcı güncelleme-> model classla
//        TblSanatcı sanatci=new TblSanatcı();
//        SanatciDAO dao=new SanatciDAO();
//        TblUlke ulke=new TblUlke();
//        ulke.setId(4);
//        sanatci.setId(8); //id-> 8 olan sanatcıyı güncelle;
//        sanatci.setSanatcıAd("Hollwieeee");
//        sanatci.setUlkeID(ulke);
//        dao.UpdateSanatci(sanatci);



//sarkı ekleme ve calmalistesisarkı tablosuna ekleme aynı anda; -> şarkı üzerinde işlem yapılınca otomataik burada da yapılır;

// TblSarkı nesnesi
//        TblSarkı sarki = new TblSarkı();
//        sarki.setSarkıAd("Yeni Şarkım");
//        sarki.setSure(100);
//        sarki.setDinlenmeSayi(12000);
//        sarki.setTarih(LocalDate.now());
//        TblAlbum album = new TblAlbum();
//        album.setId(5);
//        sarki.setAlbum(album);
//
//
//        TblKullanıcı kullanici = new TblKullanıcı();
//        kullanici.setId(20); //
//        TblCalmaListesi calmaListesi = new TblCalmaListesi();
//        calmaListesi.setKullaniciID(kullanici);
//        calmaListesi.setMuzıkTur("Pop");
//        calmaListesi.setCalmaListesi("Favori şarkılar");
//
//
//        CalmaListesiSarkiDAO dao = new CalmaListesiSarkiDAO();
//        SarkiDAO sarkiDAO = new SarkiDAO();
//        sarkiDAO.CreateSong(sarki, 5);
//        dao.CreateCalmaListesiSarkı(2, 3);


//Calma listesine sarkı eklemeyi deneyelim; -> calmalistesine ekleme başarılı:
//TblCalmaListesi liste=new TblCalmaListesi();
//TblKullanıcı user=new TblKullanıcı();
//user.setId(13);
//liste.setKullaniciID(user);
//liste.setMuzıkTur("Pop");
//liste.setCalmaListesi("Her nefeste");
//CalmaListesiDAO dao=new CalmaListesiDAO();
//dao.CreateCalmaListesi(liste);
//

//Constructerla başarılı bir şekilde şarkılar okunuyor;;
//        SarkiDAO dao = new SarkiDAO();
//       ArrayList<TblSarkı> sarkilistesi=dao.getSarki();
//        sarkilistesi.forEach(sarki -> {
//            System.out.println(sarki.toString());
//        });



//TblAlbum da constructer -> başarılı bir şlekilde verileri okuduk!
//TblAlbum album = new TblAlbum();
//AlbumDAO dao = new AlbumDAO();
//ArrayList<TblAlbum>albums=dao.getSarki();
//        System.out.println(albums.size());
//        albums.forEach(album1 ->{
//            System.out.println(album1);
//        });



//TblCalmaListesiSarkı da constructer -> başarılı bir şlekilde verileri okuduk!
//TblCalmaListesiSarkı calmaListesiSarkı=new TblCalmaListesiSarkı();
//CalmaListesiSarkiDAO dao=new CalmaListesiSarkiDAO();
//ArrayList<TblCalmaListesiSarkı>calmaListesiSarki=dao.getCalmaListesiSarki();
//        calmaListesiSarki.forEach(liste->{
//            System.out.println(liste);
//});

//constructer a eklemeler yaparak başarılı bi -> şekilde güncelleme aldık!
//TblCalmaListesiSarkı list=new TblCalmaListesiSarkı();
//CalmaListesiSarkiDAO dao=new CalmaListesiSarkiDAO();
//list.setOldcalmaListesiId(5);
//list.setoldSarkıId(27);
//list.setSarkıId(9);
//list.setÇalmaListesiId(9);
//dao.UpdateCalmaListesiSarki(list);
//

//sanatçılar -> constructer ve ToString() metodu -> ile başarıulıu bi şekilde okunup yazdıırlıyor artık!
//SanatciDAO dao = new SanatciDAO();
//TblSanatcı sanatcı=new TblSanatcı();
//ArrayList<TblSanatcı> sanatcilar=dao.getSanatcilar();
//sanatcilar.forEach(sanatcı1 ->
//        System.out.println(sanatcı1));
//
//
   }
}
