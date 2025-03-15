import com.example.musicapp.model.TblCalmaListesi;
import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblCalmaListesiSarkı;
import java.sql.*;


public class CalmaListesiDAO {

public static void CreateCalmaListesi(TblCalmaListesi CalmaListesi ) {
    Connection con = DataConnection.connect();
    if (con == null) {
        System.out.println("The connection has been established.");
        return;
    }
    String sql= "INSERT INTO TblCalmaListesi  (KullaniciID, MuzıkTur, CalmaListesi)  VALUES (?,?,?)";

    try {
        PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if(CalmaListesi.getKullaniciID()!=null && CalmaListesi.getKullaniciID().getId()!=null){
            ps.setInt(1,CalmaListesi.getKullaniciID().getId());//-> foreign key set D:;
        }
        else {
            System.out.println("Hata!! Kullanıcı Id yok!");
        }
        ps.setString(2,CalmaListesi.getMuzıkTur());
        ps.setString(3,CalmaListesi.getCalmaListesi());
        ps.executeUpdate();
        System.out.println("Calma Listesine şarkı  eklendi.");
        ResultSet rs=ps.getGeneratedKeys();
        while (rs.next()) {
            int CalmalistID = rs.getInt(1);
            System.out.println("CalmalistID :" +CalmalistID);
        }


    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error while creating!");
    }

}








}
