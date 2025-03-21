import com.example.musicapp.model.TblCalmaListesi;
import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblAbonelik;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            String UlkeSql = "SELECT UlkeID FROM TblUlke ";
            PreparedStatement ulke = conn.prepareStatement(UlkeSql);
            ResultSet rsulke = ulke.executeQuery();
            ArrayList<Integer> ulkeler = new ArrayList<>();
            while (rsulke.next()) {
                ulkeler.add(rsulke.getInt("UlkeID"));
            }
            if (!ulkeler.contains(kullanici.getUlkeID().getId())) {
                LOGGER.warning("Invalid Ulke ID");
                conn.rollback();
                return;
            }
            String AbonelikSql = "SELECT AbonelikID FROM TblAbonelik ";
            PreparedStatement abonelik = conn.prepareStatement(AbonelikSql);
            ResultSet rsabonelik = abonelik.executeQuery();
            ArrayList<Integer> abonelikID = new ArrayList<>();
            while (rsabonelik.next()) {
                abonelikID.add(rsabonelik.getInt(1));
            }
            if (!abonelikID.contains(kullanici.getAbonelikID().getId())) {
                LOGGER.warning("Invalid Abonelik ID");
                conn.rollback();
                return;
            }
            String sql = "INSERT INTO TblKullanıcı (kullaniciAd,Email,Sifre,UlkeID,AbonelikID) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kullanici.getKullaniciAd());
            ps.setString(2, kullanici.getEmail());
            ps.setString(3, kullanici.getSifre());
            ps.setInt(4, kullanici.getUlkeID().getId());
            ps.setInt(5, kullanici.getAbonelikID().getId());
            ps.executeUpdate();
            ResultSet rskullanici = ps.getGeneratedKeys();
            int kullanici_ID = 0;
            while (rskullanici.next()) {
                kullanici_ID = rskullanici.getInt(1);
                System.out.println("KullanıcıID: " + kullanici_ID);
            }
            String[] MuzikList = {"Pop", "Jazz", "Klasik"};
            for (int i=0; i<MuzikList.length; i++) {
                String listsql = "INSERT INTO TblCalmaListesi  (KullaniciID, MuzıkTur, CalmaListesi)  VALUES (?,?,?)";
                PreparedStatement pslist = conn.prepareStatement(listsql);
                TblCalmaListesi kullaniciList = new TblCalmaListesi();
                kullaniciList.setId(kullanici_ID);
                kullaniciList.setMuzıkTur(MuzikList[i]);
                pslist.setInt(1, kullanici_ID);
                pslist.setString(2,MuzikList[i]);
                pslist.setString(3, "Çalma Listesi-" +i);
                pslist.executeUpdate();
                System.out.println("Kullanıcı Bilgiler : \n"+"KullanıcıID:"+kullanici_ID+"\tMüzik Tür: "+MuzikList[i]);
            }
            LOGGER.info("User have been created with calmaList [relation->dafeault]!");
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

    public static void  DeleteUser(TblKullanıcı kullanici) throws SQLException {
        Connection conn = DataConnection.connect();
        if(conn == null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        try {
            conn.setAutoCommit(false);
            String calmalistesisarkips="SELECT "; //devam...




            String calmaListID="SELECT CalmaListesiID from TblCalmaListesi  WHERE KullaniciID = ?";
            PreparedStatement ps = conn.prepareStatement(calmaListID);
            ps.setInt(1, kullanici.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CalmaListesiID = rs.getInt(1);
                TblCalmaListesi calmaListesi = new TblCalmaListesi();
                calmaListesi.setId(CalmaListesiID);
                CalmaListesiDAO dao = new CalmaListesiDAO();
                dao.DeleteCalmaList(calmaListesi);

            }






        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    }




//Kullanıcı silindiğinde, onun çalma listeleri ve takip ilişkileri de silinmelidir. -> aynı album gibi;





