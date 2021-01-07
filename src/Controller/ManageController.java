package Controller;

import StageStart.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageController {
//    @FXML
//    private MenuItem closed;
    @FXML
    private AnchorPane root;
    public void closed(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void xiaoshouAction(){
        BookSeldStage bss=new BookSeldStage();
        try {
            bss.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void BookManageAction(){
        BookManageStage bms=new BookManageStage();
        try {
            bms.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BookInAction(){
        BookInStage bi=new BookInStage();
        try {
            bi.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BookClassesAction(){
        BookClassesStage bcs=new BookClassesStage();
        try {
            bcs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BookDeleteAction(){
        BookDeleteStage bds=new BookDeleteStage();
        try {
            bds.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
