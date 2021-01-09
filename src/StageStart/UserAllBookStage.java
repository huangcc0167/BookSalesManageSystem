package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 用户已购图书信息查看
 */
public class UserAllBookStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/userAllBook.fxml"));
        primaryStage.setTitle("已拥有的图书");
        primaryStage.setScene(new Scene(root, 510.0D, 650.0D));
        primaryStage.show();
    }
}
