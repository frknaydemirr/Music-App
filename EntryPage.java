import javax.swing.*;

public class EntryPage extends JFrame {
    private JPanel contentPane;
    private JLabel First;
    private JTextField textField1;


    public EntryPage(){

        setTitle("Giriş Sayfası"); // Başlık ekleyin
        setSize(550, 800); // Pencerenin boyutunu belirle
        setLocationRelativeTo(null); // Ekranın ortasına al
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        contentPane.setSize(550,800);


        // 2. JLabel oluştur ve panele ekle
        First = new JLabel("Hoşgeldiniz");
        First.setBounds(50, 50, 100, 30);  // Konum ve boyut (x, y, genişlik, yükseklik)
        contentPane.add(First);

        // 3. JTextField oluştur ve panele ekle
        textField1 = new JTextField();
        textField1.setBounds(50, 100, 200, 30);
        contentPane.add(textField1);

        setVisible(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static  void main(String[] args){
        new EntryPage();
    }



}
