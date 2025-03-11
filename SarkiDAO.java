import com.example.musicapp.model.TblSarkı;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


//sarkının olayı->şarkı ekleyebilir; silebilir ve güncelleyebilir!
public class SarkiDAO {
    //model class->TblSarkı;

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
}
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
        PreparedStatement ps= conn.prepareStatement();
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