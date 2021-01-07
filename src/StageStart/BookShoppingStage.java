package StageStart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookShoppingStage {
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("../FXML/BookShopping.fxml"));
        primaryStage.setTitle("购买图书");
        primaryStage.setScene(new Scene(root, 440.0D, 750.0D));
        primaryStage.show();
    }
}
