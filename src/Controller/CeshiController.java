package Controller;

import StageStart.shoppingStage;
import Tool.Book;
import Tool.connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CeshiController implements Initializable {
    @FXML
    private ListView<Book> BookList;
    private ObservableList<Book> Book_data = FXCollections.observableArrayList();

    private String bookname;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="select * from bookinformation.bookspage";

        BookList.setItems(Book_data);
        BookList.setFixedCellSize(90);
        BookList.setCellFactory(new Callback<javafx.scene.control.ListView<Book>, ListCell<Book>>() {
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
                            ImageView iv = new ImageView(new Image("Image/cc.png", 50, 80, false, false));
                            Button button=new Button("购买");
                            button.setTranslateX(40);
                            button.setTranslateY(20);
                            MyEventHandler handler=new MyEventHandler();
                            button.setOnAction(handler);
                            hbox.getChildren().addAll(iv, label,button);

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
        BookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book book, Book t1) {
                bookname=t1.getName();
            }
        });
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while(rs.next()){
                Book_data.add(new Book(rs.getString(1),rs.getString(2),rs.getDouble(3)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private class MyEventHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            connection cnn=new connection();
            Connection con=cnn.getConnection();

            //UPDATE bookinformation.bookspage SET classes = ? WHERE name = ;
            String sql = "delete  from bookinformation.bookspage  where name=? ";
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);

                //ps.setString(1, "Image/cc.png");
                ps.setString(1,"红楼梦");

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
