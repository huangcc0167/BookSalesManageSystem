package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookSeldStage {
        public void start(Stage primaryStage) throws Exception {
            Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookSeld.fxml"));
            primaryStage.setTitle("销售查询");
            primaryStage.setScene(new Scene(root, 615.0D, 710.0D));
            primaryStage.show();
    }
}
