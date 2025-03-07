import javax.swing.*;

public class EntryPage extends JFrame {
    private JPanel contentPane;
    private JLabel First;
    private JTextField textField1;


    public EntryPage(){

        setTitle("Giriş Sayfası");
        setSize(550, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        contentPane.setSize(550,800);



        First = new JLabel("Hoşgeldiniz");
        First.setBounds(50, 50, 100, 30);
        contentPane.add(First);


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
