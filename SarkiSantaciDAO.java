//Şarkı eklenip,silinip,güncellenince de TblSarkıSanatçı(many-to-many ) ilişkisi olan tabloda da aynı işlemler gerçekleşmeli!


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SarkiSantaciDAO {


    //şarkı silinince şarkı sanatçı başarılı bi şekilde siliniyor!
    public static void DeleteSarkiSanatci(int sarkiID){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("Veritabanı bağlantısı başarısız!");
            return;
        }
        String sql= "DELETE FROM TblSarkıSanatcı WHERE SarkıID = ?";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,sarkiID);
            ps.executeUpdate();
            System.out.println("The songlist has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while deleting!");
        }

    }
    public static void CreateSarkiSanatci(int sanatcıID,int sarkiID){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("Veritabanı bağlantısı başarısız!");
            return;
        }
        String sql= "INSERT INTO TblSarkıSanatcı (SanatcıID,SarkıID) VALUES (?,?)";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,sanatcıID);
            ps.setInt(2,sarkiID);
            ps.executeUpdate();
            System.out.println("The songlist has been created!");

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting!");
        }
    }

    }



