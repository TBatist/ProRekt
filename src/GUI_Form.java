import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class GUI_Form {
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
    private JTextField textMaxinschrijvingenT;
    private JTextField textInleggeldT;
    private JTextField textInschrijfdatumT;
    private JButton voegToeButtonT;
    private JLabel labelDatumT;
    private JLabel labelBtijdT;
    private JLabel labelEtijdT;
    private JLabel labelBeschrijvingT;
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
    private JTextField textResultG;
    private JTextField textResultT;
    private JTextField textResultMC;
    private JTextField textZoekT;
    private JButton buttonZoekT;
    private JButton buttonZoekAllesT;
    private JList listToernooiId;
    private JList listToernooiDatum;
    private JList listToernooiBeginTijd;
    private JList listToernooiEindTijd;
    private JList listToernooiBeschrijving;
    private JList listToernooiWinnaar;
    private JList listToernooiMaxInschrijvingen;
    private JList listToernooiInleg;
    private JList listToernooiInsDatum;
    private JTextField textZoekG;
    private JButton buttonZoekG;
    private JList listGastId;
    private JList listGastNaam;
    private JList listGastAdres;
    private JList listGastPostcode;
    private JList listGastWoonplaats;
    private JList listGastTelnr;
    private JList listGastEmail;
    private JList listGastGebdatum;
    private JList listGastGeslacht;
    private JList listGastRating;
    private JList listGastBekspeler;
    private JButton zoekButton;
    private JTextField textFieldZoekOpDatumMc;
    private JList listMasterclassId;
    private JList listMastercalssDatum;
    private JList listMasterclassBeginTijd;
    private JList listMasterclassEindTijd;
    private JList listMasterclassPrijs;
    private JList listMasterclassMinRating;
    private JList listMasterclassIdGast;
    private JButton buttonZoekMc;
    private JButton buttonLaatAllesZienMc;
    private JTextField txtGastID;
    private JTextField txtMCID;
    private JButton masterclassRegist;
    private JCheckBox betaaldCheckBox;
    private JTextField txtToernooiID;
    private JTextField txtIdGast;
    private JCheckBox betaaldToernooiCheckBox;
    private JButton registrerenToernooiButton;
    private JTextField statusTextField;
    private JTextField statusMasterclassTextField;

    private PreparedStatement ps;
    private String insertGast = "INSERT INTO gast (naam, adres, postcode, woonplaats, telnr, email, gebdatum, geslacht, bekspeler) VALUES(?,?,?,?,?,?,?,?,?)";
    private String insertToernooi = "INSERT INTO toernooi (datum, begintijd, eindtijd, beschrijving, maxInschrijvingen, inleg, insdatum) VALUES(?,?,?,?,?,?,?)";
    private String insertMC = "INSERT INTO masterclass (datum, begin, eind, prijs, minRating) VALUES (?,?,?,?,?)";
    private String insertMcInschrijving = "INSERT INTO InschrijvingMasterclass (idGast, idmc, betaald) VALUES (?,?,?)";
    private String insertToernooiInschrijving = "INSERT INTO inschrijvingtoernooi (idGast, idToernooi, betaald) VALUES (?,?,?)";

    public GUI_Form() {
        registreerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naam = textNaam.getText();
                String adres = textAdres.getText();
                String postcode = textPostcode.getText();
                String woonplaats = textWoonplaats.getText();
                String telnr = textTelnr.getText();
                String email = textEmail.getText();
                Date gebdatum = Date.valueOf(textGebdatum.getText());
                LocalDate today = LocalDate.now();
                LocalDate birthday = gebdatum.toLocalDate();
                Period p = Period.between(birthday, today);

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

                if (p.getYears() < 18){
                    textResultG.setText("Deze gast is onder de 18 jaar en mag niet meespelen.");
                }
                else {

                    try {
                        ps = ConnectionManager.getConnection().prepareStatement(insertGast);
                        ps.setString(1, naam);
                        ps.setString(2, adres);
                        ps.setString(3, postcode);
                        ps.setString(4, woonplaats);
                        ps.setString(5, telnr);
                        ps.setString(6, email);
                        ps.setDate(7, gebdatum);
                        ps.setString(8, geslacht);
                        ps.setString(9, bekSpeler);

                        ps.executeUpdate();

                        textNaam.setText("");
                        textAdres.setText("");
                        textPostcode.setText("");
                        textWoonplaats.setText("");
                        textTelnr.setText("");
                        textEmail.setText("");
                        textGebdatum.setText("");
                        if (manRadioButton.isSelected()) {
                            manRadioButton.setSelected(false);
                        } else if (vrouwRadioButton.isSelected()) {
                            vrouwRadioButton.setSelected(false);
                        } else if (andersRadioButton.isSelected()) {
                            andersRadioButton.setSelected(false);
                        }
                        if (jaRadioButton.isSelected()) {
                            jaRadioButton.setSelected(false);
                        } else if (neeRadioButton.isSelected()) {
                            neeRadioButton.setSelected(false);
                        }

                        textResultG.setText(naam + " is toegevoegd aan de database.");

                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        voegToeButtonT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date datum = Date.valueOf(textDatumT.getText());
                Time beginTijd = Time.valueOf(textBtijdT.getText());
                Time eindTijd = Time.valueOf(textEtijdT.getText());
                String beschrijving = textBeschrijvingT.getText();
                int maxInschrijvingen = Integer.parseInt(textMaxinschrijvingenT.getText());
                double inleggeld = Double.parseDouble(textInleggeldT.getText());
                Date inschrijfdatum = Date.valueOf(textInschrijfdatumT.getText());

                try{
                    ps = ConnectionManager.getConnection().prepareStatement(insertToernooi);
                    ps.setDate(1, datum);
                    ps.setTime(2, beginTijd);
                    ps.setTime(3, eindTijd);
                    ps.setString(4, beschrijving);
                    ps.setInt(5, maxInschrijvingen);
                    ps.setDouble(6, inleggeld);
                    ps.setDate(7, inschrijfdatum);

                    ps.executeUpdate();

                    textDatumT.setText("");
                    textBtijdT.setText("");
                    textEtijdT.setText("");
                    textBeschrijvingT.setText("");
                    textMaxinschrijvingenT.setText("");
                    textInleggeldT.setText("");
                    textInschrijfdatumT.setText("");

                    textResultT.setText("Het toernooi op " + datum + " is toegevoegd aan de database.");

                } catch(SQLException exception){
                    exception.printStackTrace();
                }

            }
        });

        voegToeButtonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date datum = Date.valueOf(textDatumMC.getText());
                Time beginTijd = Time.valueOf(textBeginMC.getText());
                Time eindTijd = Time.valueOf(textEindMC.getText());
                double prijs = Double.parseDouble(textPrijsMC.getText());
                int minRating = Integer.parseInt(textMinratingMC.getText());

                try{
                    ps = ConnectionManager.getConnection().prepareStatement(insertMC);
                    ps.setDate(1, datum);
                    ps.setTime(2, beginTijd);
                    ps.setTime(3, eindTijd);
                    ps.setDouble(4, prijs);
                    ps.setInt(5, minRating);

                    ps.executeUpdate();

                    textDatumMC.setText("");
                    textBeginMC.setText("");
                    textEindMC.setText("");
                    textPrijsMC.setText("");
                    textMinratingMC.setText("");

                    textResultMC.setText("De masterclass op " + datum + " is toegevoegd aan de database.");

                } catch(SQLException exception){
                    exception.printStackTrace();
                }

            }
        });
        buttonZoekT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonZoekAllesT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                DefaultListModel modelId = new DefaultListModel();
                DefaultListModel modelD = new DefaultListModel();
                DefaultListModel modelBT = new DefaultListModel();
                DefaultListModel modelET = new DefaultListModel();
                DefaultListModel modelB = new DefaultListModel();
                DefaultListModel modelW = new DefaultListModel();
                DefaultListModel modelMI = new DefaultListModel();
                DefaultListModel modelIn = new DefaultListModel();
                DefaultListModel modelID = new DefaultListModel();
                listToernooiId.setModel(modelId);
                listToernooiDatum.setModel(modelD);
                listToernooiBeginTijd.setModel(modelBT);
                listToernooiEindTijd.setModel(modelET);
                listToernooiBeschrijving.setModel(modelB);
                listToernooiWinnaar.setModel(modelW);
                listToernooiMaxInschrijvingen.setModel(modelMI);
                listToernooiInleg.setModel(modelIn);
                listToernooiInsDatum.setModel(modelID);
                try {
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM toernooi");

                    while(rs.next()){
                        String Toernooi = rs.getString("idToernooi");
                        String datum = rs.getString("datum");
                        String begintijd = rs.getString("begintijd");
                        String eindtijd = rs.getString("eindtijd");
                        String beschrijving = rs.getString("beschrijving");
                        String winnaar = rs.getString("winnaar");
                        String maxinschrijvingen = rs.getString("maxInschrijvingen");
                        String inleg = rs.getString("inleg");
                        String insdatum = rs.getString("insDatum");

                        modelId.addElement(Toernooi);
                        modelD.addElement(datum);
                        modelBT.addElement(begintijd);
                        modelET.addElement(eindtijd);
                        modelB.addElement(beschrijving);
                        modelW.addElement(winnaar);
                        modelMI.addElement(maxinschrijvingen);
                        modelIn.addElement(inleg);
                        modelID.addElement(insdatum);
                    }



                } catch(SQLException exception){
                    exception.printStackTrace();
                }

            }
        });

        registrerenToernooiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int GastID = Integer.parseInt(txtIdGast.getText());
                    int toernooiID = Integer.parseInt(txtToernooiID.getText());
                    String betaald;
                    if (betaaldToernooiCheckBox.isSelected()){
                        betaald = "J";
                    } else {
                        betaald = "N";
                    }
                    ps = ConnectionManager.getConnection().prepareStatement(insertToernooiInschrijving);
                    ps.setInt(1,GastID);
                    ps.setInt(2, toernooiID);
                    ps.setString(3,betaald);
                    ps.executeUpdate();

                    txtIdGast.setText("");
                    txtToernooiID.setText("");
                    betaaldToernooiCheckBox.setSelected(false);
                    statusTextField.setText("Inschrijving gelukt!");

                } catch (SQLException exc) {
                    statusTextField.setText("Inschrijving mislukt, controleer of alles goed is ingevuld en/of er geen dubbele gegevens ingevoerd zijn.");
                    exc.printStackTrace();

                }
            }
        });
        masterclassRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int gastID = Integer.parseInt(txtGastID.getText());
                    int MCID = Integer.parseInt(txtMCID.getText());
                    String betaald;
                    if (betaaldCheckBox.isSelected()){
                        betaald = "J";
                    } else{
                        betaald = "N";
                    }
                    ps = ConnectionManager.getConnection().prepareStatement(insertMcInschrijving);
                    ps.setInt(1, gastID);
                    ps.setInt(2, MCID);
                    ps.setString(3, betaald);
                    ps.executeUpdate();

                    txtGastID.setText("");
                    txtMCID.setText("");
                    betaaldCheckBox.setSelected(false);
                    statusMasterclassTextField.setText("Inschrijving gelukt!");
                }
                catch (SQLException ex){
                    statusMasterclassTextField.setText("Inschrijving mislukt, controleer of alles goed is ingevuld en/of er geen dubbele gegevens ingevoerd zijn..");
                    ex.printStackTrace();
                }
            }
        });

        buttonZoekG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String zoekNaam = textZoekG.getText();
                    ps = ConnectionManager.getConnection().prepareStatement("SELECT * FROM gast WHERE naam like '%" + zoekNaam + "%';");
                    ResultSet rs = ps.executeQuery();

                    DefaultListModel modelId = new DefaultListModel();
                    DefaultListModel modelNaam = new DefaultListModel();
                    DefaultListModel modelAdres = new DefaultListModel();
                    DefaultListModel modelPostode = new DefaultListModel();
                    DefaultListModel modelWoonplaats = new DefaultListModel();
                    DefaultListModel modelTelnr = new DefaultListModel();
                    DefaultListModel modelEmail = new DefaultListModel();
                    DefaultListModel modelGebdatum = new DefaultListModel();
                    DefaultListModel modelGeslacht = new DefaultListModel();
                    DefaultListModel modelRating = new DefaultListModel();
                    DefaultListModel modelBekspeler = new DefaultListModel();

                    listGastId.setModel(modelId);
                    listGastNaam.setModel(modelNaam);
                    listGastAdres.setModel(modelAdres);
                    listGastPostcode.setModel(modelPostode);
                    listGastWoonplaats.setModel(modelWoonplaats);
                    listGastTelnr.setModel(modelTelnr);
                    listGastEmail.setModel(modelEmail);
                    listGastGebdatum.setModel(modelGebdatum);
                    listGastGeslacht.setModel(modelGeslacht);
                    listGastRating.setModel(modelRating);
                    listGastBekspeler.setModel(modelBekspeler);


                    while(rs.next()){
                        String id = rs.getString("idgast");
                        String naam = rs.getString("naam");
                        String adres = rs.getString("adres");
                        String postcode = rs.getString("postcode");
                        String woonplaats = rs.getString("woonplaats");
                        String telnr = rs.getString("telnr");
                        String email = rs.getString("email");
                        String gebdatum = rs.getString("gebdatum");
                        String geslacht = rs.getString("geslacht");
                        String rating = rs.getString("rating");
                        String bekspeler = rs.getString("bekspeler");

                        modelId.addElement(id);
                        modelNaam.addElement(naam);
                        modelAdres.addElement(adres);
                        modelPostode.addElement(postcode);
                        modelWoonplaats.addElement(woonplaats);
                        modelTelnr.addElement(telnr);
                        modelEmail.addElement(email);
                        modelGebdatum.addElement(gebdatum);
                        modelGeslacht.addElement(geslacht);
                        modelRating.addElement(rating);
                        modelBekspeler.addElement(bekspeler);

                    }

                } catch (SQLException exception){
                    exception.printStackTrace();
                }
            }
        });

        buttonLaatAllesZienMc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel modelIdMc = new DefaultListModel();
                DefaultListModel modelDMc = new DefaultListModel();
                DefaultListModel modelBTMc = new DefaultListModel();
                DefaultListModel modelETMc = new DefaultListModel();
                DefaultListModel modelPMc = new DefaultListModel();
                DefaultListModel modelMrMc = new DefaultListModel();
                DefaultListModel modelIdGMc = new DefaultListModel();
                listMasterclassId.setModel(modelIdMc);
                listMastercalssDatum.setModel(modelDMc);
                listMasterclassBeginTijd.setModel(modelBTMc);
                listMasterclassEindTijd.setModel(modelETMc);
                listMasterclassPrijs.setModel(modelPMc);
                listMasterclassMinRating.setModel(modelMrMc);
                listMasterclassIdGast.setModel(modelIdGMc);

                try {
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM Masterclass");

                    while(rs.next()){
                        String Masterclass = rs.getString("idMasterclass");
                        String datum = rs.getString("datum");
                        modelIdMc.addElement(Masterclass);
                        modelDMc.addElement(datum);
                    }



                } catch(SQLException exception){
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Full House");
        frame.setContentPane(new GUI_Form().panel);
        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
