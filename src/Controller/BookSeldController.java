package Controller;

import Tool.Book;
import Tool.Bookseld;
import Tool.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookSeldController implements Initializable {
    @FXML
    private TableColumn userId;
    @FXML
    private TableColumn bookname;
    @FXML
    private TableColumn writer;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn time;
    @FXML
    private TableView<Bookseld> BookTable;

    private ObservableList<Bookseld> BookLists = FXCollections.observableArrayList();

    public ObservableList<Bookseld> getBookData() throws SQLException {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookseld";
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs =stmt.executeQuery(sql);

        while(rs.next()) {
            BookLists.add(new Bookseld(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getDouble(4), rs.getDate(5)));
        }
        return BookLists;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        writer.setCellValueFactory(new PropertyValueFactory<>("writer"));

        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            BookTable.setItems(getBookData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
