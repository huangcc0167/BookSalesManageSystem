package Controller;

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

    /**
     * 获取表格单元格
     * @return
     * @throws SQLException
     */
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

    /**
     * 初始化界面
     * @param url
     * @param resourceBundle
     */
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

//    public void daochu(){
//        File file=new File("d:/售货信息.xls");
//        if(!file.exists()) {
//            try {
//                file.createNewFile();
//            }catch( IOException k) {
//                k.printStackTrace();
//            }
//        }
//        try {
//
//            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
//            connection cnn=new connection();
//            Connection con=cnn.getConnection();
//            String sql="select * from bookinformation.bookseld";
//            Statement stmt = (Statement) con.createStatement();
//            ResultSet rs =stmt.executeQuery(sql);
//
//            bw.write("买家用户名");
//            bw.write("\t");
//            bw.write("书名");
//            bw.write("\t");
//            bw.write("作者");
//            bw.write("\t");
//            bw.write("售价");
//            bw.write("\t");
//            bw.write("交易成功时间");
//            bw.write("\t");
//            bw.newLine();
//
//            while(rs.next()) {
//                bw.write(rs.getString(1));
//                bw.write("\t");
//                bw.write(rs.getString(2));
//                bw.write("\t");
//                bw.write(rs.getString(3));
//                bw.write("\t");
//                bw.write(String.valueOf(rs.getDouble(4)));
//                bw.write("\t");
//                bw.write(String.valueOf(rs.getDate(5)));
//                bw.write("\t");
//                bw.newLine();
//            }
//            bw.close();
//        }catch(IOException | SQLException l) {
//            l.printStackTrace();
//        }
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.headerTextProperty().set("导出成功！");
//        alert.showAndWait();
//    }

}
