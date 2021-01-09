package Controller;

import StageStart.BookClassesStage;
import Tool.connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditorController implements Initializable {
    @FXML
    private Label bookname;
    @FXML
    private Label classes;
    @FXML
    private TextField newclasses;
    @FXML
    private AnchorPane root;

    private File file = new File("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\bookname.txt");

    /**
     * 初始化界面
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            bookname.setText(booksname);

            connection cnn=new connection();
            Connection con=cnn.getConnection();
            String sql="select * from bookinformation.bookspage";
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(1).equals(booksname)){
                    classes.setText(rs.getString(7));
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更改书籍类别
     */
    public void changedAction() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            String newclassString=newclasses.getText();

            connection cnn=new connection();
            Connection con=cnn.getConnection();

            String sql = "update bookinformation.bookspage set classes=? where name=? ";
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(2,booksname);
            ps.setString(1, newclassString);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.headerTextProperty().set("更改成功！");
        alert.showAndWait();

        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        BookClassesStage bcs=new BookClassesStage();
        try {
            bcs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
