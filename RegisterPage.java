import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {
    private JPanel RegisterMainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton kayitOlButton;

    public RegisterPage() {
        add(RegisterMainPanel);
        setTitle("REGISTER PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 800);
        kayitOlButton.setBackground(Color.GREEN);
        kayitOlButton.setForeground(Color.BLACK);
        setVisible(true);
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
