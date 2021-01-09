package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 用户确定购买界面
 */
public class shoppingStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/shopping.fxml"));
        primaryStage.setTitle("shopping");
        primaryStage.setScene(new Scene(root, 430.0D, 225.0D));
        primaryStage.show();
    }
}
