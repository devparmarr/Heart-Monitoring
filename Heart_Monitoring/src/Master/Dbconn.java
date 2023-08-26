package Master;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

public class Dbconn {

    public static int cflag = 0;
    public static String reportfile = "C:\\Users\\acer\\OneDrive\\Desktop\\Heart_Monitoring_BSN\\Report\\";
    public static String fuzzyfinalTest = null;
    public static String qlfinalTest = null;
    public static String rffinalTest = null;
    public static String drfinalTest = null;
    // public static String reportfile="C:\\";
    public static ArrayList<Integer> syslist = new ArrayList<>();
    public static ArrayList<Integer> dyslist = new ArrayList<>();
    public static ArrayList<Integer> hlist = new ArrayList<>();
    public static ArrayList<Integer> qtlist = new ArrayList<>();
    public static ArrayList<Integer> prlist = new ArrayList<>();
    public static ArrayList<Integer> templist = new ArrayList<>();
    public static ArrayList<Integer> oplist = new ArrayList<>();
    public static  String Heart_Rate = null,tempreture = null,QT_Interval = null,PR_Interval = null,Oxygen_saturation = null;
    public static  String Heart_Ratev = null,QT_Intervalv = null,PR_Intervalv = null,Oxygen_saturationv = null;
    public static int id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12,id13,id14,id15;
    public static String pid=null,pname=null,gender=null,emailid=null,mobile=null,address=null,dob=null;
   

    public static void rule() {
        syslist.add(80);
        syslist.add(120);
        syslist.add(130);
        syslist.add(160);
        //dys
        dyslist.add(60);
        dyslist.add(80);
        dyslist.add(90);
        dyslist.add(120);
        dyslist.add(160);
        //hr
        hlist.add(30);
        hlist.add(40);
        hlist.add(60);
        hlist.add(110);
        hlist.add(120);
        //QT
        qtlist.add(330);
        qtlist.add(430);
        //Pr
        prlist.add(120);
        prlist.add(200);
        //Temp
        templist.add(24);
        templist.add(35);
        templist.add(36);
        templist.add(37);
        templist.add(38);
        templist.add(40);
        templist.add(44);
//oxy
        oplist.add(72);
        oplist.add(110);
        oplist.add(180);
    }

    public Dbconn() throws SQLException {
        //initComponents();
        //Connection con;

    }

    public static Connection conn1() throws Exception {
       Connection con;
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://182.50.133.80:3306/academyprojectmw", "mayuriwagh", "T5j@h6k9");
        
        return (con);

    }
    public static Connection conn() throws Exception {
       Connection con;
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/23_cs_806_heart_disease_iot", "root", "Dev@2104");
        
        return (con);

    }

    public static void main(String args[]) {
       HashMap<Integer,Integer>map=new HashMap<Integer, Integer>();
        map.put(0, 50);
        map.put(1, 60);
        
        int maxValueInMap=(Collections.max(map.values()));  // This will return max value in the Hashmap
        for (Entry<Integer, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
            }
        }
    }

}
