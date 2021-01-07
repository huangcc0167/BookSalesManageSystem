package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 504.0D, 272.0D));
        primaryStage.show();
    }
}
