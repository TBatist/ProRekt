import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_From {
    private JTabbedPane tabbedPane;
    private JPanel panel;
    private JTextField textNaam;
    private JTextField textAdres;
    private JTextField textPostcode;
    private JTextField textWoonplaats;
    private JTextField textTelnr;
    private JTextField textEmail;
    private JTextField textGebdatum;
    private JRadioButton manRadioButton;
    private JRadioButton vrouwRadioButton;
    private JRadioButton andersRadioButton;
    private JTextField textRating;
    private JRadioButton jaRadioButton;
    private JRadioButton neeRadioButton;
    private JLabel labelNaam;
    private JLabel labelAdres;
    private JLabel labelPostcode;
    private JLabel labelWoonplaats;
    private JLabel labelTelnr;
    private JLabel labelEmail;
    private JLabel labelGebdatum;
    private JLabel labelGeslacht;
    private JLabel labelRating;
    private JLabel labelBekspeler;
    private JButton registreerButton;

    public GUI_From() {
        registreerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Full House");
        frame.setContentPane(new GUI_From().panel);
        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
