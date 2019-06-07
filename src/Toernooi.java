import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Toernooi {
    private static ArrayList<Integer> gastenLijst = new ArrayList<>();
    private static ArrayList<Integer[]> tafelLijst = new ArrayList<>();
    private static ArrayList<Integer> tafelWinnaars = new ArrayList<>();
    private static ArrayList<Integer[]> rondeTafels = new ArrayList<>();
    private static PreparedStatement ps;
    private static int rondeNummer = 1;

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

    public static void rondeIndeling(int idToernooi){
        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select winnaar from Tafel where idtoernooi = " + idToernooi);
            while (rs.next()) {
                tafelWinnaars.add(rs.getInt(1));
            }

            Collections.shuffle(tafelWinnaars);
            Collections.shuffle(tafelWinnaars);
            Collections.shuffle(tafelWinnaars);


            int tafelNummer = -1;
            int stoelNum = 0;

            for (int i = 0; i < tafelWinnaars.size(); i++) {
                if (i % 5 == 0) {
                    rondeTafels.add(new Integer[5]);
                    tafelNummer++;
                }
                rondeTafels.get(tafelNummer)[stoelNum] = tafelWinnaars.get(i);
                stoelNum++;
                if(stoelNum == 5){stoelNum = 0;}
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void rondeIndelingToevoegen(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("select * from tafelgasten");

            int gastNummer = 0;
            int tafelNummer = 0;

            for (int i = 0; i < tafelWinnaars.size(); i++) {
                while(gastNummer < tafelWinnaars.size()){
                    ps = ConnectionManager.getConnection().prepareStatement("INSERT tafelgasten SET idtoernooi = ?, idgast = ?, idtafel = ?, ronde = ?"); //nieuwe query
                    ps.setInt(1, idToernooi);
                    ps.setInt(2, tafelWinnaars.get(gastNummer));
                    if(gastNummer % 4 == 0 && gastNummer != 0)
                        tafelNummer++;
                    ps.setInt(3, tafelNummer);
                    ps.setInt(4, rondeNummer);
                    gastNummer++;
                    ps.executeUpdate();
                }
                ps = ConnectionManager.getConnection().prepareStatement("UPDATE Tafel SET idronde = ? WHERE idtoernooi = " +  idToernooi);
                ps.setInt(1, rondeNummer);
                ps.executeUpdate();
                rondeNummer++;

            }
        } catch (SQLException exception){
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
                ps = ConnectionManager.getConnection().prepareStatement("INSERT tafelgasten SET idtoernooi = ?, idgast = ?, idtafel = ?, ronde = ?");
                ps.setInt(1, idToernooi);
                ps.setInt(2, gastenLijst.get(gastNummer));
                if(gastNummer % 4 == 0 && gastNummer != 0)
                    tafelNummer++;
                ps.setInt(3, tafelNummer);
                ps.setInt(4, rondeNummer);
                gastNummer++;
                ps.executeUpdate();
                ps = ConnectionManager.getConnection().prepareStatement("INSERT Tafel SET idronde = ? WHERE idtoernooi = " +  idToernooi);
                ps.setInt(1, rondeNummer);
                ps.executeUpdate();
            }

            rondeNummer++;

            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public static void ratingSysteem(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT AVG(G.rating) as average FROM gast G JOIN tafelgasten T on G.idgast = T.idgast WHERE idtoernooi = " + idToernooi + " GROUP BY idtafel");
            int avgRating;
            ArrayList<Integer> tafelAvg= new ArrayList<>();
            while(rs.next()){
                avgRating = rs.getInt("average");
                tafelAvg.add(avgRating);
            }
            for(int i = 0; i < tafelAvg.size(); i++){
                try{
                    rs = st.executeQuery("SELECT G.rating, T.winnaar FROM gast G JOIN Tafel T ON G.idgast = T.TafelNummer WHERE idtoernooi = " + idToernooi + " AND TafelNummer = " + i);
                    if(rs.next()){
                        try{
                            int rating = Integer.parseInt(rs.getString(1));
                            int idgast = Integer.parseInt(rs.getString(2));
                            if(rating < tafelAvg.get(i)){
                                rating =+ tafelAvg.get(i) * 2;
                            }else{
                                rating =+ tafelAvg.get(i);
                            }
                            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET rating = " + rating + " WHERE idgast = " + idgast);
                            ps.executeUpdate();
                        }catch(SQLException exception){exception.printStackTrace();}
                    }
                }catch(SQLException exception){exception.printStackTrace();}
            }

        } catch(SQLException exception){exception.printStackTrace();}
    }

    public static void prijzenGeldVerdeling(int idToernooi){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select (count(idGast) * inleg), winnaar, tweedePlek from inschrijvingtoernooi I join toernooi T on I.idToernooi = T.idToernooi where I.idToernooi = " + idToernooi);
            int winnaarID = 0;
            int tweedePlekID = 0;
            Double eerstePrijs = 0.00;
            Double tweedePrijs = 0.00;
            while(rs.next()) {
                int totaleInleg = rs.getInt(1);
                winnaarID = rs.getInt(2);
                tweedePlekID = rs.getInt(3);
                eerstePrijs = (totaleInleg * 0.4);
                tweedePrijs = (totaleInleg * 0.25);
            }

            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET prijzenGeld = prijzenGeld + "+ eerstePrijs +" WHERE idgast = " + winnaarID);
            ps.executeUpdate();
            ps = ConnectionManager.getConnection().prepareStatement("UPDATE gast SET prijzenGeld = prijzenGeld + "+ tweedePrijs +" WHERE idgast = " + tweedePlekID);
            ps.executeUpdate();

        } catch(SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {
        rondeIndeling(1);
        rondeIndelingToevoegen(1);
            //System.out.println(tafelWinnaars.get(0));
        }
    }
