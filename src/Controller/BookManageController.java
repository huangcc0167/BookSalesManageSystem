package Controller;

import Tool.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookManageController implements Initializable {
    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> writer;
    @FXML
    private TableColumn<Book, Double> price;
    @FXML
    private TableColumn<Book, String>time;
    @FXML
    private TableColumn<Book, String>chuban;
    @FXML
    private TableColumn<Book,Integer>store;
    @FXML
    private TableColumn<Book,String>classes;
    @FXML
    private TableView<Book> BookTable;
    @FXML
    private TextField look;
    @FXML
    private Label dangqian;

    private int l=100;
    private static int sum=101;
    private int i=1;
    private int x=0;

    private ObservableList<Book> BookLists = FXCollections.observableArrayList();
    private ArrayList<Book>arrayList=new ArrayList<>();

    /**
     * 获取表格元素list
     * @return
     * @throws SQLException
     */
    public ObservableList<Book> getBookData() throws SQLException {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookspage";
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs =stmt.executeQuery(sql);
        while(rs.next()){
            Book book=new Book(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7));
            arrayList.add(book);
        }
        while(x<i*l){
            BookLists.add(arrayList.get(x));
            x++;
        }
        return BookLists;
    }

    /**
     * 初始化界面
     * @param url
     * @param resourceBundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String s=i+"/"+sum;
        dangqian.setText(s);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        writer.setCellValueFactory(new PropertyValueFactory<>("writer"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        chuban.setCellValueFactory(new PropertyValueFactory<>("chuban"));
        store.setCellValueFactory(new PropertyValueFactory<>("store"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));
        try {
            BookTable.setItems(getBookData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按书名模糊查询
     */
    public void lookAction(){
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        Statement stmt= null;
        String lookword=look.getText();
        try {
            stmt = con.createStatement();
            String sql="select * from bookinformation.bookspage where name like '%"+lookword+"%'";//生成sql语句
            //System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            BookLists.clear();
            while(rs.next()){
                BookLists.add(new Book(rs.getString(1), rs.getString(2), rs.getDouble(3),
                        rs.getString(4), rs.getString(5),rs.getInt(6),rs.getString(7)));
                BookTable.setItems(BookLists);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 翻页
     */
    public void nextAction(){
        i++;
        if(i>sum){
            i--;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("已是最后一页！");
            alert.showAndWait();
            return;
        }else {
            BookLists.clear();
            while (x < i * l) {
                BookLists.add(arrayList.get(x));
                x++;
            }
            BookTable.setItems(BookLists);
        String s=i+"/"+sum;
        dangqian.setText(s);
        }
    }

    /**
     * 前一页
     */
    public void ProAction() {
        i--;
        x = x - 2 * l;
        if (i <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.headerTextProperty().set("已是最前一页！");
            alert.showAndWait();
            i++;
            x = x + 2 * l;
            return;
        }
        BookLists.clear();
        while (x < i * l) {
            BookLists.add(arrayList.get(x));
            x++;
        }
        BookTable.setItems(BookLists);
        String s = i + "/" + sum;
        dangqian.setText(s);
    }
}