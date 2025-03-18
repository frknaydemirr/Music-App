//Şarkı eklenince -> TblÇalmaListesine (many-to-many )   de SarkıID ile eklenmeli, silinince TblÇalmaListesi nden de silinmeli
//güncelleme durumunda da aynı şekilde işlemler yapılmalıdır!

import com.example.musicapp.model.TblCalmaListesiSarkı;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.example.musicapp.model.TblCalmaListesi; //buradan foreign key olan CalmaListesi -> id yi alır!


//CREATE METODU: -> ÇalmaListesine şarkı eklenince TblSarkıya da şarkılar eklenecek; -> TblSarkıda create metodunun içinde çağıracağız metodu!
public class CalmaListesiSarkiDAO {
private static final Logger LOGGER = Logger.getLogger(CalmaListesiSarkiDAO.class.getName());

    //CREATE WITH MODEL CLASS
    public static void CreateCalmaListesiSarkı(int sarkiID , int CalmaListesiID){
        Connection conn = DataConnection.connect();
        if(conn==null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql= "INSERT INTO TBLCalmaListesiSarkı  (CalmaListesiID, SarkıID)   VALUES (?,?) ";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,CalmaListesiID);
            ps.setInt(2,sarkiID);
            ps.executeUpdate();
            System.out.println("The song has been added to list succesfully!");

        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error while creating!");
        }
    }



    //const oluşlturuldu -> READ WITH MODAL CLASS
    public ArrayList<TblCalmaListesiSarkı>getCalmaListesiSarki(){
        ArrayList<TblCalmaListesiSarkı>calmaListesiSarkı=new ArrayList<>();
        Connection conn = DataConnection.connect();
        if(conn==null){
            System.out.println("The Connection connected failed ! ");
            return calmaListesiSarkı ;
        }
        try {
            Statement st=conn.createStatement();
            String sql="SELECT * FROM TblCalmaListesiSarkı";
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                calmaListesiSarkı.add(
                        new TblCalmaListesiSarkı(
                                rs.getInt(1),
                                rs.getInt(2)

                ));

            }
        }

        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error while getting calmaListesiSarki!");
        }return  calmaListesiSarkı;
    }



    //update with modal class -> Primary key yok dikkat! -> başarılı bi şekilde update sağladm!
    //eklenmesi gereken bu tablo -> çalmalistesiyle ilişkilki olduğpu için onu da etkiler değişimleri!!!
    public static void UpdateCalmaListesiSarki(TblCalmaListesiSarkı calmaListesiSark){
        Connection conn = DataConnection.connect();
        if(conn==null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="UPDATE TblCalmaListesiSarkı SET CalmaListesiID= ? , SarkıID = ? WHERE CalmaListesiID = ? AND  SarkıID = ?";
        if(calmaListesiSark.getoldcalmaListesiId()==null || calmaListesiSark.getSarkıId()==null){
            LOGGER.warning("The old CalmaListesiID or SarkıID şs null!");
            return;
        }
        LOGGER.info("Çalma listesi kontrolü başarılı.");
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,calmaListesiSark.getSarkıId());
            ps.setInt(2,calmaListesiSark.getÇalmaListesiId());
            ps.setInt(3,calmaListesiSark.getoldcalmaListesiId());
            ps.setInt(4,calmaListesiSark.getoldSarkıId());
            ps.executeUpdate();
            System.out.println("The Playlist Song has been updated!");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while updating!");
        }
    }




    public static void DeleteCalmaListesiSarki(int sarkiID){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("Veritabanı bağlantısı başarısız!");
            return;
        }
        String sql= "DELETE FROM TblCalmaListesiSarkı WHERE SarkıID = ?";
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
        }



