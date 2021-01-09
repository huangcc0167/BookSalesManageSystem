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

    /**
     * 退出主菜单
     */
    public void closed(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    /**
     * 打开销售信息页面
     */
    @FXML
    public void xiaoshouAction(){
        BookSeldStage bss=new BookSeldStage();
        try {
            bss.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开图书管理页面
     */
    @FXML
    public void BookManageAction(){
        BookManageStage bms=new BookManageStage();
        try {
            bms.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开书籍买入界面
     */
    public void BookInAction(){
        BookInStage bi=new BookInStage();
        try {
            bi.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开书籍类别管理界面
     */
    public void BookClassesAction(){
        BookClassesStage bcs=new BookClassesStage();
        try {
            bcs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开书籍管理页面，编辑书籍全部信息及是否下架操作
     */
    public void BookDeleteAction(){
        BookDeleteStage bds=new BookDeleteStage();
        try {
            bds.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
