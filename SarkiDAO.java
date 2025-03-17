import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblCalmaListesi;
import com.example.musicapp.model.TblSarkı;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//Admin; yeni sanatçı, şarkı ve albüm ekleyebilecek ya da silme güncelleme işlemleri
//yapabilecektir


//sarkının olayı->şarkı ekleyebilir; silebilir ve güncelleyebilir!
public class SarkiDAO {
    //model class->TblSarkı;


    //CREATE
    public static void CreateSong(TblSarkı sarkı, int CalmaListesiID) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(sarkı.getTarih());
        //lokal date-> sqldate e dönüştü;

        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql = "INSERT INTO TblSarkı (SarkıAd,Tarih,Sure,DinlenmeSayi,AlbumID) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sarkı.getSarkıAd());
            ps.setDate(2, sqlDate);
            ps.setInt(3, sarkı.getSure());
            ps.setInt(4, sarkı.getDinlenmeSayi());
            ps.setInt(5, sarkı.getAlbum().getId());
            ps.executeUpdate();
            System.out.println("The Song have been created!");
            ResultSet rs= ps.getGeneratedKeys();//sarkıID -> Çekecez! -> sonra CreateCalmaListesiSarkı() metodunun içinbe set edip çağıracaz!
            while (rs.next()) {
                int sarkiID = rs.getInt(1); //primary key ile aldığım id yi çektim;
                System.out.println("SarkıID: " + sarkiID);
                CalmaListesiSarkiDAO.CreateCalmaListesiSarkı(sarkiID, CalmaListesiID);
                //calmaListesiTbl ye eklemeyi ekle ->
//                CalmaListesiDAO.CreateCalmaListesi(new TblCalmaListesi(),CalmaListesiID);
                System.out.println("--------------------The Song has been added successfully to TblCalmaListesiSarki & TblSarkı  % TblCalmaListesi   -----------------------");
            }
        } catch (Exception e) {
            System.out.println("Kullanıcı eklenirken hata oluştu!");
            e.printStackTrace();

        }
    }
    //UPDATE with modal class
    public static void UpdateSarkı(TblSarkı sarki){
        java.sql.Date sqlDate = java.sql.Date.valueOf(sarki.getTarih());
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="UPDATE TblSarkı SET SarkıAd = ? Tarih  = ? sure = ?, dinlenmeSayi = ? , AlbumID = ? WHERE SarkıID = ? ";
        try {
            //primary key -> de güncellemeye dahil!
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,sarki.getSarkıAd());
            ps.setDate(2,sqlDate);
            ps.setInt(3,sarki.getSure());
            ps.setInt(4,sarki.getDinlenmeSayi());
            ps.setInt(5,sarki.getAlbum().getId()); //albumID -> foreign key -> Tblalbum den album alıyor-> getAlbum();  -> albumId mevcut;!
            ps.setInt(6,sarki.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Hata");
            e.printStackTrace();
        }
    }

    //    READ -> başarılı bir şekilde  constructer ve ToString() metoduyla şarkılar ekrana yazdırılıyor;; -> tabloya UI a dökeceğiz bunu!
    public ArrayList<TblSarkı> getSarki() {
        ArrayList<TblSarkı> Sarki = new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return Sarki;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM TblSarkı";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TblAlbum album = new TblAlbum();
                album.setId(rs.getInt(6));

                LocalDate tarih = (rs.getDate(3) != null) ? rs.getDate(3).toLocalDate() : null;

                Sarki.add(new TblSarkı(
                        rs.getInt(1),
                        rs.getString(2),
                        tarih,
                        rs.getInt(4),
                        rs.getInt(5),
                        album
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while reading the sarki!");
        }
        return Sarki;
    }





//Sarkı-> Album,CalmalistesiSarkı,SarkıSanatçı ya bağlıdır!
    //DELETE -> sarkı da albumün primary key-> foreign key var kontrollü silme !!!
    public static void DeleteSarkı(TblSarkı sarki) {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return;
        }

        try {
            //  bu şarkıya bağlı albümü
            String albumQuery = "SELECT COUNT(*) FROM TblSarkı WHERE AlbumID = ?"; //foreign key olan ıd
            PreparedStatement psAlbum = conn.prepareStatement(albumQuery);
            psAlbum.setInt(1, sarki.getAlbum().getId());
            ResultSet rs = psAlbum.executeQuery();

            if (rs.next() && rs.getInt(1) > 1) {
                //  aynı albüme ait başka şarkılar varsa
                String sql = "DELETE FROM TblSarkı WHERE SarkıID = ?";
                PreparedStatement psSarki = conn.prepareStatement(sql);
                psSarki.setInt(1, sarki.getId());
                psSarki.executeUpdate();
                System.out.println("Song deleted successfully!");
            } else {
                // Eğer albüme bağlı başka şarkılar yoksa, albümün de silinmesi .
                String sql = "DELETE FROM TblSarkı WHERE SarkıID = ?";
                PreparedStatement psDeleteSong = conn.prepareStatement(sql);
                psDeleteSong.setInt(1, sarki.getId());
                psDeleteSong.executeUpdate();

                System.out.println("Song   deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while deleting the song!");
            e.printStackTrace();
        }
    }













    }









//ŞARKI :
//Şarkı eklenince -> TblÇalmaListesine (many-to-many )   de SarkıID ile eklenmeli, silinince TblÇalmaListesi nden de silinmeli
//güncelleme durumunda da aynı şekilde işlemler yapılmalıdır!
//Şarkı eklenip,silinip,güncellenince de TblSarkıSanatçı(many-to-many ) ilişkisi olan tabloda da aynı işlemler gerçekleşmeli!
//TblAlbum de de ilişki var o da etkilenecek;






