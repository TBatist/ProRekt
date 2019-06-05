package Registreer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class registreerGast {

    public static void registreerG(String naam, String adres, String postcode, String woonplaats, String telnr, String email, Date gebdatum, String geslacht, String bekSpeler, PreparedStatement ps){
        try {

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
