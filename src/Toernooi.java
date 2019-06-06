import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Toernooi {
    static ArrayList<Integer> gastenLijst = new ArrayList<>();
    static ArrayList<Integer[]> tafelLijst = new ArrayList<Integer[]>();

    public static void tafelsMaken() {
        try {
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select idGast as 'inschrijvingen' from inschrijvingtoernooi where idToernooi = 1");
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

    public void toevoegenDatabase(){
        try{
            Connection con = ConnectionManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tafelgasten");

            while(rs.next()){

            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tafelsMaken();
        System.out.println("Tafel 1");
        for (int i = 0; i < 5; i++) {
            System.out.println(tafelLijst.get(0)[i]);
        }
        System.out.println("Tafel 2");
        for (int i = 0; i < 5; i++) {
            System.out.println(tafelLijst.get(1)[i]);
        }
        System.out.println("Tafel 3");
        for (int i = 0; i < 5; i++) {
            System.out.println(tafelLijst.get(2)[i]);
        }
    }
}
