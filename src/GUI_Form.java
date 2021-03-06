import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class GUI_Form {
    private JTabbedPane tabbedPane;
    private JPanel panel;
    public JTextField textNaam;
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
    private JButton wijzigGastButton;
    private JButton verwijderButton;
    private JButton wijzigButton;
    private JTextField BGastIDtxt;
    private JScrollPane scrollPaneGast;
    private JScrollPane scrollPaneToernooi;
    private JButton indelingMakenButton;
    private JRadioButton deelnemersToernooiRadioButton;
    private JRadioButton deelnemersMasterclassRadioButton;
    private JList listGastID;
    private JList listEventID;
    private JList listBetaald;
    private JTextField IDtextfield;
    private JLabel labelGastID;
    private JLabel labelEventID;
    private JLabel labelBetaald;
    private JButton zoekButtonEvent;
    private JButton wijzigButtonMC;
    private JList listMasterclassNaamGast;
    private JButton beëindigToernooiButton;
    private JList listPrijsgeld;
    private JButton zetWinnaarButton;
    private JTextField locatieTxt;

    private PreparedStatement ps;
    Masterclass mc = new Masterclass();
    private String insertGast = "INSERT INTO gast (naam, adres, postcode, woonplaats, telnr, email, gebdatum, geslacht, bekspeler) VALUES(?,?,?,?,?,?,?,?,?)";
    private String insertToernooi = "INSERT INTO toernooi (datum, begintijd, eindtijd, beschrijving, maxInschrijvingen, inleg, insdatum, locatie) VALUES(?,?,?,?,?,?,?,?)";
    private String insertMC = "INSERT INTO masterclass (datum, begin, eind, prijs, minRating, geverMasterclass) VALUES (?,?,?,?,?,?)";
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
                else{
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
                    } catch (SQLException exception){
                        textResultG.setText("Registratie mislukt, controleer of alles goed is ingevuld en/of er geen dubbele gegevens ingevoerd zijn.");
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
                String locatie = locatieTxt.getText();

                try{
                    ps = ConnectionManager.getConnection().prepareStatement(insertToernooi);
                    ps.setDate(1, datum);
                    ps.setTime(2, beginTijd);
                    ps.setTime(3, eindTijd);
                    ps.setString(4, beschrijving);
                    ps.setInt(5, maxInschrijvingen);
                    ps.setDouble(6, inleggeld);
                    ps.setDate(7, inschrijfdatum);
                    ps.setString(8, locatie);

                    ps.executeUpdate();

                    textDatumT.setText("");
                    textBtijdT.setText("");
                    textEtijdT.setText("");
                    textBeschrijvingT.setText("");
                    textMaxinschrijvingenT.setText("");
                    textInleggeldT.setText("");
                    textInschrijfdatumT.setText("");
                    locatieTxt.setText("");

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
                int BekendeSpelerID = Integer.parseInt(BGastIDtxt.getText());

                if(mc.GastAllowed(BekendeSpelerID) == true){
                    mc.registMC(datum, beginTijd, eindTijd, prijs, minRating, BekendeSpelerID);
                }

                    textDatumMC.setText("");
                    textBeginMC.setText("");
                    textEindMC.setText("");
                    textPrijsMC.setText("");
                    textMinratingMC.setText("");
                    textResultMC.setText("De masterclass op " + datum + " is toegevoegd aan de database.");

            }
        });

        buttonZoekT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = Date.valueOf(textZoekT.getText());
                try{
                    ps = ConnectionManager.getConnection().prepareStatement("SELECT * FROM toernooi WHERE datum = " + date);
                } catch (SQLException exception){
                    exception.printStackTrace();
                }
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


        zoekButtonEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel modelGastID = new DefaultListModel();
                DefaultListModel modelEventID = new DefaultListModel();
                DefaultListModel modelBetaald = new DefaultListModel();

                listGastID.setModel(modelGastID);
                listEventID.setModel(modelEventID);
                listBetaald.setModel(modelBetaald);

                int eventID = Integer.parseInt(IDtextfield.getText());

                if (deelnemersToernooiRadioButton.isSelected()){
                    try {
                        Connection con = ConnectionManager.getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM inschrijvingtoernooi WHERE idToernooi = " + eventID);

                        while (rs.next()){
                            int ID = rs.getInt("idGast");
                            int idToernooi = rs.getInt("idToernooi");
                            String betaald = rs.getString("betaald");

                            modelGastID.addElement(ID);
                            modelEventID.addElement(idToernooi);
                            modelBetaald.addElement(betaald);
                        }

                    } catch (SQLException e1) {
                          e1.printStackTrace();
                    }


                    }
                if (deelnemersMasterclassRadioButton.isSelected()) {
                    try {
                        Connection con = ConnectionManager.getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM InschrijvingMasterclass WHERE idmc = " + eventID);

                        while (rs.next()) {
                            int ID = rs.getInt("idGast");
                            int idmc = rs.getInt("idmc");
                            String betaald = rs.getString("betaald");

                            modelGastID.addElement(ID);
                            modelEventID.addElement(idmc);
                            modelBetaald.addElement(betaald);
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        registrerenToernooiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT maxInschrijvingen FROM toernooi");

                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery("SELECT COUNT(idGast) as totaal FROM inschrijvingtoernooi WHERE idToernooi = " + Integer.parseInt(txtToernooiID.getText()));

                    int GastID = Integer.parseInt(txtIdGast.getText());
                    Statement st3 = con.createStatement();
                    ResultSet rs3 = st3.executeQuery("SELECT rating FROM gast WHERE idgast = " + GastID);

                    int tempRating = 0;
                    if (rs3.next()) {
                        tempRating = Integer.parseInt(rs3.getString("rating"));
                    }

                    int toernooiID = Integer.parseInt(txtToernooiID.getText());
                    String betaald;
                    if (betaaldToernooiCheckBox.isSelected()) {
                        betaald = "J";
                    } else {
                        betaald = "N";
                    }
                    while (rs2.next() && rs.next()) {
                        if (Integer.parseInt(rs2.getString("totaal")) >= Integer.parseInt(rs.getString("maxInschrijvingen"))) {
                            textResultT.setText("De limiet van gasten is voor dit toernooi bereikt");
                        } else {
                            ps = ConnectionManager.getConnection().prepareStatement(insertToernooiInschrijving);
                            ps.setInt(1, GastID);
                            ps.setInt(2, toernooiID);
                            ps.setString(3, betaald);
                            ps.executeUpdate();

                            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET rating = ? WHERE idgast = " + GastID);
                            ps.setInt(1, tempRating + 10);
                            ps.executeUpdate();
                        }
                    }
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
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT rating FROM gast WHERE idgast = " + Integer.parseInt(txtMCID.getText()));

                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery("SELECT minRating FROM masterclass WHERE idMC = " + Integer.parseInt(txtMCID.getText()));
                    int gastID = Integer.parseInt(txtGastID.getText());
                    int MCID = Integer.parseInt(txtMCID.getText());
                    String betaald;
                    if (betaaldCheckBox.isSelected()){
                        betaald = "J";
                    } else{
                        betaald = "N";
                    }
                    while(rs2.next() && rs.next()) {
                        if (rs.getInt("rating") < rs2.getInt("minRating")) {
                            statusMasterclassTextField.setText("De gast heeft niet de vereiste minimale rating behaald!");
                        } else {
                            ps = ConnectionManager.getConnection().prepareStatement(insertMcInschrijving);
                            ps.setInt(1, gastID);
                            ps.setInt(2, MCID);
                            ps.setString(3, betaald);
                            ps.executeUpdate();

                            statusMasterclassTextField.setText("Inschrijving gelukt!");
                        }
                    }
                    txtGastID.setText("");
                    txtMCID.setText("");
                    betaaldCheckBox.setSelected(false);
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
                    DefaultListModel modelPrijzengeld = new DefaultListModel();

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
                    listPrijsgeld.setModel(modelPrijzengeld);


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
                        double prijzengeld = rs.getDouble("prijzenGeld");

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
                        modelPrijzengeld.addElement(prijzengeld);

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
                    ResultSet rs = st.executeQuery("SELECT * FROM masterclass");

                    while(rs.next()){
                        String Masterclass = rs.getString("idmc");
                        String datum = rs.getString("datum");
                        String begin = rs.getString("begin");
                        String eind = rs.getString("eind");
                        String prijs = rs.getString("prijs");
                        String minrating = rs.getString("minRating");
                        String gever = rs.getString("geverMasterclass");
                        modelIdMc.addElement(Masterclass);
                        modelDMc.addElement(datum);
                        modelBTMc.addElement(begin);
                        modelETMc.addElement(eind);
                        modelPMc.addElement(prijs);
                        modelMrMc.addElement(minrating);
                        modelIdGMc.addElement(gever);
                    }



                } catch(SQLException exception){
                    exception.printStackTrace();
                }
            }
        });

        wijzigGastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idIndex = Integer.parseInt((String) listGastId.getSelectedValue());
                JPanel optionPanel = new JPanel();
                optionPanel.setLayout(new GridLayout(13,2));
                try{
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM gast WHERE idgast = " + idIndex + ";");

                    if(rs.next()) {

                        JLabel idTxt = new JLabel ("ID gast");
                        JTextField id = new JTextField(rs.getString("idgast"));
                        int tempId = Integer.parseInt(id.getText());
                        JLabel naamTxt = new JLabel("Naam");
                        JTextField naam = new JTextField(rs.getString("naam"));
                        JLabel adresTxt = new JLabel("Adres");
                        JTextField adres = new JTextField(rs.getString("adres"));
                        JLabel postcodeTxt = new JLabel("Postcode");
                        JTextField postcode = new JTextField(rs.getString("postcode"));
                        JLabel woonplaatsTxt= new JLabel("Woonplaats");
                        JTextField woonplaats = new JTextField(rs.getString("woonplaats"));
                        JLabel telnummerTxt = new JLabel("Telefoonnummer");
                        JTextField telnr = new JTextField(rs.getString("telnr"));
                        JLabel emailTxt = new JLabel("E-mail adres");
                        JTextField email = new JTextField(rs.getString("email"));
                        JLabel gebdatumTxt = new JLabel("Geboortedatum");
                        JTextField gebdatum = new JTextField(rs.getString("gebdatum"));
                        JLabel geslachtTxt = new JLabel("Geslacht");
                        JTextField geslacht = new JTextField(rs.getString("geslacht"));
                        JLabel ratingTxt = new JLabel("Rating");
                        JTextField rating = new JTextField(rs.getString("rating"));
                        JLabel prijzengeldTxt = new JLabel("Prijzengeld");
                        JTextField prijzengeld = new JTextField(rs.getString("prijzenGeld"));
                        JLabel bekspelerTxt = new JLabel("Bekende speler");
                        JTextField bekspeler = new JTextField(rs.getString("bekspeler"));

                        optionPanel.add(idTxt);
                        optionPanel.add(id);
                        optionPanel.add(naamTxt);
                        optionPanel.add(naam);
                        optionPanel.add(adresTxt);
                        optionPanel.add(adres);
                        optionPanel.add(postcodeTxt);
                        optionPanel.add(postcode);
                        optionPanel.add(woonplaatsTxt);
                        optionPanel.add(woonplaats);
                        optionPanel.add(telnummerTxt);
                        optionPanel.add(telnr);
                        optionPanel.add(emailTxt);
                        optionPanel.add(email);
                        optionPanel.add(gebdatumTxt);
                        optionPanel.add(gebdatum);
                        optionPanel.add(geslachtTxt);
                        optionPanel.add(geslacht);
                        optionPanel.add(ratingTxt);
                        optionPanel.add(rating);
                        optionPanel.add(prijzengeldTxt);
                        optionPanel.add(prijzengeld);
                        optionPanel.add(bekspelerTxt);
                        optionPanel.add(bekspeler);

                        int result = JOptionPane.showConfirmDialog(null, optionPanel, "Wijzigen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET idgast = ?, naam = ?, adres = ?, postcode = ?, woonplaats = ?, telnr = ?, email = ?, gebdatum = ?, geslacht = ?, rating = ?, prijzenGeld = ?, bekspeler = ? WHERE idgast = " + tempId + ";");
                                ps.setInt(1, Integer.parseInt(id.getText()));
                                ps.setString(2, naam.getText());
                                ps.setString(3,adres.getText());
                                ps.setString(4, postcode.getText());
                                ps.setString(5, woonplaats.getText());
                                ps.setString(6, telnr.getText());
                                ps.setString(7, email.getText());
                                ps.setDate(8, Date.valueOf(gebdatum.getText()));
                                ps.setString(9,geslacht.getText());
                                ps.setInt(10, Integer.parseInt(rating.getText()));
                                ps.setDouble(11, Double.parseDouble(prijzengeld.getText()));
                                ps.setString(12, bekspeler.getText());
                                ps.executeUpdate();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException exception){
                    exception.printStackTrace();
                }
            }
        });

        verwijderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idIndex = Integer.parseInt((String) listGastId.getSelectedValue());
                try{
                    ps = ConnectionManager.getConnection().prepareStatement("DELETE FROM gast WHERE idgast = " + idIndex + ";");
                    ps.executeUpdate();
                } catch(SQLException exception){
                    exception.printStackTrace();
                }
            }
        });

        wijzigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idIndex = Integer.parseInt((String) listToernooiId.getSelectedValue());
                JPanel optionPanel = new JPanel();
                optionPanel.setLayout(new GridLayout(9,1));

                try {
                    Connection con = ConnectionManager.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM toernooi WHERE idToernooi = " + idIndex + ";");

                    if (rs.next()) {
                        JTextField id = new JTextField(rs.getString("idToernooi"));
                        int tempId = Integer.parseInt(id.getText());
                        JTextField datum = new JTextField(rs.getString("datum"));
                        JTextField begin = new JTextField(rs.getString("begintijd"));
                        JTextField eind = new JTextField(rs.getString("eindtijd"));
                        JTextField beschrijving = new JTextField(rs.getString("beschrijving"));
                        JTextField winnaar = new JTextField(rs.getString("winnaar"));
                        JTextField maxInschr = new JTextField(rs.getString("maxInschrijvingen"));
                        JTextField inleg = new JTextField(rs.getString("inleg"));
                        JTextField insdatum = new JTextField(rs.getString("insDatum"));

                        optionPanel.add(id);
                        optionPanel.add(datum);
                        optionPanel.add(begin);
                        optionPanel.add(eind);
                        optionPanel.add(beschrijving);
                        optionPanel.add(winnaar);
                        optionPanel.add(maxInschr);
                        optionPanel.add(inleg);
                        optionPanel.add(insdatum);

                        int result = JOptionPane.showConfirmDialog(null, optionPanel, "Wijzigen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if(result == JOptionPane.OK_OPTION) {
                            try{
                                ps = ConnectionManager.getConnection().prepareStatement("UPDATE toernooi SET idToernooi = ?, datum = ?, begintijd = ?, eindtijd = ?, beschrijving = ?, winnaar = ?, maxInschrijvingen = ?, inleg = ?, insdatum = ? WHERE idToernooi = " + tempId);
                                ps.setInt(1, tempId);
                                ps.setDate(2, Date.valueOf(datum.getText()));
                                ps.setTime(3, Time.valueOf(begin.getText()));
                                ps.setTime(4, Time.valueOf(eind.getText()));
                                ps.setString(5, beschrijving.getText());
                                ps.setString(6, winnaar.getText());
                                ps.setInt(7, Integer.parseInt(maxInschr.getText()));
                                ps.setInt(8, Integer.parseInt(inleg.getText()));
                                ps.setDate(9, Date.valueOf(insdatum.getText()));
                                ps.executeUpdate();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException exception){
                    exception.printStackTrace();
                }

            }
        });
