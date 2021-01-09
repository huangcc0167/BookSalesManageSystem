package Controller;

import StageStart.BookDeleteStage;
import Tool.Book;
import Tool.DataBasesUtil;
import Tool.connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookEditorAllController implements Initializable {
    @FXML
    private Label bookname;
    @FXML
    private Label writer;
    @FXML
    private Label chuban;
    @FXML
    private Label price;
    @FXML
    private Label store;
    @FXML
    private Label time;
    @FXML
    private Label address;
    @FXML
    private TextField booknameT;
    @FXML
    private TextField writerT;
    @FXML
    private TextField chubanT;
    @FXML
    private TextField priceT;
    @FXML
    private TextField storeT;
    @FXML
    private TextField timeT;
    @FXML
    private TextField addressT;
    @FXML
    private AnchorPane root;

    private File file = new File("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\bookname.txt");
    @Override
    /**
     * 初始化编辑界面
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            bookname.setText(booksname);
            booknameT.setText(booksname);

            connection cnn=new connection();
            Connection con=cnn.getConnection();
            String sql="select * from bookinformation.bookspage";
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while(rs.next()){
                String rsname=rs.getString(1);
                if(rsname.equals(booksname)){
                    writer.setText(rs.getString(2));
                    chuban.setText(rs.getString(5));
                    price.setText(String.valueOf(rs.getDouble(3)));
                    store.setText(String.valueOf(rs.getInt(6)));
                    time.setText(rs.getString(4));
                    address.setText(rs.getString(8));
                    writerT.setText(rs.getString(2));
                    chubanT.setText(rs.getString(5));
                    priceT.setText(String.valueOf(rs.getDouble(3)));
                    storeT.setText(String.valueOf(rs.getInt(6)));
                    timeT.setText(rs.getString(4));
                    addressT.setText(rs.getString(8));
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更改书籍的全部信息
     */
    public void changedAction(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            String newname=booknameT.getText();
            String newwriter=writerT.getText();
            String newchuban=chubanT.getText();
            String newprice=priceT.getText();
            String newstore=storeT.getText();
            String newtime=timeT.getText();
            String newaddress=addressT.getText();
            String s=address.getText();
            String str=address.getText();

            if(!s.equals(newaddress)) {
                 s= newaddress.substring(24);
                 String s1="Image/"+s;
                 str=s1;
            }

            double price= Double.parseDouble(newprice);
            int store= Integer.parseInt(newstore);
            DataBasesUtil dbu=new DataBasesUtil();
            dbu.EditorBook(new Book(newname,newwriter,price,newtime,newchuban,str,store),booksname);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.headerTextProperty().set("更改成功！");
        alert.showAndWait();

        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        BookDeleteStage bds=new BookDeleteStage();
        try {
            bds.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下架书本
     */
    public void deleteBook(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            String sql = "delete from bookinformation.bookspage where name=?";

            connection cnn=new connection();
            Connection con=cnn.getConnection();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, booksname);
                ps.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.headerTextProperty().set("下架成功！");
                alert.showAndWait();

                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
                BookDeleteStage bds=new BookDeleteStage();
                bds.start(new Stage());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择上传的封面
     */
    public void selectFile(){
        addressT.clear();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        if(file!=null) {
            String path = file.getPath();
            addressT.setText(path);
        }
    }
}
