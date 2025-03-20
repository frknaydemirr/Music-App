import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblAbonelik;
import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class KullaniciDAO {
    private  static final Logger LOGGER = Logger.getLogger(KullaniciDAO.class.getName());

    //REGISTER USER && CREATE NEW USER -> dön!
    public static void  createUser(TblKullanıcı kullanici) throws SQLException {
        Connection conn = DataConnection.connect();
        if(conn == null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        conn.setAutoCommit(false);
        try {
            String UlkeSql="SELECT UlkeID FROM TblUlke ";
            PreparedStatement ulke = conn.prepareStatement(UlkeSql);
            ResultSet rsulke = ulke.executeQuery();
            ArrayList<Integer>ulkeler=new ArrayList<>();
            while(rsulke.next()){
                ulkeler.add(rsulke.getInt("UlkeID"));
            }
            if(!ulkeler.contains(kullanici.getUlkeID().getId())){
                LOGGER.warning("Invalid Ulke ID");
                conn.rollback();
                return;
            }
            String AbonelikSql="SELECT AbonelikID FROM TblAbonelik ";
            PreparedStatement abonelik = conn.prepareStatement(AbonelikSql);
            ResultSet rsabonelik = abonelik.executeQuery();
            ArrayList<Integer>abonelikID=new ArrayList<>();
            while(rsabonelik.next()){
                abonelikID.add(rsabonelik.getInt("AbonelikID"));
            }
            if(!abonelikID.contains(kullanici.getAbonelikID())){
                LOGGER.warning("Invalid Abonelik ID");
                conn.rollback();
                return;
            }
            String sql="INSERT INTO TblKullanıcı (kullaniciAd,Email,Sifre,UlkeID,AbonelikID) VALUES (?,?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, kullanici.getKullaniciAd());
            ps.setString(2, kullanici.getEmail());
            ps.setString(3, kullanici.getSifre());
            ps.setInt(4,kullanici.getUlkeID().getId());
            ps.setInt(5,kullanici.getAbonelikID().getId());
            ps.executeUpdate();
            System.out.println("User have been created!");
            conn.commit();


        } catch (Exception e) {
            System.out.println("error occurred while creating user!");
            e.printStackTrace();
            conn.rollback();
        }

    }


//Giriş Yapma metodunu buraya -> GUI de buton click eventiyle bilgiler buradan çekilsin veritabanındaki;













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






