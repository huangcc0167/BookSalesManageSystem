package Controller;

import StageStart.BookInStage;
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

            String sql = "insert into bookinformation.bookin(name,writer,price,chuban,number,Time) values(?,?,?,?,?,?)";
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, newname);
                ps.setString(2,newwriter);
                ps.setDouble(3,num);
                ps.setString(4,newchuban);
                ps.setInt(5,newnumber);
                ps.setDate(6,date);

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }//bookin

            Connection con1=cnn.getConnection();
            String sql1="select * from bookinformation.bookspage";
            Statement stmt1 = null;
            try {
                int flag=0;
                stmt1 = (Statement) con.createStatement();
                ResultSet rs1 =stmt1.executeQuery(sql1);
                while(rs1.next()){
                    if(rs1.getString(1).equals(newname)){
                        String sql2="update bookinformation.bookspage set store=? where name=? ";
                        PreparedStatement ps2 = null;
                        int newn=newnumber+rs1.getInt(6);
                        ps2 = con1.prepareStatement(sql2);
                        ps2.setInt(1, newn);
                        ps2.setString(2,newname);
                        ps2.executeUpdate();
                        flag=1;
                    }
                }
                if(flag==0){
                    String sql3="insert into bookinformation.bookspage(name,writer,price,time,chuban,store,classes,image) values(?,?,?,?,?,?,?,?)";
                    PreparedStatement ps3 = null;
                    try {
                        ps3 = con.prepareStatement(sql3);

                        ps3.setString(1, newname);
                        ps3.setString(2,newwriter);
                        ps3.setDouble(3,num);
                        ps3.setString(4,newtime);
                        ps3.setString(5,newchuban);
                        ps3.setInt(6,newnumber);
                        ps3.setString(7,newclasses);
                        ps3.setString(8,s1);

                        ps3.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
    }

    public void setImageAddress(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        String path = file.getPath();//选择的文件夹路径
        fileT.setText(path);
    }
}
