import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    private JPanel RegisterMainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton kayitOlButton;
    private JButton anaSayfaButton;

    public RegisterPage() {
        add(RegisterMainPanel);
        setTitle("REGISTER PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 800);
        kayitOlButton.setBackground(Color.GREEN);
        kayitOlButton.setForeground(Color.BLACK);
        anaSayfaButton.setBackground(Color.GREEN);
        anaSayfaButton.setForeground(Color.BLACK);
        setVisible(true);


        anaSayfaButton.addActionListener(e->{
            new EntryPage();
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RegisterPage rp = new RegisterPage();
                rp.setVisible(true);

            }

        });
    }
}
