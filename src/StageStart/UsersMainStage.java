package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 用户主界面
 */
public class UsersMainStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/UsersMain.fxml"));
        primaryStage.setTitle("菜单");
        primaryStage.setScene(new Scene(root, 265.0D, 340.0D));
        primaryStage.show();
    }
}
