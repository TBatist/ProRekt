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
        gastenLijst.add("1");
        gastenLijst.add("2");
        gastenLijst.add("3");
        gastenLijst.add("4");
        gastenLijst.add("5");
        gastenLijst.add("6");
        gastenLijst.add("7");
        gastenLijst.add("8");
        gastenLijst.add("9");
        gastenLijst.add("10");
        //Collections.shuffle(gastenLijst); // Deze gastenLijst is even een test voor het shuffelen en plaatsen van gasten!

        Integer[] tafel1 = new Integer[5];
        Integer[] tafel2 = new Integer[5];
        Integer[] tafel3 = new Integer[5];

        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet  rs = st.executeQuery("select count(idGast) as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
            inschrijvingen = rs.getInt("inschrijvingen") ;

            for (int i = 1; i <= inschrijvingen; i++) {

            }

        } catch (SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {
    }
}
