import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreminyumEntryPage extends JFrame {
    private JPanel PreminyumMainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton oturumAçButton;
    private JButton anaSayfaButton;

    public PreminyumEntryPage() {
        add(PreminyumMainPanel);
        setTitle("PREMINYUM ENTRY PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 800);
        oturumAçButton.setBackground(Color.GREEN);
        oturumAçButton.setForeground(Color.BLACK);
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
                PreminyumEntryPage pu = new PreminyumEntryPage();
                pu.setVisible(true);

            }

        });

    }
}
