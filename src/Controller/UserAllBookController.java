package Controller;

import Tool.Book;
import Tool.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserAllBookController implements Initializable {
    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> writer;
    @FXML
    private TableColumn<Book, Double> price;
    @FXML
    private TableColumn<Book, String>chuban;
    @FXML
    private TextField look;
    @FXML
    private TableView BookTable;
    private File file = new File("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\username.txt");

    private ObservableList<Book> BookLists = FXCollections.observableArrayList();

    public ObservableList<Book> getBookData(){
        connection cnn=new connection();
        Connection con=cnn.getConnection();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String booksname=br.readLine();
            String sql="select * from bookinformation.bookseld where userId="+booksname;
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);

            while(rs.next()) {
                BookLists.add(new Book(rs.getString(2), rs.getString(3), rs.getDouble(4),
                rs.getString(6)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return BookLists;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        writer.setCellValueFactory(new PropertyValueFactory<>("writer"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        chuban.setCellValueFactory(new PropertyValueFactory<>("chuban"));

        BookTable.setItems(getBookData());
    }

    public void LookAction(){
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        Statement stmt= null;//创建一个Statement对象
        String lookword=look.getText();
        try {
            stmt = con.createStatement();
            String sql="select * from bookinformation.bookseld where bookname like '%"+lookword+"%'";//生成sql语句
            //System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                BookLists.clear();
                BookLists.add(new Book(rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(6)));
                BookTable.setItems(BookLists);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