/*        indelingMakenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToernooi = Integer.parseInt((String) listToernooiId.getSelectedValue());
                Toernooi.tafelsMaken(idToernooi);
                Toernooi.toevoegenDatabase(idToernooi);
            }
        });*/
        beëindigToernooiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToernooi = Integer.parseInt((String) listToernooiId.getSelectedValue());
                Toernooi.prijzenGeldVerdeling(idToernooi);
                Toernooi.ratingSysteem(idToernooi);
            }
        });
        /*volgendeRondeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idToernooi = Integer.parseInt((String) listToernooiId.getSelectedValue());
                Toernooi.prijzenGeldVerdeling(idToernooi);
                Toernooi.rondeIndeling(idToernooi);
                Toernooi.rondeIndelingToevoegen(idToernooi);
                Toernooi.ratingSysteem(idToernooi);
            }
        });*/
        zetWinnaarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idIndex = Integer.parseInt((String) listToernooiId.getSelectedValue());
                JPanel optionPanel = new JPanel();
                optionPanel.setLayout(new GridLayout(3,2));

                JTextField ronde = new JTextField();
                JTextField tafel = new JTextField();
                JTextField winnaar = new JTextField();

                optionPanel.add(new JLabel("Ronde:"));
                optionPanel.add(ronde);
                optionPanel.add(new JLabel("Tafel:"));
                optionPanel.add(tafel);
                optionPanel.add(new JLabel("Winnaar:"));
                optionPanel.add(winnaar);

                int result = JOptionPane.showConfirmDialog(null, optionPanel, "Wijzigen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if(result == JOptionPane.OK_OPTION) {
                    try {
                        ps = ConnectionManager.getConnection().prepareStatement("INSERT INTO Tafel VALUES(?,?,?,?)");
                        ps.setInt(1,Integer.parseInt(tafel.getText()));
                        ps.setInt(2, idIndex);
                        ps.setInt(3,Integer.parseInt(ronde.getText()));
                        ps.setInt(4,Integer.parseInt(winnaar.getText()));
                        ps.executeUpdate();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Full House");
        frame.setContentPane(new GUI_Form().panel);
        frame.setSize(1600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
