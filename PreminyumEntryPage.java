import javax.swing.*;
import java.awt.*;

public class PreminyumEntryPage extends JFrame {
    private JPanel PreminyumMainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton oturumAçButton;

    public PreminyumEntryPage() {
        add(PreminyumMainPanel);
        setTitle("PREMINYUM ENTRY PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 800);
        oturumAçButton.setBackground(Color.GREEN);
        oturumAçButton.setForeground(Color.BLACK);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreminyumEntryPage pu = new PreminyumEntryPage();
                pu.setVisible(true);

            }

        });

    }
}
