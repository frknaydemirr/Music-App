
import java.sql.Connection;
public class Main {

    public static void main(String[] args){


        Connection conn = DataConnection.connect();
        DataConnection.ReadData();


    }
}
