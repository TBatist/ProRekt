import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Toernooi {
    static ArrayList<Integer> gastenLijst = new ArrayList<>();
    static ArrayList<Integer[]> tafelLijst = new ArrayList<Integer[]>();

    public static void tafelsMaken(){
        int inschrijvingen;
        //Collections.shuffle(gastenLijst);

        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select idGast as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
            while(rs.next()) {
                gastenLijst.add(rs.getInt(1));
                gastenLijst.add(rs.getInt(2));
                gastenLijst.add(rs.getInt(3));
                gastenLijst.add(rs.getInt(4));
                gastenLijst.add(rs.getInt(5));
                gastenLijst.add(rs.getInt(6));
                gastenLijst.add(rs.getInt(7));
                gastenLijst.add(rs.getInt(8));
                gastenLijst.add(rs.getInt(9));
            }

            int tafelNummer = 0;

            for (int i = 0; i < gastenLijst.size(); i++) {
                    if(i % 5 == 0){
                    tafelLijst.add(new Integer[5]);
                    tafelNummer++;}
                for (int j = 0; j < 5; j++) {
                    tafelLijst.get(tafelNummer)[j] = gastenLijst.get(i);
                    if(j == 4){
                        j = 0;
                    }
                }
            }

        } catch (SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {
        tafelsMaken();
    }
}
