import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblSarkı;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Admin; yeni sanatçı, şarkı ve albüm ekleyebilecek ya da silme güncelleme işlemleri
//yapabilecektir


//sarkının olayı->şarkı ekleyebilir; silebilir ve güncelleyebilir!
public class SarkiDAO {
    //model class->TblSarkı;


    //CREATE
    public static void CreateSong(TblSarkı sarkı) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(sarkı.getTarih());
        //lokal date-> sqldate e dönüştü;

        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql = "INSERT INTO TblSarkı (SarkıAd,Tarih,Sure,DinlenmeSayi,AlbumID) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sarkı.getSarkıAd());
            ps.setDate(2, sqlDate);
            ps.setInt(3, sarkı.getSure());
            ps.setInt(4, sarkı.getDinlenmeSayi());
            ps.setInt(5, sarkı.getAlbum().getId());
            ps.executeUpdate();
            System.out.println("The Song have been created!");
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
            ps.setInt(5,sarki.getAlbum().getId());
            ps.setInt(6,sarki.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Hata");
            e.printStackTrace();
        }
    }


    //DELETE -> sarkı da albumün primary key-> foreign key var kontrollü silme !!!
    public static void DeleteSarkı(TblSarkı sarki) {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return;
        }

        try {
            //  bu şarkıya bağlı albümü
            String albumQuery = "SELECT COUNT(*) FROM TblSarkı WHERE AlbumID = ?";
            PreparedStatement psCheckAlbum = conn.prepareStatement(albumQuery);
            psCheckAlbum.setInt(1, sarki.getAlbum().getId());
            ResultSet rs = psCheckAlbum.executeQuery();

            if (rs.next() && rs.getInt(1) > 1) {
                //  aynı albüme ait başka şarkılar varsa
                String sql = "DELETE FROM TblSarkı WHERE SarkıID = ?";
                PreparedStatement psDeleteSong = conn.prepareStatement(sql);
                psDeleteSong.setInt(1, sarki.getId());
                psDeleteSong.executeUpdate();
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


    //READ -> hata var;
    public ArrayList<TblSarkı>getSarki(){
        ArrayList<TblSarkı>Sarki=new ArrayList<>();
       Connection conn = DataConnection.connect();
       if (conn == null) {
            System.out.println("The Connection connected failed!");
            return Sarki;
        }
        try{
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM TblSarkı";
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                Sarki.add(new TblSarkı(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6,new TblAlbum().getId())


                );

            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error occurred while reading the sarki!");
        }

    }





    }


    //read kısmını arraylist metotla yap;












