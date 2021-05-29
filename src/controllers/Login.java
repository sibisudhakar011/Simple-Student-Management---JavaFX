package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.DB_Con;
import tools.Utils;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login implements Initializable {

    DB_Con con;
    public Scene sc;

    @FXML
    TextField admin_id;
    @FXML
    PasswordField admin_key;
    @FXML
    Button login_btn;
    @FXML
    Text db_status;
    @FXML
    Text login_stat;

    public void logon(ActionEvent e){
        try{
            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM ADMIN WHERE (id = ? or email=?) and password=?");
            ps.setString(1,admin_id.getText());
            ps.setString(2,admin_id.getText());
            ps.setString(3,admin_key.getText());

            login_stat.setText("");
            login_stat.getStyleClass().clear();

            if(con.query(ps) > 0) {
                Utils.stopThreads();
                Utils.log("[Login Success]");
                Node node = (Node) e.getSource();
                Stage appStage = (Stage) node.getScene().getWindow();
                FXMLLoader load = new FXMLLoader(getClass().getResource("../UI/Home.fxml"));
                Parent HOME = load.load();
                ResultSet res = con.getResult();
                res.next();

                Utils.user = res.getString("id");

                Scene appScene = new Scene(HOME);

                appStage.setScene(appScene);
                appStage.setTitle("Mini Student Management - Home");
                appStage.centerOnScreen();
                appStage.setMinHeight(730);
                appStage.setMinWidth(1280);
                appStage.setResizable(true);

            }else{
                Utils.log("[Login Failed]");
                Utils.alert("Login Failed", "Login Failed", "Please Check Your Credentials", Alert.AlertType.ERROR);
            }
        }catch (Exception xe){ System.out.println(xe); }
    }

    public void setScene(Scene sc){ this.sc = sc; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = new DB_Con();
        Utils util = new Utils();
        util.setStatusText(db_status, login_btn);
        util.startChecker();
    }
}