package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 管理员管理界面
 */
public class ManageStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/ManagePage.fxml"));
        primaryStage.setTitle("ManagerPage");
        primaryStage.setScene(new Scene(root, 400.0D, 524.0D));
        primaryStage.show();
    }
}
