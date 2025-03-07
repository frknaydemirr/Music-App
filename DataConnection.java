import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class DataConnection {

    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-PIQ3E57;databaseName=MusicFile;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
    private static Connection conn;

    public static Connection connect(){
        try{
            conn= DriverManager.getConnection(DB_URL,USER,PASSWORD);
            System.out.println("The Connection connected succesfuly ! ");
            return conn;

        }catch(SQLException e){
            System.out.println("The Connection connected failed ! ");
            e.printStackTrace();
            return null;
        }

    }
    public static void ReadData(){
        if(conn == null){
            System.out.println("The connection could not be established ! ");
            System.out.println("Trying to connect!");
            connect();
        }
        //sql statement work on it:
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                //meta info
            ResultSet rs= stmt.executeQuery("select * from TblKullanıcı");
            ResultSetMetaData rsmd = rs.getMetaData();
                System.out.printf("%-10s %-20s %-30s %-20s %-10s %-10s%n",
                        "UserID", "UserName", "Email", "Password", "CountryID", "AbonelikID");
                System.out.println("---------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%-10d %-20s %-30s %-20s %-10d %-10d%n",
                            rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getInt(5), rs.getInt(6));
                }

            } catch (SQLException e) {
                System.out.println("Error while creating statement!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is still null, cannot create statement.");
        }
    }

    }






