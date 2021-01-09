package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 全部书籍查询界面
 */
public class BookManageStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookManage.fxml"));
        primaryStage.setTitle("ManagerPage");
        primaryStage.setScene(new Scene(root, 760.0D, 747.0D));
        primaryStage.show();
    }
}
