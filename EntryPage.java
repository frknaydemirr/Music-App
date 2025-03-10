import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryPage  extends  JFrame {

    private JPanel MainPanel;
    private JButton preminyumGirişiButton;
    private JButton kullanıcıGirişiButton;
    private JButton adminOlarakDevamEtButton;
    private JButton kayıtOlButton;

    public EntryPage() {
        add(MainPanel);
        setTitle("EntryPage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 800);


        //admin girişi;
        adminOlarakDevamEtButton.addActionListener(e-> {
            System.out.println("Hello World!");
            //admin giriş paneli;
            dispose();

        });


        //kullanıcı girişi;
        kullanıcıGirişiButton.addActionListener(e-> {
            System.out.println("Kullanıcı Giriş Yapıyor!");
            UserEntryPage userEntryPage = new UserEntryPage(); //-> open user ;
            dispose();

        });
        //preminyum giriş:
    preminyumGirişiButton.addActionListener(e-> {
        System.out.println("Preminyum giriş");
        PreminyumEntryPage pe=new PreminyumEntryPage();
        dispose();

    });

    //kayit ol
        kayıtOlButton.addActionListener(e-> {
            System.out.println("Kayıt olma...");
            new RegisterPage();
            dispose();
        });

    }
    public static void main(String[] args) {
//        new EntryPage().setVisible(true);
    }
}
