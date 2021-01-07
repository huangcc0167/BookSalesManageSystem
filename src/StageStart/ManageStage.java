package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/ManagePage.fxml"));
        primaryStage.setTitle("ManagerPage");
        primaryStage.setScene(new Scene(root, 400.0D, 524.0D));
        primaryStage.show();
    }
}
