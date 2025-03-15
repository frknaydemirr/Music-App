//Şarkı eklenince -> TblÇalmaListesine (many-to-many )   de SarkıID ile eklenmeli, silinince TblÇalmaListesi nden de silinmeli
//güncelleme durumunda da aynı şekilde işlemler yapılmalıdır!

import com.example.musicapp.model.TblCalmaListesiSarkı;
import java.sql.*;
import com.example.musicapp.model.TblCalmaListesi; //buradan foreign key olan CalmaListesi -> id yi alır!


//CREATE METODU: -> ÇalmaListesine şarkı eklenince TblSarkıya da şlarkılar eklenecek; -> TblSarkıda create metodunun içinde çağıracağız metodu!
public class CalmaListesiSarkiDAO {

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




}
