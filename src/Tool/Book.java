package Tool;

/**
 * 对象类 关于书籍的全部构造方法
 */
public class Book {
    private String name,writer,classes;
    private double price;
    private String time;
    private String chuban;
    private int store;
    private String image;

    public Book(String name,String writer,double price, String time,String chuban,int store,String classes){
        this.name=name;
        this.writer=writer;
        this.price=price;
        this.time=time;
        this.chuban=chuban;
        this.store=store;
        this.classes=classes;
    }

    public Book(String name,String writer,double price,String time,String chuban,String str,int store){
        this.name=name;
        this.writer=writer;
        this.price=price;
        this.time=time;
        this.chuban=chuban;
        this.store=store;
        this.image=str;
    }

    public Book(String name,String writer,String chuban,String classes){
        this.name=name;
        this.writer=writer;
        this.classes=classes;
        this.chuban=chuban;
    }

//    public Book(String bookname,String writer,String chuban,double price,int number,String time){
//        this.name=bookname;
//        this.writer=writer;
//        this.store=number;
//        this.price=price;
//        this.chuban=chuban;
//        this.time=time;
//    }

//    public Book(String bookname,String writer,double price,String time,String chuban,String classes){
//        this.time=time;
//        this.writer=writer;
//        this.name=bookname;
//        this.price=price;
//        this.chuban=chuban;
//        this.classes=classes;
//    }

    public Book(String bookname,String writer,double price,String chuban){
        this.name=bookname;
        this.writer=writer;
        this.price=price;
        this.chuban=chuban;
    }

    public Book(double price,String name,String writer,String image){
        this.price=price;
        this.name=name;
        this.writer=writer;
        this.image=image;
    }

//    public Book(String bookname,String writer,double price){
//        this.name=bookname;
//        this.writer=writer;
//        this.price=price;
//    }

    public Book(String name,int num){
        this.name=name;
        this.store=num;
    }

    public Book(String name,String writer,double price,String time,String chuban,int store,String classes,String image){
        this.name=name;
        this.writer=writer;
        this.price=price;
        this.time=time;
        this.chuban=chuban;
        this.store=store;
        this.classes=classes;
        this.image=image;
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getChuban() {
        return chuban;
    }

    public String getWriter() {
        return writer;
    }

    public String getData() {
        return time;
    }

    public int getStore() {
        return store;
    }

    public String getTime() {
        return time;
    }

    public String getClasses() {
        return classes;
    }

    public String getImage() {
        return image;
    }
}