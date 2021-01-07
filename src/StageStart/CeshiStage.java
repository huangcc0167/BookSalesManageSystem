package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CeshiStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/Ceshi.fxml"));
        primaryStage.setTitle("测试");
        primaryStage.setScene(new Scene(root, 350.0D, 700.0D));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
