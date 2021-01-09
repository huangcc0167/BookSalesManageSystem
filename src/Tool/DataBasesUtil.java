package Tool;

import java.sql.*;

/**
 * DAO类，数据库的增删查改都在这
 */
public class DataBasesUtil {
    /**
     * 增加进货信息
     * @param book
     */
    public void AddBookAction(BookIn book) {
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql = "insert into bookinformation.bookin(name,writer,price,chuban,number,Time) values(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getWriter());
            ps.setDouble(3, book.getPrice());
            ps.setString(4, book.getChuban());
            ps.setInt(5, book.getNumber());
            ps.setDate(6, book.getTime());

            ps.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加已有书籍的库存
     * @param book
     * @return
     */
    public boolean AddBookAction(Book book) {
        connection cnn=new connection();
        Connection con = cnn.getConnection();
        String sql1 = "select * from bookinformation.bookspage";
        Statement stmt1 = null;
        try {
            stmt1 = (Statement) con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()) {
                if (rs1.getString(1).equals(book.getName())) {
                    String sql2 = "update bookinformation.bookspage set store=? where name=? ";
                    PreparedStatement ps2 = null;
                    int newn = book.getStore() + rs1.getInt(6);
                    ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, newn);
                    ps2.setString(2, book.getName());
                    ps2.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 如果书名存在，则增加新的数据
     * @param book
     */
    public void AddBookActionFalse(Book book){
        connection cnn=new connection();
        Connection con=cnn.getConnection();
        String sql="insert into bookinformation.bookspage(name,writer,price,time,chuban,store,classes,image) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2,book.getWriter());
            ps.setDouble(3,book.getPrice());
            ps.setString(4,book.getTime());
            ps.setString(5,book.getChuban());
            ps.setInt(6,book.getStore());
            ps.setString(7,book.getClasses());
            ps.setString(8,book.getImage());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑书籍全部信息
     * @param book
     * @param bookname
     */
    public void EditorBook(Book book,String bookname){
        connection cnn=new connection();
        Connection con=cnn.getConnection();

        String sql = "update bookinformation.bookspage set name=? ,writer=?,price=?,time=?,chuban=?,store=?,image=?where name=? ";
        PreparedStatement ps = null;
        try {
            String price= String.valueOf(book.getPrice());
            String  store= String.valueOf(book.getStore());
            ps = con.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2,book.getWriter());
            ps.setString(3,price);
            ps.setString(4,book.getTime());
            ps.setString(5,book.getChuban());
            ps.setString(6,store);
            ps.setString(7,book.getImage());
            ps.setString(8,bookname);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}