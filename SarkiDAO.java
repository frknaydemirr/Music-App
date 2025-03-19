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
        String sql = "INSERT INTO TblSarkı (SarkıAd,Tarih,Sure,DinlenmeSayi,AlbumID) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sarkı.getSarkıAd());
            ps.setDate(2, sqlDate);
            ps.setInt(3, sarkı.getSure());
            ps.setInt(4, sarkı.getDinlenmeSayi());
            ps.setInt(5, sarkı.getAlbum().getId());
            ps.executeUpdate();
            System.out.println("The Song have been created!");
            ResultSet rs= ps.getGeneratedKeys();//sarkıID -> Çekecez! -> sonra CreateCalmaListesiSarkı() metodunun içinbe set edip çağıracaz!
            conn.setAutoCommit(false);
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
            System.out.println("Kullanıcı eklenirken hata oluştu!");
            e.printStackTrace();
            conn.rollback();

        }
    }



    //EKSİK -> DÖN!
    public static void UpdateSarki(TblSarkı eskiSarki, TblSarkı yeniSarki){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="UPDATE TblSarkı SET SarkıAd = ? , Tarih  = ? , sure = ?, dinlenmeSayi = ? , AlbumID = ? WHERE SarkıID = ? ";
        try {
            if(eskiSarki.getId()!=yeniSarki.getId()){
                SarkiSantaciDAO.UpdateSarkiSanatci(eskiSarki.getId(),yeniSarki.getId());
                CalmaListesiSarkiDAO.UpdateCalmaListesiSarki(eskiSarki.getId(),yeniSarki.getId());
                LOGGER.info("All Data Updated successfully (TblSarkı, TblSarkıSanatcı , TblCalmaListesiSarkı) !");
            }
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,yeniSarki.getSarkıAd());
            ps.setDate(2,java.sql.Date.valueOf(yeniSarki.getTarih()));
            ps.setInt(3,yeniSarki.getSure());
            ps.setInt(4,yeniSarki.getDinlenmeSayi());
            ps.setInt(5,yeniSarki.getAlbum().getId());
            ps.setInt(6,eskiSarki.getId());
            ps.executeUpdate();
            LOGGER.info("The song has been updated successfully! ");



        } catch (Exception e) {
            System.out.println("Hata");
            e.printStackTrace();
        }
    }




    //    READ -> başarılı bir şekilde  constructer ve ToString() metoduyla şarkılar ekrana yazdırılıyor;; -> tabloya UI a dökeceğiz bunu!
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








//TblCalmaListesiSarkı tablosundaki ilgili kayıtlar silinmeli. -> TblSarkıSanatcı tablosundaki ilgili kayıtlar silinmeli. ->TblSarkı tablosundan şarkı silinmeli.

//şarkı silmede dikkat edeceğimiz kısım -> şarkının bulunduğu albumde bu şarkıdan başka şarkı var mı -> eğer yoksa albumu de sileceğiz [MANY-TO-ONE ilişkisi]

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
    conn.setAutoCommit(false); //ilişkide  senkronluğu sağldm
    int ID = sarki.getId();// -> id başta aldımm ki diğer tablolarla ilişkiyi silerken kolay olsun
    int  albumID=sarki.getAlbum().getId();

    try {
        CalmaListesiSarkiDAO.DeleteCalmaListesiSarki(ID);
        SarkiSantaciDAO.DeleteSarkiSanatci(ID);
        String sql = "DELETE FROM TblSarkı WHERE SarkıID = ?";
        String albumsql="SELECT COUNT(*) AS ŞarkıSayısı FROM TblSarkı WHERE AlbumID = ?"; //silinecek şarkının bulundupu albumdeki şarkı sayısı =1 ise album de direkt silinir;
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,ID);
        ps.executeUpdate();
        LOGGER.info("All Data Deleted successfully (TblSarkı, TblSarkıSanatcı , TblCalmaListesiSarkı) !");
        conn.commit();
    }
    catch (SQLException e) {
        conn.rollback();
        e.printStackTrace();
    }

}









    }















