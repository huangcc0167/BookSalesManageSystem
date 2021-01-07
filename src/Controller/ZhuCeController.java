package Controller;

import Tool.connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;


public class ZhuCeController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane root;

    public void sureAction() {
        connection cnn = new connection();
        Connection con = cnn.getConnection();
        String sql = "select * from bookinformation.username";
        String sql2 = "insert into bookinformation.username(name,password) values(?,?)";
        Statement stmt = null;
        PreparedStatement ps = null;
        String name = username.getText();
        String pwd = password.getText();
        if (name.length() != 0 && pwd.length() != 0) {
            int flag = 0;
            try {
                stmt = (Statement) con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    if (rs.getString(1).equals(name)) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    ps = con.prepareStatement(sql2);
                    ps.setString(1, name);
                    ps.setString(2, pwd);
                    ps.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.headerTextProperty().set("欢迎注册！");
                    alert.showAndWait();

                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.headerTextProperty().set("用户名已存在！");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("请输入密码或用户名！");
            alert.showAndWait();
        }
    }
}
