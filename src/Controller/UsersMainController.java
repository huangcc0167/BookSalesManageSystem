package Controller;

import StageStart.BookShoppingStage;
import StageStart.UserAllBookStage;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UsersMainController {
    @FXML
    private AnchorPane root;
    public void shoppingAction(){
        BookShoppingStage bss=new BookShoppingStage();
        try {
            bss.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ShowMyBook(){
        UserAllBookStage uabs=new UserAllBookStage();
        try {
            uabs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ExitAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
