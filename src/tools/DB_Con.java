package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.sql.*;

public class DB_Con {

    private static Connection con;
    private static String url, user, pass, dbname;
    private static int port;
    private ResultSet res;

    public void initCon(String u, int p, String us, String ps, String db){
        url = u;
        user = us;
        pass = ps;
        port = p;
        dbname = db;
        initConnection();
    }

    public static Connection getCon(){ return con; }

    public String getDBURL(){ return String.format("jdbc:mysql://%s:%d/%s", url, port, dbname); }

    public boolean initConnection(){
        try {
            con = DriverManager.getConnection(getDBURL(), user, pass);
            Utils.log("[Connection Established] "+getDBURL());
            return true;
        }catch (SQLException e){ e.printStackTrace(); return false; }
    }
    public int query(PreparedStatement ps){
        int s = 0;
        try{
            res = ps.executeQuery();
            while(res.next()) s++;
            res = ps.executeQuery();
            return s;
        }catch (Exception e){
            e.printStackTrace();
            Utils.alert("ERROR","Failed To Update", e.toString() ,Alert.AlertType.ERROR);
            return s;
        }
    }


    public boolean update(PreparedStatement ps){
        try{
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            Utils.alert("ERROR","Failed To Update", e.toString() ,Alert.AlertType.ERROR);
            return false; }
    }

    public ResultSet getResult() { return  res; }

    public boolean getConnectionStatus() {
        try {
            if(con == null) return false;
            con.createStatement().executeQuery("SELECT 1");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            Utils.alert("ERROR","Failed To Get Connection", e.toString() ,Alert.AlertType.ERROR);
            return false;
        }
    }

}
