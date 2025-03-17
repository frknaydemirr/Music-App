//Şarkı eklenince -> TblÇalmaListesine (many-to-many )   de SarkıID ile eklenmeli, silinince TblÇalmaListesi nden de silinmeli
//güncelleme durumunda da aynı şekilde işlemler yapılmalıdır!

import com.example.musicapp.model.TblCalmaListesiSarkı;
import java.sql.*;
import java.util.ArrayList;

import com.example.musicapp.model.TblCalmaListesi; //buradan foreign key olan CalmaListesi -> id yi alır!


//CREATE METODU: -> ÇalmaListesine şarkı eklenince TblSarkıya da şarkılar eklenecek; -> TblSarkıda create metodunun içinde çağıracağız metodu!
public class CalmaListesiSarkiDAO {


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



    //update with modal class -> Primary key yok dikkat!
    public static void UpdateCalmaListesiSarki(TblCalmaListesiSarkı calmaListesiSark){
        Connection conn = DataConnection.connect();
        if(conn==null){
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="UPDATE TblCalmaListesiSarkı SET CalmaListesiID= ? , SarkıID = ? WHERE CalmaListesiID = ? AND  SarkıID = ?";

        try{
            int CalmaListOldID= calmaListesiSark.getÇalmaListesiId();
            int SarkiOldID=calmaListesiSark.getSarkıId();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,calmaListesiSark.getSarkıId());
            ps.setInt(2,calmaListesiSark.getÇalmaListesiId());
            ps.setInt(3,CalmaListOldID);
            ps.setInt(4,SarkiOldID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while updating!");
        }
    }


}
