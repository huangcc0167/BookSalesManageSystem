package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookInStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookIn.fxml"));
        primaryStage.setTitle("书籍购买查询");
        primaryStage.setScene(new Scene(root, 730.0D, 800.0D));
        primaryStage.show();
    }
}
