package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookEditorAllStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookEditorAll.fxml"));
        primaryStage.setTitle("图书信息管理");
        primaryStage.setScene(new Scene(root, 580.0D, 680.0D));
        primaryStage.show();
    }
}
