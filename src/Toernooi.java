import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Toernooi {
    ArrayList<String> gastenLijst = new ArrayList<>();

    public void gastenlijstVullen(){
    }

    public void tafelsMaken(){
        int inschrijvingen;
        //Collections.shuffle(gastenLijst);

        String[] tafel1 = new String[5]; // moet tweedimensionale array worden
        String[] tafel2 = new String[5];
        String[] tafel3 = new String[5];

        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet  rs = st.executeQuery("select count(idGast) as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
            inschrijvingen = rs.getInt("inschrijvingen");
            rs = st.executeQuery("select idGast as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
            gastenLijst.add(rs.getString(1));
            gastenLijst.add(rs.getString(2));
            gastenLijst.add(rs.getString(3));
            gastenLijst.add(rs.getString(4));
            gastenLijst.add(rs.getString(5));
            gastenLijst.add(rs.getString(6));
            gastenLijst.add(rs.getString(7));

            int tafel = 0;

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {

                }
                tafel1[i] = gastenLijst.get(i);
            }

        } catch (SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {
    }
}
