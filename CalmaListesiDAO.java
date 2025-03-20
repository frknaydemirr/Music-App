import com.example.musicapp.model.TblCalmaListesi;
import com.example.musicapp.model.TblKullanıcı;
import com.example.musicapp.model.TblCalmaListesiSarkı;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;



public class CalmaListesiDAO {
private  static final Logger LOGGER = Logger.getLogger(CalmaListesiDAO.class.getName());



public static void CreateCalmaListesi(TblCalmaListesi CalmaListesi ) throws SQLException {
    Connection conn = DataConnection.connect();
    if (conn == null) {
        System.out.println("The connection has been established.");
        return;
    }
    conn.setAutoCommit(false);
    ResultSet rscalmalistesi = null;
    PreparedStatement calmalistesips = null;
    String sql= "INSERT INTO TblCalmaListesi  (KullaniciID, MuzıkTur, CalmaListesi)  VALUES (?,?,?)";

    try {
        String kullanicisql="SELECT KullaniciID FROM TblKullanıcı";
        calmalistesips = conn.prepareStatement(kullanicisql);
        rscalmalistesi = calmalistesips.executeQuery();
        ArrayList<Integer>KullaniciIDList=new ArrayList<>();
        while (rscalmalistesi.next()) {
            KullaniciIDList.add(rscalmalistesi.getInt(1));
        }
        if (!KullaniciIDList.contains(CalmaListesi.getKullaniciID().getId())) {
            LOGGER.warning("Invalid Kullanıcı ID");
            conn.rollback();
            return;
        }
        PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if(CalmaListesi.getKullaniciID()!=null && CalmaListesi.getKullaniciID().getId()!=null){
            ps.setInt(1,CalmaListesi.getKullaniciID().getId());
        }
        else {
            LOGGER.warning(" KullanıcıID is NULL");
            conn.rollback();
            return;
        }
        ps.setString(2,CalmaListesi.getMuzıkTur());
        ps.setString(3,CalmaListesi.getCalmaListesi());
        ps.executeUpdate();
        LOGGER.info("The song has been added to CalmaList");
        conn.commit();

    } catch (SQLException e) {
        e.printStackTrace();
        conn.rollback();
        System.out.println("Error while creating!");
    }

}



    public ArrayList<TblCalmaListesi>getAllCalmaList() throws SQLException {
        ArrayList<TblCalmaListesi> calmaListesi = new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The connection has been established.");
            return calmaListesi;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql= "SELECT  * FROM TblCalmaListesi";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TblKullanıcı kullanici=new TblKullanıcı();
                kullanici.setId(rs.getInt(2));
                calmaListesi.add(new TblCalmaListesi(
                        rs.getInt(1),
                        kullanici,
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.close();
        }

        return calmaListesi;
    }


    public static void UpdateCalmaList(TblCalmaListesi CalmaListesi) throws SQLException {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The connection has been established.");
            return;
        }
        conn.setAutoCommit(false);
        ResultSet rscalmalistesi = null;
        PreparedStatement calmalistesips = null;
        try {
            String kullanicisql="SELECT KullaniciID FROM TblKullanıcı";
            calmalistesips = conn.prepareStatement(kullanicisql);
            rscalmalistesi = calmalistesips.executeQuery();
            ArrayList<Integer>KullaniciIDList=new ArrayList<>();
            while (rscalmalistesi.next()) {
                KullaniciIDList.add(rscalmalistesi.getInt(1));
            }
            if (!KullaniciIDList.contains(CalmaListesi.getKullaniciID().getId())) {
                LOGGER.warning("Invalid Kullanıcı ID");
                conn.rollback();
                return;
            }
            String sql= "UPDATE TblCalmaListesi SET   KullaniciID = ? ,MuzıkTur = ? , CalmaListesi = ?  WHERE CalmaListesiID = ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1,CalmaListesi.getKullaniciID().getId());
            ps.setString(2,CalmaListesi.getMuzıkTur());
            ps.setString(3,CalmaListesi.getCalmaListesi());
            ps.setInt(4,CalmaListesi.getId());
            ps.executeUpdate();
            LOGGER.info("The CalmaList has been updated successfully! ");
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }

    }


    public static void DeleteCalmaList(TblCalmaListesi calmaListesi) throws SQLException {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The connection has been established.");
            return;
        }
        try {
            String sql= "DELETE FROM TblCalmaListesi WHERE CalmaListesiID = ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1,calmaListesi.getId());
            ps.executeUpdate();
            System.out.println("The list has been deleted!");


        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting!");
            conn.close();
        }

    }



}
