package Controller;

import StageStart.shoppingStage;
import Tool.Book;
import Tool.connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookShoppingController implements Initializable {
    @FXML
    private TextField look;
    @FXML
    private ListView<Book> BookList;
    @FXML
    private AnchorPane root;

    private ObservableList<Book> Book_data = FXCollections.observableArrayList();

    /**
     * 初始化界面
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookspage";

        BookList.setItems(Book_data);
        BookList.setFixedCellSize(90);
        BookList.setCellFactory(new Callback<ListView<Book>, ListCell<Book>>() {
            @Override
            public ListCell<Book> call(javafx.scene.control.ListView<Book> param) {
                ListCell<Book> listCell;
                listCell = new ListCell<Book>() {
                    @Override
                    protected void updateItem(Book item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null){
                            HBox hbox = new HBox(10);
                            Label label = new Label(item.getName() + "[" + item.getWriter() + "]"+"\n"+item.getPrice()+"￥");
                            label.setFont(new Font(18));
                            String s=item.getImage();
                            if(s==null){
                                s="Image/cc.png";
                            }
                            ImageView iv = new ImageView(new Image(s, 50, 80, false, false));
                            hbox.getChildren().addAll(iv, label);
                            this.setGraphic(hbox);
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while(rs.next()){
                Book_data.add(new Book(rs.getDouble(3),rs.getString(1),rs.getString(2),rs.getString(8)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        BookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book book, Book t1) {
                FileWriter writer;
                try {
                    writer = new FileWriter("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\buy.txt");
                    writer.write("");//清空原文件内容
                    writer.write(t1.getName());
                    writer.flush();
                    writer.close();
                } catch ( IOException e) {
                    e.printStackTrace();
                }
                shoppingStage ss=new shoppingStage();
                try {
                    ss.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * 按书名模糊查询
     */
    public void lookAction(){
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        Statement stmt= null;//创建一个Statement对象
        String lookword=look.getText();
        try {
            stmt = con.createStatement();
            String sql="select * from bookinformation.bookspage where name like '%"+lookword+"%'";//生成sql语句
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Book_data.clear();
                Book_data.add(new Book(rs.getDouble(3),
                        rs.getString(1), rs.getString(2), rs.getString(8)));
                BookList.setItems(Book_data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
