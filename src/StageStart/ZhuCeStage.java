package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZhuCeStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/ZhuCe.fxml"));
        primaryStage.setTitle("注册");
        primaryStage.setScene(new Scene(root, 600.0D, 400));
        primaryStage.show();
    }
}
