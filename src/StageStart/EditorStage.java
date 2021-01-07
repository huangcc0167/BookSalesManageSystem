package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/Editor.fxml"));
        primaryStage.setTitle("编辑");
        primaryStage.setScene(new Scene(root, 570.0D, 320));
        primaryStage.show();
    }
}
