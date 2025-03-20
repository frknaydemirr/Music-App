
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SarkiSantaciDAO {



    public static void DeleteSarkiSanatci(int sarkiID) throws SQLException {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("Veritabanı bağlantısı başarısız!");
            return;
        }
        String sql = "DELETE FROM TblSarkıSanatcı WHERE SarkıID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sarkiID);
            ps.executeUpdate();
            System.out.println("The songlist has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting!");

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
    }

        public static void CreateSarkiSanatci ( int sanatcıID, int sarkiID){
            Connection conn = DataConnection.connect();
            if (conn == null) {
                System.out.println("Veritabanı bağlantısı başarısız!");
                return;
            }
            String sql = "INSERT INTO TblSarkıSanatcı (SanatcıID,SarkıID) VALUES (?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, sanatcıID);
                ps.setInt(2, sarkiID);
                ps.executeUpdate();
                System.out.println("The songlist has been created!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error while inserting!");
            }

        }

        public static void UpdateSarkiSanatci ( int eskiSarkiID, int yeniSarkiID) throws SQLException {
            Connection conn = DataConnection.connect();
            if (conn == null) {
                System.out.println("The Connection connected failed ! ");
                return;
            }
            String sql = "UPDATE TblSarkıSanatcı SET SarkiID = ? WHERE SarkiID = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, yeniSarkiID);
                ps.setInt(2, eskiSarkiID);
                ps.executeUpdate();

            } catch (Exception e) {
                System.out.println("Hata");
                e.printStackTrace();

            }


        }

        //READ:


    }


