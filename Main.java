
import javax.swing.*;
import java.sql.Connection;
public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EntryPage entry = new EntryPage();
                entry.setVisible(true);

            }

        });


        Connection conn = DataConnection.connect();
        DataConnection.ReadData();


    }
}
