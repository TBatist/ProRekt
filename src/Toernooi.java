import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Toernooi {
    private static ArrayList<Integer> gastenLijst = new ArrayList<>();
    private static ArrayList<Integer[]> tafelLijst = new ArrayList<Integer[]>();
    private static PreparedStatement ps;

    public static void tafelsMaken(int idToernooi) {
        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select idGast as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = " + idToernooi);
            while (rs.next()) {
                gastenLijst.add(rs.getInt(1));
            }

            Collections.shuffle(gastenLijst);
            Collections.shuffle(gastenLijst);
            Collections.shuffle(gastenLijst);

            int tafelNummer = -1;
            int stoelNum = 0;

            for (int i = 0; i < gastenLijst.size(); i++) {
                if (i % 5 == 0) {
                    tafelLijst.add(new Integer[5]);
                    tafelNummer++;
                }
                tafelLijst.get(tafelNummer)[stoelNum] = gastenLijst.get(i);
                stoelNum++;
                if(stoelNum == 5){stoelNum = 0;}
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void toevoegenDatabase(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("select * from tafelgasten");

            int gastNummer = 0;
            int tafelNummer = 0;

            for (int i = 0; i < gastenLijst.size(); i++) {
            while(gastNummer < gastenLijst.size()){
                ps = ConnectionManager.getConnection().prepareStatement("INSERT tafelgasten SET idtoernooi = ?, idgast = ?, idtafel = ?");
                ps.setInt(1, idToernooi);
                ps.setInt(2, gastenLijst.get(gastNummer));
                if(gastNummer % 4 == 0 && gastNummer != 0)
                    tafelNummer++;
                ps.setInt(3, tafelNummer);
                gastNummer++;
                ps.executeUpdate();
            }


            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void ratingSysteem(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            int rating;

        } catch(SQLException exception){exception.printStackTrace();}
    }

    public static void prijzenGeldVerdeling(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select (count(idGast) * inleg), winnaar, tweedePlek from inschrijvingtoernooi I join toernooi T on I.idToernooi = T.idToernooi where I.idToernooi = " + idToernooi);
            int totaleInleg = rs.getInt(1);
            int winnaarID = rs.getInt(2);
            int tweedePlekID = rs.getInt(3);
            Double eerstePrijs = (totaleInleg * 0.4);
            Double tweedePrijs = (totaleInleg * 0.25);

            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET prijzenGeld = '"+ eerstePrijs +"' WHERE idgast = " + winnaarID);
            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET prijzenGeld = '"+ tweedePrijs +"' WHERE idgast = " + tweedePlekID);
            ps.executeUpdate();

        } catch(SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {

        }
    }
