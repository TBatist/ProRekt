import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Masterclass {
    private PreparedStatement ps;
    private String JaNee;
    public Masterclass(){}

    public boolean GastAllowed (int BGastID) {
        //String JaNee;
        try {
            ps = ConnectionManager.getConnection().prepareStatement("SELECT bekspeler FROM gast WHERE idgast = " + BGastID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JaNee = rs.getString("bekspeler");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (JaNee.equals("J")) {
            return true;
        }
        else{
            return false;
        }


    }
    public void registMC(Date datum, Time beginTijd, Time eindTijd, double prijs, int minRating, int bekendeSpelerID){
        String insertMC = "INSERT INTO masterclass (datum, begin, eind, prijs, minRating, geverMasterclass) VALUES (?,?,?,?,?,?)";

        try {
            ps = ConnectionManager.getConnection().prepareStatement(insertMC);
            ps.setDate(1, datum);
            ps.setTime(2, beginTijd);
            ps.setTime(3, eindTijd);
            ps.setDouble(4, prijs);
            ps.setInt(5, minRating);
            ps.setInt(6, bekendeSpelerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
