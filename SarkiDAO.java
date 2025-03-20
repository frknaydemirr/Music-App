import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblCalmaListesi;
import com.example.musicapp.model.TblSarkı;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


//Admin; yeni sanatçı, şarkı ve albüm ekleyebilecek ya da silme güncelleme işlemleri
//yapabilecektir



public class SarkiDAO {
    private static final Logger LOGGER = Logger.getLogger(CalmaListesiSarkiDAO.class.getName());




    public static void CreateSong(TblSarkı sarkı, int CalmaListesiID,List<Integer> sanatciIDList) throws SQLException {
        java.sql.Date sqlDate = java.sql.Date.valueOf(sarkı.getTarih());
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        conn.setAutoCommit(false);
        try {
            String albumsql = "SELECT AlbumID FROM TblAlbum";
            PreparedStatement albumps = conn.prepareStatement(albumsql);
            ResultSet rsalbum = albumps.executeQuery();
            ArrayList<Integer> AlbumIdList = new ArrayList<Integer>();
            while (rsalbum.next()) {
                AlbumIdList.add(rsalbum.getInt("AlbumID"));
            }
            if (!AlbumIdList.contains(sarkı.getAlbum().getId())) {
                LOGGER.warning("Invalid Album ID");
                conn.rollback();
                return;
            }
            String sql = "INSERT INTO TblSarkı (SarkıAd,Tarih,Sure,DinlenmeSayi,AlbumID) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sarkı.getSarkıAd());
            ps.setDate(2, sqlDate);
            ps.setInt(3, sarkı.getSure());
            ps.setInt(4, sarkı.getDinlenmeSayi());
            ps.setInt(5, sarkı.getAlbum().getId());
            ps.executeUpdate();
            System.out.println("The Song have been created!");
            ResultSet rs= ps.getGeneratedKeys();//sarkıID -> Çekecez! -> sonra CreateCalmaListesiSarkı() metodunun içinbe set edip çağıracaz!
            while (rs.next()) {
                int sarkiID = rs.getInt(1); //primary key ile aldığım id yi çektim;
                System.out.println("SarkıID: " + sarkiID);
                CalmaListesiSarkiDAO.CreateCalmaListesiSarkı(sarkiID, CalmaListesiID); //->yeni bir şarkı oluşturulunca bu oluşuturulan şarkı -> tblçalmalistesisarkı da da oluşturulur:
                sanatciIDList.forEach( sanatciID->{
                    SarkiSantaciDAO.CreateSarkiSanatci(sanatciID, sarkiID);
                });

            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("An error occured while creating song  !");
            e.printStackTrace();
            conn.rollback();

        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }


    public static void UpdateSarki(TblSarkı eskiSarki, TblSarkı yeniSarki) throws SQLException {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        conn.setAutoCommit(false);
        ResultSet rsalbum = null;
        PreparedStatement albumps = null;
        try {
            String albumsql = "SELECT AlbumID FROM TblAlbum";//olmayan albumu kullanıcı sarkıyı güncelleyeceğinde set etmemeli!
            albumps = conn.prepareStatement(albumsql);
            rsalbum = albumps.executeQuery();
            ArrayList<Integer> AlbumIdList = new ArrayList<Integer>();
            while (rsalbum.next()) {
                AlbumIdList.add(rsalbum.getInt("AlbumID"));
            }
            if (!AlbumIdList.contains(yeniSarki.getAlbum().getId())) {
                LOGGER.warning("Invalid Album ID");
                conn.rollback();
                return;
            }
            if(eskiSarki.getId()==null ){
                LOGGER.warning("ID is NULL!");
                return;
            }
            String sql = "UPDATE TblSarkı SET SarkıAd = ? , Tarih  = ? , sure = ?, dinlenmeSayi = ? , AlbumID = ? WHERE SarkıID = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, yeniSarki.getSarkıAd());
            ps.setDate(2, java.sql.Date.valueOf(yeniSarki.getTarih()));
            ps.setInt(3, yeniSarki.getSure());
            ps.setInt(4, yeniSarki.getDinlenmeSayi());
            ps.setInt(5, yeniSarki.getAlbum().getId());
            ps.setInt(6, eskiSarki.getId());
            ps.executeUpdate();
            LOGGER.info("The song has been updated successfully! ");
            conn.commit();

        } catch (Exception e) {
            System.out.println("Error occuring while updating TblSarkı ");
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (rsalbum != null) rsalbum.close();
            if (albumps != null) albumps.close();
            if (conn != null) conn.close();
        }
    }







    //  -> tabloya UI a dökeceğiz bunu!
    public ArrayList<TblSarkı> getSarki() {
        ArrayList<TblSarkı> Sarki = new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return Sarki;
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM TblSarkı";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TblAlbum album = new TblAlbum();
                album.setId(rs.getInt(6));

                LocalDate tarih = (rs.getDate(3) != null) ? rs.getDate(3).toLocalDate() : null;

                Sarki.add(new TblSarkı(
                        rs.getInt(1),
                        rs.getString(2),
                        tarih,
                        rs.getInt(4),
                        rs.getInt(5),
                        album
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while reading the sarki!");
        }
        return Sarki;
    }









public static void DeleteSong(TblSarkı sarki) throws SQLException {
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed!");
            return;
        }
    if (sarki == null || sarki.getId() == null) {
        LOGGER.warning("Error: The song object or its ID is null!");
        return;
    }
    conn.setAutoCommit(false);
    int ID = sarki.getId();
    int  albumID=sarki.getAlbum().getId();

    try {
        CalmaListesiSarkiDAO.DeleteCalmaListesiSarki(ID);
        SarkiSantaciDAO.DeleteSarkiSanatci(ID);
        String sql = "DELETE FROM TblSarkı WHERE SarkıID = ?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,ID);
        ps.executeUpdate();
        LOGGER.info("All Data Deleted successfully (TblSarkı, TblSarkıSanatcı , TblCalmaListesiSarkı) !");
        String albumReadsql="SELECT COUNT(*) AS ŞarkıSayısı FROM TblSarkı WHERE AlbumID = ?";
        PreparedStatement albumReadps=conn.prepareStatement(albumReadsql);
        albumReadps.setInt(1, albumID);
        ResultSet rs=albumReadps.executeQuery();
        if(rs.next() && rs.getInt("ŞarkıSayısı")==0){
            String AlbumDeletesql = "DELETE FROM TblAlbum WHERE AlbumID = ?";
            PreparedStatement psDeleteAlbum=conn.prepareStatement(AlbumDeletesql);
            psDeleteAlbum.setInt(1, albumID);
            psDeleteAlbum.executeUpdate();
            LOGGER.info("The album was deleted because it was empty!");
        }
        conn.commit();
    }
    catch (SQLException e) {
        conn.rollback();
        e.printStackTrace();
        conn.close();
    }

}









    }















