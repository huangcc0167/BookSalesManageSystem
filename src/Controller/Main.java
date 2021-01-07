package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        primaryStage.setTitle("春秋图书馆");
        primaryStage.setScene(new Scene(root, 505, 270));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}