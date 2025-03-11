import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEntryPage extends JFrame {
    private JTextField girişbtn;
    private JTextField parolabtn;
    private JButton oturumAçButton;
    private JPanel MainUserPanel;
    private JButton anaSayfaButton;

    public UserEntryPage() {
        add(MainUserPanel);
        setTitle("USER ENTRY PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750,800);
        oturumAçButton.setBackground(Color.GREEN);
        oturumAçButton.setForeground(Color.BLACK);
        anaSayfaButton.setBackground(Color.GREEN);
        anaSayfaButton.setForeground(Color.BLACK);
        setVisible(true);


        //kullanıcı arayüzüne geçiş yapar: -> müzik listesi, hoşgeldin F.A, takipçi, takip edilen sayısı, çalma listesine müzik ekle,->mevcut olan şarkı varsa eklenmez çalma listesine;
    oturumAçButton.addActionListener(e->{

    });

    anaSayfaButton.addActionListener(e->{
        new EntryPage();
        dispose();
    });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserEntryPage user = new UserEntryPage();
                user.setVisible(true);

            }

        });
    }
}
