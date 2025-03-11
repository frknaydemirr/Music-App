import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblSarkı;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AlbumDAO {
//create album
        public static void CreateAlbum(TblAlbum album){
            Connection conn = DataConnection.connect();
            java.sql.Date sqlDate = java.sql.Date.valueOf(album.getTarih()); //localdate türünde olduğu için;
            if (conn == null) {
                System.out.println("The Connection connected failed ! ");
                return;
            }
            String sql = "INSERT INTO TblAlbum (AlbumAd,Tarih,Tur) VALUES (?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, album.getAlbumAd());
                ps.setDate(2, sqlDate);
                ps.setString(3, album.getTur());
                ps.executeUpdate();
                System.out.println("The Album have been created!");
            } catch (Exception e) {
                System.out.println("Album Error!");
                e.printStackTrace();

            }
        }
    }


//delete album with model Class


//update album with model Class
