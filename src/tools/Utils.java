package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Utils {

    static Text a, b, c, d, e, displayed_date;
    public static String user;

    private Text db_status;
    private Button btn;
    private static Thread db_checker, abcdefThread;
    DB_Con con = new DB_Con();

    public static void log(String str){
        System.out.printf("[APP] %s : %s\n",new Date().toGMTString(),str);
    }

    public void setabcdef(Text [] txts){
        a = txts[0];
        b = txts[1];
        c = txts[2];
        d = txts[3];
        e = txts[4];
    }

    public void setDisplayed_date(Text givenText){displayed_date = givenText; }

    public void setStatusText(Text status, Button btn){ db_status = status; this.btn = btn; }

    public static void quitApp(){
        System.exit(0);
    }


    public void checkDb(){
        while(true){
            try {
                db_status.getStyleClass().clear();
                if (!new DB_Con().getConnectionStatus()) {
                    db_status.setText("Disconnected");
                    db_status.getStyleClass().add("failed");
                    btn.setDisable(true);
                    new DB_Con().initConnection();
                } else {
                    db_status.setText("Connected");
                    db_status.getStyleClass().add("success");
                    btn.setDisable(false);
                }
                Thread.sleep(2000);
            }catch (Exception e){ }
        }
    }

    public void startChecker(){
        db_checker = new Thread(this::checkDb);
        db_checker.start();
    }

    public static void clearTextFields(TextField [] txtflds ) { for(TextField t : txtflds) t.setText(""); }

    public static void clearCombos(ComboBox [] comb){ for(ComboBox C : comb) C.setValue(C.getItems().get(0)); }

    public String getCurTimeAMPM(){
        DateFormat df = new SimpleDateFormat("hh:mm aa");
        String ds = df.format(new Date()).toString();
        String curTime = ds;
        if(curTime.charAt(0) == '0') curTime = " "+curTime.substring(1);
        return curTime;
    }

    public String getCurDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
    }

    public void updateabcdef(){
        while(true){
            try{
                displayed_date.setText("It's "+getCurTimeAMPM());

                Thread.sleep(3000);
                PreparedStatement ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS");
                a.setText(con.query(ps)+"");

                ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS where date_added = ?");
                ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                b.setText(con.query(ps)+"");

                ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS where isActive=0");
                c.setText(con.query(ps)+"");

                ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS where student_type = 'Regular' && isActive=1");
                d.setText(con.query(ps)+"");

                ps = DB_Con.getCon().prepareStatement("SELECT * FROM STUDENTS where student_type = 'Irregular' && isActive=1");
                e.setText(con.query(ps)+"");

            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void startAutoUpdateabcdef(){
        abcdefThread = new Thread(this::updateabcdef);
        abcdefThread.start();
    }

    public static boolean isValidMobileNumber(String text){
        for(char t : text.toCharArray())
            if(!Character.isDigit(t)) return false;
        return text.length() <= 11;
    }

    public static Alert alert(String title, String header, String message, Alert.AlertType altype){
        Alert alert = new Alert(altype);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }

    public static void stopThreads(){
            if(db_checker!= null)
                db_checker.stop();
            if(abcdefThread != null)
                abcdefThread.stop();
    }

    public int [] getQuickStatus (){
        int t [] = new int[6];
        return t;
    }

}
