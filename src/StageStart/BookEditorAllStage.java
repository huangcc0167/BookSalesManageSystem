package StageStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 书籍全部信息编辑界面
 */
public class BookEditorAllStage extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookEditorAll.fxml"));
        primaryStage.setTitle("图书信息管理");
        primaryStage.setScene(new Scene(root, 580.0D, 680.0D));
        primaryStage.show();
    }
}
