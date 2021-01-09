package Controller;

import StageStart.BookShoppingStage;
import StageStart.UserAllBookStage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UsersMainController  {
    @FXML
    private AnchorPane root;

    /**
     * 打开用户购买界面
     */
    public void shoppingAction(){
        BookShoppingStage bss=new BookShoppingStage();
        try {
            bss.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示已买书籍界面
     */
    public void ShowMyBook(){
        UserAllBookStage uabs=new UserAllBookStage();
        try {
            uabs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出主菜单
     */
    public void ExitAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
