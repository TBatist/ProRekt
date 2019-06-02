import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    private JLabel labelBekspeler;
    private JButton registreerButton;
    private JTextField textDatumT;
    private JTextField textBtijdT;
    private JTextField textEtijdT;
    private JTextField textBeschrijvingT;
    private JTextField textWinnaarT;
    private JTextField textMaxinschrijvingenT;
    private JTextField textInleggeldT;
    private JTextField textInschrijfdatumT;
    private JButton voegToeButtonT;
    private JLabel labelDatumT;
    private JLabel labelBtijdT;
    private JLabel labelEtijdT;
    private JLabel labelBeschrijvingT;
    private JLabel labelWinnaarT;
    private JLabel labelMaxinschrijvingenT;
    private JLabel labelInleggeldT;
    private JLabel labelInschrijfdatumT;
    private JTextField textDatumMC;
    private JTextField textBeginMC;
    private JTextField textEindMC;
    private JTextField textPrijsMC;
    private JTextField textMinratingMC;
    private JButton voegToeButtonMC;
    private JLabel labelDatumMC;
    private JLabel labelBeginMC;
    private JLabel labelEindMC;
    private JLabel labelPrijsMC;
    private JLabel labelMinratingMC;

    private PreparedStatement ps;
    private String insertGast = "INSERT INTO gast (naam, adres, postcode, woonplaats, telnr, email, gebdatum, geslacht, bekspeler) VALUES(?,?,?,?,?,?,?,?,?)";

    public GUI_From() {
        registreerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naam = textNaam.getText();
                String adres = textAdres.getText();
                String postcode = textPostcode.getText();
                String woonplaats = textWoonplaats.getText();
                long telnr = Long.parseLong(textTelnr.getText());
                String email = textEmail.getText();
                Date gebdatum = Date.valueOf(textGebdatum.getText());
                String geslacht = "";
                if(manRadioButton.isSelected()){
                    geslacht = "M";
                }else if(vrouwRadioButton.isSelected()){
                    geslacht = "V";
                }else if(andersRadioButton.isSelected()){
                    geslacht = "A";
                }
                String bekSpeler = "";
                if(jaRadioButton.isSelected()){
                    bekSpeler = "J";
                }else if(neeRadioButton.isSelected()){
                    bekSpeler = "N";
                }

                try{
                    ps = ConnectionManager.getConnection().prepareStatement(insertGast);
                    ps.setString(1, naam);
                    ps.setString(2, adres);
                    ps.setString(3,postcode);
                    ps.setString(4, woonplaats);
                    ps.setLong(5, telnr);
                    ps.setString(6, email);
                    ps.setDate(7, gebdatum);
                    ps.setString(8, geslacht);
                    ps.setString(9, bekSpeler);

                    ps.executeUpdate();

                }catch(SQLException exception){
                    exception.printStackTrace();
                }
                //TODO Voeg insert van de database toe met bovenstaande variabelen.
                // PreparedStatement s = ConnectionManager.getConnection().prepareStatement("INSERT INTO Person VALUES (?, ?, ?)"); Dit kan je evt gebruiken
                //s.setInt(1, .getId());
                //            s.setString(2, //todo());
                //            s.setInt(3, //todo());
                //            s.execute();
                //            model.addElement(//todo);
                // zie voor uitleg java-bestand van docent op BB

            }
        });

        voegToeButtonT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date datum = Date.valueOf(textDatumT.getText());
                String beginTijd = textBtijdT.getText();
                String eindTijd = textEtijdT.getText();
                String beschrijving = textBeschrijvingT.getText();
                String winnaar = textWinnaarT.getText();
                int maxInschrijvingen = Integer.parseInt(textMaxinschrijvingenT.getText());
                int inleggeld = Integer.parseInt(textInleggeldT.getText());
                Date inschrijfdatum = Date.valueOf(textInschrijfdatumT.getText());

                //TODO Voeg insert van de database toe met bovenstaande variabelen.

            }
        });

        voegToeButtonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date datum = Date.valueOf(textDatumMC.getText());
                String beginTijd = textBeginMC.getText();
                String eindTijd = textEindMC.getText();
                int prijs = Integer.parseInt(textPrijsMC.getText());
                int minRating = Integer.parseInt(textMinratingMC.getText());

                //TODO Voeg insert van de database toe met bovenstaande variabelen.

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
