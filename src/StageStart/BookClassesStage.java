package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookClassesStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookClasses.fxml"));
        primaryStage.setTitle("图书类别管理");
        primaryStage.setScene(new Scene(root, 620.0D, 750.0D));
        primaryStage.show();
    }
}
