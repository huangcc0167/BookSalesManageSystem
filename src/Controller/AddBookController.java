package Controller;

import StageStart.BookInStage;
import Tool.Book;
import Tool.BookIn;
import Tool.DataBasesUtil;
import Tool.connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;
import java.util.Calendar;

public class AddBookController {
    @FXML
    private TextField name;
    @FXML
    private TextField writer;
    @FXML
    private TextField price;
    @FXML
    private TextField chuban;
    @FXML
    private TextField time;
    @FXML
    private TextField number;
    @FXML
    private TextField classes;
    @FXML
    private TextField fileT;
    @FXML
    private AnchorPane root;

    /**
     * 增加进货信息，如果书名已经存在，此书数量增加，如果不存在则新建书籍
     */
    public void AddBookAction(){
        if(name.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入书名！");
            alert.showAndWait();
        }
        else if(writer.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入作者名！");
            alert.showAndWait();
        }
         else if(price.getText().length()==0){
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.headerTextProperty().set("请输入售价！");
             alert.showAndWait();
         }
         else if(chuban.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入出版社！");
            alert.showAndWait();
        }
        else if(time.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入出版时间！");
            alert.showAndWait();
        }
        else if(number.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入进货数量！");
            alert.showAndWait();
        }
        else if(classes.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入作品分类！");
            alert.showAndWait();
        }
        else{
            String newname=name.getText();
            String newwriter=writer.getText();
            String newprice=price.getText();
            double num = Double.parseDouble(newprice);
            String newchuban=chuban.getText();
            String newtime=time.getText();
            int newnumber= Integer.parseInt(number.getText());
            String newclasses=classes.getText();
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            String image=fileT.getText();
            String str= image.substring(24);
            String s1="Image/"+str;

            connection cnn=new connection();
            Connection con=cnn.getConnection();

            DataBasesUtil dbu = new DataBasesUtil();
            dbu.AddBookAction(new BookIn(newname,newwriter,num,newchuban,newnumber,date));

            boolean flag=dbu.AddBookAction(new Book(newname,newnumber));
                if(flag==false){
                    dbu.AddBookActionFalse(new Book(newname,newwriter,num,newtime,newchuban,newnumber,newclasses,s1));
                }
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("添加成功！");
            alert.showAndWait();

            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
            BookInStage bis=new BookInStage();
            try {
                bis.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**
     * 设置封面图片地址
     */
    public void setImageAddress(){
        FileChooser fileChooser = new FileChooser();
        File file=null;
        file = fileChooser.showOpenDialog(new Stage());

        if(file!=null) {
            String path = null;
            path = file.getAbsolutePath();//选择的文件夹路径
            fileT.setText(path);
        }
    }

    /**
     * 取消增加
     */
    public void quxiaoAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        BookInStage bis=new BookInStage();
        try {
            bis.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
