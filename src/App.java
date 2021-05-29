
import controllers.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tools.DB_Con;
import tools.Utils;

public class App extends Application {

    @Override
    public void start(Stage appStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Login.fxml"));

        Parent login = loader.load();

        Scene appScene = new Scene(login);

        Login controller = (Login)loader.getController();
        controller.sc = appScene;

        appStage.setScene(appScene);
        appStage.setTitle("Mini Student Management - Authentication");
        appStage.setResizable(false);
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