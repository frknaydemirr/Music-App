import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblSarkı;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;







public class AlbumDAO {
    private static final Logger LOGGER = Logger.getLogger(AlbumDAO.class.getName());

        //CREATE album -> bir albumde 1'den fazla şarkı bulunabilir ancak 1 şarkı yanlızca 1 albumde bulunur!
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



//album silinince -> şarkı direkt silinir
//şarkı silinince bağlı olan -> tblcalmalistesisarkı ve tblsarkısanatcı da silinir!
        public static void DeleteAlbum(TblAlbum album) throws SQLException {
            Connection conn = DataConnection.connect();
            if (conn == null) {
                System.out.println("The Connection connected failed ! ");
                return;
            }
            try {
                conn.setAutoCommit(false);
                String sarkiID="SELECT SarkıID from TblSarkı  WHERE AlbumID = ? "; //albumlerin içindeki şarkıların ID sini çektik;
                PreparedStatement psSarki = conn.prepareStatement(sarkiID);
                psSarki.setInt(1, album.getId());
                ResultSet rsSarki = psSarki.executeQuery(); //sarkıID leri depoladık
                while (rsSarki.next()) {
                    int Sarki_ID=rsSarki.getInt("SarkıID");
                    CalmaListesiSarkiDAO calmaListesiSarkiDAO = new CalmaListesiSarkiDAO();
                    calmaListesiSarkiDAO.DeleteCalmaListesiSarki(Sarki_ID);
                    SarkiSantaciDAO sarkiSantaciDAO = new SarkiSantaciDAO();
                    sarkiSantaciDAO.DeleteSarkiSanatci(Sarki_ID);
                }
                LOGGER.info("Deleted all data  related to songs (TblSarkı)  !");
                String songsql= "DELETE FROM TblSarkı WHERE AlbumID = ?";
                PreparedStatement psSong = conn.prepareStatement(songsql);
                psSong.setInt(1, album.getId());
                psSong.executeUpdate();
                LOGGER.info("Deleted all songs related to TblAlbum  !");
                String Albumsql = "DELETE FROM TblAlbum WHERE AlbumID = ?";
                PreparedStatement psAlbum=conn.prepareStatement(Albumsql);
                psAlbum.setInt(1, album.getId());
                psAlbum.executeUpdate();
                LOGGER.info("The Album have been Deleted successfully dependent to Album (Song) !");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                LOGGER.severe(e.getMessage());
            }
        }





    public static void UpdateAlbum(TblAlbum album){
        java.sql.Date sqlDate = java.sql.Date.valueOf(album.getTarih());
            Connection conn = DataConnection.connect();
            if (conn == null) {
                System.out.println("The Connection connected failed ! ");
                return;
            }
            String sql= "UPDATE TblAlbum SET AlbumAd = ?,  Tarih = ? ,Tur = ? WHERE AlbumID = ?";
            try {
                PreparedStatement ps= conn.prepareStatement(sql);
                ps.setString(1, album.getAlbumAd());
                ps.setDate(2, sqlDate);
                ps.setString(3, album.getTur());
                ps.setInt(4, album.getId());
                ps.executeUpdate();
                System.out.println("The Album have been updated!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    //album bilgilerini okuyoruz ancak herhangi bir işlem yok -> tabloya dökeceğiz sonrasında; -> size(boyut) la kontrol başarılı bir şekilde sağlanıyor;
    public ArrayList<TblAlbum>getSarki(){
        ArrayList<TblAlbum>albumList=new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return albumList;
        }
        try{
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM TblAlbum";
            ResultSet rs= stmt.executeQuery(sql);
        while (rs.next()){
            LocalDate tarih = (rs.getDate(3) != null) ? rs.getDate(3).toLocalDate() : null;
            albumList.add(new TblAlbum(
                    rs.getInt(1),
                    rs.getString(2),
                    tarih,
                    rs.getString(4)
            ));
        }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error occurred while reading the sarki!");
        }
return albumList;
    }




    }




