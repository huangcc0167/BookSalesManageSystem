package Tool;

import java.sql.Date;

/**
 * 卖出的书籍对象
 */
public class Bookseld {
    private String userId,bookname,writer;
    private double price;
    private Date time;
    public Bookseld(String userId, String bookname, String writer, double price, Date time){
        this.userId=userId;
        this.bookname=bookname;
        this.writer=writer;
        this.price=price;
        this.time=time;
    }
    public String getWriter() {
        return writer;
    }
    public double getPrice() {
        return price;
    }
    public String getBookname() {
        return bookname;
    }
    public String getUserId() {
        return userId;
    }
    public Date getTime() {
        return time;
    }
}
