package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddBookStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/AddBook.fxml"));
        primaryStage.setTitle("春秋图书馆登录");
        primaryStage.setScene(new Scene(root, 430.0D, 665));
        primaryStage.show();
    }
}
