import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminPage  extends JFrame {
    private JButton albumlerButton;
    private JButton top10Button;
    private JButton calmaListesiButton;
    private JButton sanatcilarButton;
    private JButton sarkilarButton;
    private JButton guncelleButton;
    private JButton silButton;
    private JButton ekleButton;
    private JButton gosterButton;
    private JLabel MainAdminLabel;
    private JPanel MainPanel;
    private JTable table1;
    private JButton kullaniciListesiButton;


    public AdminPage() {
        add(MainPanel);

        setTitle("Admin PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 800);
        albumlerButton.setBackground(Color.GRAY);
        top10Button.setBackground(Color.GRAY);
        calmaListesiButton.setBackground(Color.GRAY);
        sanatcilarButton.setBackground(Color.GRAY);
        sarkilarButton.setBackground(Color.GRAY);
        guncelleButton.setBackground(Color.GRAY);
        silButton.setBackground(Color.RED);
        ekleButton.setBackground(Color.GREEN);
        gosterButton.setBackground(Color.GRAY);
        kullaniciListesiButton.setBackground(Color.GRAY);
        setVisible(true);
        table1=new JTable();








    }
//    public void loadUserData() {
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("UserID");
//        model.addColumn("UserName");
//        model.addColumn("Email");
//        model.addColumn("Password");
//        model.addColumn("CountryID");
//        model.addColumn("MemberID");
//        Connection conn = DataConnection.connect();
//        if(conn!=null){
//            try{
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery("select * from TblKullanıcı");
//                while (rs.next()){
//                    Object[] row= new Object[6];
//                    row[0] = rs.getInt("KullaniciID");
//                    row[1] = rs.getString("KullaniciAd");
//                    row[2] = rs.getString("Email");
//                    row[3] = rs.getString("Sifre");
//                    row[4] = rs.getInt("UlkeID");
//                    row[5] = rs.getInt("AbonelikID");
//                    model.addRow(row);
//                }
//                table1.setModel(model);
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminPage ad = new AdminPage();
                ad.setVisible(true);

            }

        });
    }
}
