import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblSanatcı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblSarkıSanatcı;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;





public class SanatciDAO {



    public static void CreateSanatci(TblSanatcı sanatci){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        String sql="INSERT INTO TblSanatcı(SanatcıAd, UlkeID) VALUES (?,?) ";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,sanatci.getSanatcıAd());
            ps.setInt(2,sanatci.getUlkeID().getId());
            ps.executeUpdate();
            System.out.println("The actress have been created  successfully!");
        }
        catch (Exception e) {
            System.out.println("Kullanıcı eklenirken hata oluştu!");
            e.printStackTrace();

        }
    }



    public static void DeleteSanatci(TblSanatcı sanatci){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
        try{
            String sql="DELETE FROM  TblSanatcı WHERE SanatcıID= ?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,sanatci.getId());
            ps.executeUpdate();
            System.out.println("The actress have been deleted successfully!");
        }
        catch (Exception e) {
            System.out.println("While deleting the error have been created!");
            e.printStackTrace();

        }

    }

    //UPDATE
    public static void UpdateSanatci(TblSanatcı sanatci){
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return;
        }
            String sql= "UPDATE  TblSanatcı SET  SanatcıAd = ? , UlkeID = ? WHERE SanatcıID = ?";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,sanatci.getSanatcıAd());
            ps.setInt(2,sanatci.getUlkeID().getId()); //foreign key setleme
            ps.setInt(3,sanatci.getId());
            ps.executeUpdate();
            System.out.println("The actress have been updated successfully!");
        }
        catch (Exception e) {
            System.out.println("While deleting the error have been created!");
            e.printStackTrace();

        }
    }



    //READ-> arraylistte sanatçıları tutup okuma işlemi; -> bilgilerini okuyoruz ancak herhangi bir işlem yok -> tabloya dökeceğiz sonrasında; -> size(boyut) la kontrol başarılı bir şekilde sağlanıyor;
//  READ WITH MODAL CLASS
    public ArrayList<TblSanatcı> getSanatcilar() {
        ArrayList<TblSanatcı>sanatcilar=new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return sanatcilar;
        }

        try{
            String sql="SELECT * FROM TblSanatcı";
            PreparedStatement ps=conn.prepareStatement(sql);
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                TblUlke ulke=new TblUlke();
                ulke.setId(rs.getInt(3));
                sanatcilar.add(new TblSanatcı(
                        rs.getInt(1),
                        rs.getString(2),
                        ulke
                ));
            }
        }catch (Exception e) {
            System.out.println("While reading the error have been created!");
            e.printStackTrace();
        }
return sanatcilar;

    }
}
