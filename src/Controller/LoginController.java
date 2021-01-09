package Controller;

import StageStart.ManageStage;
import StageStart.UsersMainStage;
import StageStart.ZhuCeStage;
import Tool.connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField name;
    @FXML
    private PasswordField pwd;
    @FXML
    public AnchorPane root;

    /**
     * 登录 判断账号是否存在以及判断密码
     */
    @FXML
    public void loginAction(){
        String username=name.getText();
        String password=pwd.getText();
        if(username.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入账号！");
            alert.showAndWait();
        }else if(password.length()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入密码！");
            alert.showAndWait();
        }
        else if(username.equals("user123")){
            if(!password.equals("qwer")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.headerTextProperty().set("管理员密码错误，请重新输入！");
                alert.showAndWait();
            }else if(password.equals("qwer")){
                ManageStage manageStage=new ManageStage();
                Stage newstage = new Stage();
                newstage.setMaximized(true);
                try {
                    manageStage.start(newstage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
        }
        else {
            connection cnn = new connection();
            Connection con = cnn.getConnection();
            String sql = "select * from bookinformation.username";
            Statement stmt = null;
            int flag=0;
            try {
                stmt = (Statement) con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
                        FileWriter writer2;
                        try {
                            writer2 = new FileWriter("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\username.txt");
                            writer2.write("");//清空原文件内容
                            writer2.write(username);
                            writer2.flush();
                            writer2.close();
                        } catch ( IOException e) {
                            e.printStackTrace();
                        }
                        UsersMainStage ums = new UsersMainStage();
                        Stage newstage = new Stage();
                        newstage.setMaximized(true);
                        ums.start(newstage);

                        Stage stage = (Stage) root.getScene().getWindow();
                        stage.close();
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.headerTextProperty().set("用户名或密码错误！");
                    alert.showAndWait();
                }
            }catch(SQLException e){
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

    /**
     * 注册
     */
    public void ZhuceAction(){
        ZhuCeStage zcs=new ZhuCeStage();
        try {
            zcs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
