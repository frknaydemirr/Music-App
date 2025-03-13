import com.example.musicapp.model.TblAlbum;
import com.example.musicapp.model.TblSanatcı;
import com.example.musicapp.model.TblUlke;
import com.example.musicapp.model.TblSarkıSanatcı;
//Sarkı tablosu -> hem tbl albumle bağlantılı hem tblsarkısanatçıyla  (album un primary key-> forign key e sahip)
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Admin; yeni sanatçı, şarkı ve albüm ekleyebilecek ya da silme güncelleme işlemleri
public class SanatciDAO {

//UlkeID-> foreign key mevcut buraya dikkat!
    //CREATE Sanatcı
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


    //DELETE SANATCI -> Dikkat primary key mevcut->UlkeID
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
//    READ -> hata var;
    public ArrayList<TblSanatcı> getSanatcilar() {
        ArrayList<TblSanatcı>sanatcilar=new ArrayList<>();
        Connection conn = DataConnection.connect();
        if (conn == null) {
            System.out.println("The Connection connected failed ! ");
            return sanatcilar;
        }
        String sql="SELECT * FROM TblSanatcı";
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            TblUlke ulke=new TblUlke();
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                sanatcilar.add(new TblSanatcı(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3,new TblUlke().getId()); //foreign key okuma işlemi sıkıntı!!!

                ));


            }
        }catch (Exception e) {
            System.out.println("While reading the error have been created!");
            e.printStackTrace();
        }
return sanatcilar;

    }
}
