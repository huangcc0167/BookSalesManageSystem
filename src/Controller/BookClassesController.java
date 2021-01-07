package Controller;

import StageStart.EditorStage;
import Tool.Book;
import Tool.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookClassesController implements Initializable {
    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> writer;
    @FXML
    private TableColumn<Book, String>chuban;
    @FXML
    private TableColumn<Book,String>classes;
    @FXML
    private TableColumn<Book,String>delCol;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField look;
    @FXML
    private TableView<Book> BookTable;
    private ObservableList<Book> BookLists = FXCollections.observableArrayList();

    private String bookname;

    public ObservableList<Book> getBookData() throws SQLException {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookspage";
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs =stmt.executeQuery(sql);

        while(rs.next()) {
            BookLists.add(new Book(rs.getString(1), rs.getString(2), rs.getString(5)
                    ,rs.getString(7)));
        }

        delCol.setCellFactory((col) -> {
            TableCell<Book, String> cell = new TableCell<Book, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Button delBtn = new Button("编辑");
                        this.setGraphic(delBtn);
                        delBtn.setOnMouseClicked((me) -> {
                            Book clickedBook = this.getTableView().getItems().get(this.getIndex());
                            bookname=clickedBook.getName();
                            FileWriter writer;
                            try {
                                writer = new FileWriter("C:\\Users\\86134\\IdeaProjects\\BookSalesManagementSystem\\src\\TXT\\bookname.txt");
                                writer.write("");//清空原文件内容
                                writer.write(bookname);
                                writer.flush();
                                writer.close();
                            } catch ( IOException e) {
                                e.printStackTrace();
                            }

                            EditorStage es=new EditorStage();
                            try {
                                es.start(new Stage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Stage stage = (Stage) root.getScene().getWindow();
                            stage.close();
                            System.out.println(bookname);
                        });
                    }
                }
            };
            return cell;
        });
        return BookLists;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        writer.setCellValueFactory(new PropertyValueFactory<>("writer"));
        chuban.setCellValueFactory(new PropertyValueFactory<>("chuban"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));

        try {
            BookTable.setItems(getBookData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lookAction(){
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        Statement stmt= null;
        String lookword=look.getText();
        try {
            stmt = con.createStatement();
            String sql="select * from bookinformation.bookspage where classes like '%"+lookword+"%'";
            ResultSet rs=stmt.executeQuery(sql);
            BookLists.clear();
            while(rs.next()){

                BookLists.add(new Book(rs.getString(1), rs.getString(2), rs.getString(5)
                        ,rs.getString(7)));
                BookTable.setItems(BookLists);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
