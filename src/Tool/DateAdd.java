package Tool;

import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class DateAdd {
    //定义姓氏
    private static String name1="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严" +
            "华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑" +
            "薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米" +
            "贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏" +
            "蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠" +
            "甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶" +
            "幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边" +
            "扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾" +
            "敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗" +
            "政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷" +
            "宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";
    //定义女生的名
    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    //定义男生的名
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

    private static String chuban="人民出版社;清华出版社;北大出版社;长沙出版社;北京出版社;新疆出版社;中华书局;三联书局;译林出版社;凤凰出版社;江苏出版社;广西师大出版社;湖南科技出版社;机工出版社;湖南出版社;贝贝特出版社;朗朗书房出版社;花生文库;后浪";
    public static int getNum(int start,int end) {//随机返回返回指定范围间的整数
        return (int)(Math.random()*(end-start+1)+start);
    }
    //随机返回书名
    public static StringBuilder getbookname() {//不使用String，因为需要大量拼接字符串
        int i=getNum(1,name1.length()-1);
        int j=getNum(1,name1.length()-1);
        StringBuilder xh= new StringBuilder(name1.substring(i,i+1));
        StringBuilder xh1=new StringBuilder(name1.substring(j,j+1));
        StringBuilder xh2=new StringBuilder("氏春秋");
        xh.append(xh1);
        xh.append(xh2);
        return xh;
    }
    //随机返回中文姓名
    public static String getwriter() {
        int index=getNum(0, name1.length()-1);
        String first=name1.substring(index, index+1);
        int sex=getNum(0,1);
        String str=boy;
        int length=boy.length();
        if(sex==0){
            str=girl;
            length=girl.length();
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }

    public static double getprice(){
        int n1=(int)(Math.random()*(8)+1);
        int n2=(int)(Math.random()*(8)+1);
        int n3=(int)(Math.random()*(8)+1);
        double price=n1*10+n2+n3*0.1;
        return price;
    }
    public static int gettime(){
        return (int)(Math.random()*(2020)+1);
    }
    public static String getChuban(){
        int i=getNum(0,18);
        String s[]=chuban.split(";");
        return s[i];
    }
    public static int getStore(){
        return (int)(Math.random()*100+1);
    }
    public static String getClasses(){
        return "历史文化";
    }
    public static void main(String[] args) {
        addDate();
    }

    public static void addDate() {//增加成绩
        connection cnn=new connection();
        String sql="insert into bookinformation.bookspage(name,writer,price,time,chuban,store,classes) values(?,?,?,?,?,?,?)";//使用占位符定义插入语句
        try(Connection con=cnn.getConnection();//获取数据库接
            PreparedStatement pstmt=con.prepareStatement(sql);){//实例化PreparedStatement
            ArrayList<String> alist=new ArrayList<String>();//定义集合
            for(int i=1;i<=1;) {
                String bookname=getbookname().toString();//随机获取书名
                if(!alist.contains(bookname)) {//判断书名是否唯一
                    alist.add(bookname);//将书名加入集合
                    String writer=getwriter();//随机获取姓名
                    double price=getprice();//随机获取成绩
                    int time=gettime();
                    String chuban=getChuban();
                    int store=getStore();
                    String classes=getClasses();
                    pstmt.setString(1,bookname);//定义第1个占位符的内容
                    pstmt.setString(2, writer);//定义第2个占位符的内容
                    pstmt.setDouble(3, price);
                    pstmt.setInt(4, time);//定义第3个占位符的内容
                    pstmt.setString(5, chuban);
                    pstmt.setInt(6, store);
                    pstmt.setString(7, classes);
                    pstmt.addBatch();//加入批处理等待执行
                    i++;//学号唯一，循环继续往下执行
                }
            }
            pstmt.executeBatch();//批量执行插入操作
            JOptionPane.showMessageDialog(null, "sucess");
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
