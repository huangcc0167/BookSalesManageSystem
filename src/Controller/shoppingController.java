package Controller;

import StageStart.BookShoppingStage;
import StageStart.shoppingStage;
import Tool.connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class shoppingController implements Initializable {
    @FXML
    private Label price;
    @FXML
    private Label name;
    @FXML
    private AnchorPane root;

    private File file = new File("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\buy.txt");
    private File file2 = new File("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\username.txt");

    /**
     * 页面初始化
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            name.setText(booksname);

            connection cnn=new connection();
            Connection con=cnn.getConnection();
            String sql="select * from bookinformation.bookspage";
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while(rs.next()){
                String rsname=rs.getString(1);
                if(rsname.equals(booksname)){
                    price.setText(String.valueOf(rs.getDouble(3)));
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 确定是否购买
     */
    public void TESAction() {
        String username= null;
        String booksname=null;
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            username = br2.readLine();
            BufferedReader br = new BufferedReader(new FileReader(file));
            booksname=br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="insert into bookinformation.bookseld(userId,bookname,writer,price,time,chuban) values(?,?,?,?,?,?)";
        String sql1="select * from bookinformation.bookspage";
        String sql2="update bookinformation.bookspage set store=? where name=? ";
        Statement stmt1 = null;
        try {
            stmt1 = (Statement) con.createStatement();
            ResultSet rs1 =stmt1.executeQuery(sql1);
            while(rs1.next()){
                if(rs1.getString(1).equals(booksname)){
                    if(rs1.getInt(6)>=1){
                    String writer=rs1.getString(2);
                    double prices=rs1.getDouble(3);
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    String chuban=rs1.getString(5);

                    PreparedStatement ps = null;
                    ps = con.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2,booksname);
                    ps.setString(3,writer);
                    ps.setDouble(4,prices);
                    ps.setDate(5,date);
                    ps.setString(6,chuban);
                    ps.executeUpdate();

                    PreparedStatement ps2 = null;
                    int newn=rs1.getInt(6)-1;
                    ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, newn);
                    ps2.setString(2,booksname);
                    ps2.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.headerTextProperty().set("购买成功，欢迎下次光临！");
                    alert.showAndWait();

                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.close();
                    BookShoppingStage ss=new BookShoppingStage();
                    ss.start(new Stage());
                }
                    else{
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.headerTextProperty().set("购买失败，商品数量不足！");
                        alert.showAndWait();

                        Stage stage = (Stage) root.getScene().getWindow();
                        stage.close();
                        shoppingStage ss=new shoppingStage();
                        ss.start(new Stage());
                    }
            }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出此页面，回到书籍购买页面
     */
    public void quxiaoAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        BookShoppingStage ss=new BookShoppingStage();
        try {
            ss.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
