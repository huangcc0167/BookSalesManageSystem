package Controller;

import StageStart.AddBookStage;
import Tool.BookIn;
import Tool.Bookseld;
import Tool.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookInController  implements Initializable {
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn writer;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn chuban;
    @FXML
    private TableColumn number;
    @FXML
    private TableColumn time;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<BookIn> BookTable;

    private ObservableList<BookIn> BookLists = FXCollections.observableArrayList();

    public ObservableList<BookIn> getBookData() throws SQLException {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookin";
        Statement stmt = (Statement) con.createStatement();
        ResultSet rs =stmt.executeQuery(sql);

        while(rs.next()) {
            BookLists.add(new BookIn(rs.getString(1), rs.getString(2), rs.getDouble(3),
                    rs.getString(4), rs.getInt(5),rs.getDate(6)));
        }
        return BookLists;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        writer.setCellValueFactory(new PropertyValueFactory<>("writer"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        chuban.setCellValueFactory(new PropertyValueFactory<>("chuban"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            BookTable.setItems(getBookData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddBookAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        AddBookStage abs=new AddBookStage();
        try {
            abs.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
