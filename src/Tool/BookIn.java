package Tool;

import java.sql.Date;

public class BookIn {
    private String name,writer,chuban;
    private int number;
    private double price;
    private Date time;
    public BookIn(String name,String writer,double price,String chuban,int number,Date time){
        this.name=name;
        this.writer=writer;
        this.chuban=chuban;
        this.price=price;
        this.time=time;
        this.number=number;
    }

    public double getPrice() {
        return price;
    }

    public String getWriter() {
        return writer;
    }

    public String getChuban() {
        return chuban;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Date getTime() {
        return time;
    }
}
