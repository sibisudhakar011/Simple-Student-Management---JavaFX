package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tools.Utils;

public class About extends Application {

    @FXML
    public void goBack(ActionEvent e){
        try {
            Utils.stopThreads();
            Utils.log("[Login Success]");
            Node node = (Node) e.getSource();
            Stage appStage = (Stage) node.getScene().getWindow();
            FXMLLoader load = new FXMLLoader(getClass().getResource("../UI/Home.fxml"));
            Parent HOME = load.load();
            Scene appScene = new Scene(HOME);
            appStage.setScene(appScene);
            appStage.setTitle("Mini Student Management - Home");
            appStage.centerOnScreen();
            appStage.setMinHeight(730);
            appStage.setMinWidth(1280);
            appStage.setResizable(true);
        }catch (Exception ex){}
    }

    @Override
    public void start(Stage appStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/About.fxml"));
        Parent About = loader.load();
        Scene appScene = new Scene(About);
        appStage.setScene(appScene);
        appStage.setTitle("Mini Student Management - Authentication");
        appStage.show();
    }

    @Override
    public void stop() {
        Utils.log("App Closed\n\n");
        Utils.stopThreads();
    }

    public static void main(String[] appArgs) {
        Utils.log("[MSM-Auth Win] Started");
        launch(appArgs);
    }
}