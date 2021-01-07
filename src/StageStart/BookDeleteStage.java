package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookDeleteStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookDelete.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 440.0D, 750));
        primaryStage.show();
    }
}
