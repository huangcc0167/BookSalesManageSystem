package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 添加书籍的界面
 */
public class AddBookStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/AddBook.fxml"));
        primaryStage.setTitle("添加记录");
        primaryStage.setScene(new Scene(root, 430.0D, 665));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
