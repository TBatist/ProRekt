import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Toernooi {
    static ArrayList<Integer> gastenLijst = new ArrayList<>();
    static ArrayList<Integer[]> tafelLijst = new ArrayList<>();

    public static void tafelsMaken(){
        int inschrijvingen;
        //Collections.shuffle(gastenLijst);

        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select idGast as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
            while(rs.next()) {
                gastenLijst.add(rs.getInt("inschrijvingen"));
            }

            int tafelNummer = -1;
            int j = 0;

            for (int i = 0; i < gastenLijst.size(); i++) {
                    if(i % 5 == 0){
                    tafelLijst.add(new Integer[5]);
                    tafelNummer++;
                        System.out.println(1);
                    }
                for (j = 0; j < 5; j++) {
                    tafelLijst.get(tafelNummer)[j] = gastenLijst.get(i);

                }
                if(j == 4){
                    j = 0;
                }
            }

        } catch (SQLException exception){exception.printStackTrace();}
    }

    public static void main(String[] args) {
        tafelsMaken();
    }
}
