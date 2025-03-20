import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblAbonelik;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


//kullanıcı ekleme gereksiz oldu-> ancak yeni kayıt alınırsa lazım!
public class KullaniciDAO {

    public static void  createUser(TblKullanıcı kullanici){
        Connection conn = DataConnection.connect();
        if(conn == null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="INSERT INTO TblKullanıcı (kullaniciAd,Email,Sifre,UlkeID,AbonelikID) VALUES (?,?,?,?,?)";
        try {
            //priId->almayız;
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,kullanici.getKullaniciAd());
            ps.setString(2,kullanici.getEmail());
            ps.setString(3,kullanici.getSifre());
            ps.setInt(4,kullanici.getUlkeID().getId()); //foreıgn key
            ps.setInt(5,kullanici.getAbonelikID().getId()); //foreıgn key
            ps.executeUpdate();
            System.out.println("User have been created!");


        } catch (Exception e) {
            System.out.println("Kullanıcı eklenirken hata oluştu!");
            e.printStackTrace();
        }


    }
    //UPDATE->başarılı;
    public static void updateUser(TblKullanıcı kullanici){
        Connection conn = DataConnection.connect();
        if(conn == null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql = "UPDATE TblKullanıcı SET KullaniciAd = ?, Email = ?, Sifre = ?, UlkeID = ?, AbonelikID = ? WHERE KullaniciID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kullanici.getKullaniciAd());
            ps.setString(2, kullanici.getEmail());
            ps.setString(3, kullanici.getSifre());
            ps.setInt(4, kullanici.getUlkeID().getId());
            ps.setInt(5, kullanici.getAbonelikID().getId());
            ps.setInt(6, kullanici.getId());
            ps.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating user!");
            e.printStackTrace();
        }
    }
    }





