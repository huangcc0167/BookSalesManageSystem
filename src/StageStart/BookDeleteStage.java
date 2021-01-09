package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 书籍选择编辑界面
 */
public class BookDeleteStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookDelete.fxml"));
        primaryStage.setTitle("编辑信息");
        primaryStage.setScene(new Scene(root, 440.0D, 750));
        primaryStage.show();
    }
}
