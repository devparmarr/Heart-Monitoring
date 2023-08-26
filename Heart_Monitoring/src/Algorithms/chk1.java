/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Analysis.Graph;
import Analysis.Graph1;
import Analysis.Graph2;
import Analysis.Graph4;
import Master.Dbconn;
import static Master.Dbconn.pid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class chk1 {

    public static double fuzzyTP = 0.0;
    public static double fuzzyFN = 0.0;
    public static double fuzzyFP = 0.0;
    public static double fuzzyTN = 0.0;

    

    public static double QLearningTP = 0.0;
    public static double QLearningFN = 0.0;
    public static double QLearningFP = 0.0;
    public static double QLearningTN = 0.0;

    public static double RFTP = 0.0;
    public static double RFFN = 0.0;
    public static double RFFP = 0.0;
    public static double RFTN = 0.0;

    public static double TotalA = 0;
    public static double TotalB = 0;
    public static double Totalrecord = 0;
    //public static int pid = 1;

    // public static int normal=(int) (Math.random() * (20 - 1) + 1);
    public void br() throws IOException {
        try {

            Master.Dbconn db = new Dbconn();
            Connection con = db.conn();
            Statement st = con.createStatement();
            st.executeQuery("select * from sensor_data");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Totalrecord++;
                String Acutal = rs.getString("Actualclass");
                String fuzzy = rs.getString("Fuzzy_label");
                String RF = rs.getString("RF_label");
                String QLearning = rs.getString("Qlearning");
               

                if (Acutal.equalsIgnoreCase("Normal")) {
                    TotalA = TotalA + 1;
                    if (fuzzy.equalsIgnoreCase("Normal")) {
                        fuzzyTP = fuzzyTP + 1;
                    } else if (fuzzy.equalsIgnoreCase("Critical") || fuzzy.isEmpty()) {
                        fuzzyFN = fuzzyFN + 1;
                    }

                    if (RF.equalsIgnoreCase("Normal")) {
                        RFTP = RFTP + 1;
                    } else if (RF.equalsIgnoreCase("Critical") || RF.isEmpty()) {
                        RFFN = RFFN + 1;
                    }

                    if (QLearning.equalsIgnoreCase("Normal")) {
                        QLearningTP = QLearningTP + 1;
                    } else if (QLearning.equalsIgnoreCase("Critical") || QLearning.isEmpty()) {
                        QLearningFN = QLearningFN + 1;
                    }
                    
                } else if (Acutal.equalsIgnoreCase("Critical")) {
                    TotalB = TotalB + 1;

                    if (fuzzy.equalsIgnoreCase("Critical")) {
                        fuzzyTN++;
                    } else if (fuzzy.equalsIgnoreCase("Normal") || fuzzy.isEmpty()) {
                        fuzzyFP++;
                    }
                    if (RF.equalsIgnoreCase("Critical")) {
                        RFTN++;
                    } else if (RF.equalsIgnoreCase("Normal") || RF.isEmpty()) {
                        RFFP++;
                    }
                    if (QLearning.equalsIgnoreCase("Critical")) {
                        QLearningTN++;
                    } else if (QLearning.equalsIgnoreCase("Normal") || QLearning.isEmpty()) {
                        QLearningFP++;
                    }
                    
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("***************");
        System.out.println("Total Positive" + Totalrecord);
        System.out.println("Total Negative" + TotalB);
        //   System.out.println("Total Neutral" + TotalC);

        System.out.println("fuzzy TP \t" + fuzzyTP);
        System.out.println("fuzzy FN \t" + fuzzyFN);
        System.out.println("fuzzy TN \t" + fuzzyTN);
        System.out.println("fuzzy FP \t" + fuzzyFP);
        System.out.println("***************");
        System.out.println("RF TP \t" + RFTP);
        System.out.println("RF FN \t" + RFFN);
        System.out.println("RF TN \t" + RFTN);
        System.out.println("RF FP \t" + RFFP);
        System.out.println("***************");
        System.out.println("QLearning TP \t" + QLearningTP);
        System.out.println("QLearning FN \t" + QLearningFN);
        System.out.println("QLearning TN\t" + QLearningTN);
        System.out.println("QLearning FP \t" + QLearningFP);
       
        System.out.println("***************");

        // fuzzy
        double fuzzyacc1 = (double) fuzzyTP + fuzzyTN;
        double fuzzacc2 = (double) (fuzzyacc1 / Totalrecord);
        System.out.println("fuzzy \t" + fuzzacc2);
        double fTP = fuzzyTP + fuzzyFP;
        double fnTP = fuzzyTP + fuzzyFN;
        double fuzzyPrecision = fuzzyTP / fTP;
        double fuzzyRecall = fuzzyTP / fnTP;
        double f = fuzzyPrecision * fuzzyRecall;
        double f1 = fuzzyPrecision + fuzzyRecall;
        double f2 = (f / f1);
        double fuzzyF1 = 2 * f2;

        Graph2.fpre1 = fuzzacc2;
        Graph2.fpre2 = fuzzyPrecision;
        Graph2.fpre3 = fuzzyRecall;
        Graph2.fpre4 = fuzzyF1;
        System.out.println("Fuzzy Precision=>" + fuzzyPrecision + "\tFuzzy Recall=>" + fuzzyRecall + " F1 Score=>" + fuzzyF1);
        System.out.println("***************");
        //RF
        double rfacc1 = (double) RFTP + RFTN;
        double rfacc2 = (double) rfacc1 / Totalrecord;
        System.out.println("RF \t" + rfacc2);
        double rfTP = RFTP + RFFP;
        double rfnTP = RFTP + RFFN;
        double rfPrecision = RFTP / rfTP;
        double rfRecall = RFTP / rfnTP;
        double rf = rfPrecision * rfRecall;
        double rf1 = rfPrecision + rfRecall;
        double rf2 = (rf / rf1);
        double rfF1 = 2 * rf2;

        Graph1.rfpre1 = rfacc2;
        Graph1.rfpre2 = rfPrecision;
        Graph1.rfpre3 = rfRecall;
        Graph1.rfpre4 = rfF1;
        System.out.println("Rf Precision=>" + rfPrecision + "\t Rf Recall=>" + rfRecall + " F1 Score=>" + rfF1);
        System.out.println("***************");
//QLearning
        double qlacc1 = (double) QLearningTP + QLearningTN;
        double qlacc2 = (double) qlacc1 / Totalrecord;
        System.out.println("QLearning \t" + qlacc2);
        double qTP = QLearningTP + QLearningFP;
        double qPrecision = QLearningTP / qTP;

        double qfTP = QLearningTP + QLearningFN;
        double qRecall = QLearningTP / qfTP;

        double qf = qPrecision * qRecall;
        double qf1 = qPrecision + qRecall;
        double qf2 = (qf / qf1);
        double qfF1 = 2 * qf2;

        Graph.pre1 = qlacc2;
        Graph.pre2 = qPrecision;
        Graph.pre3 = qRecall;
        Graph.pre4 = qfF1;
        System.out.println("QLearning Precision=>" + qPrecision + "\t QLearning Recall=>" + qRecall + " F1 Score=>" + qfF1);
        System.out.println("************************");

        
    }

    public static void main(String a[]) throws IOException {
        chk1 c = new chk1();
        c.br();
//String s = "1990-12-20";
//  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//  Date d;
//        try {
//            d = sdf.parse(s);
//        
//  Calendar c = Calendar.getInstance();
//  c.setTime(d);
//  int year = c.get(Calendar.YEAR);
//  int month = c.get(Calendar.MONTH) + 1;
//  int date = c.get(Calendar.DATE);
//  LocalDate l1 = LocalDate.of(year, month, date);
//  LocalDate now1 = LocalDate.now();
//  Period diff1 = Period.between(l1, now1);
//  System.out.println("age:" + diff1.getYears() + "years");
//  } catch (ParseException ex) {
//            Logger.getLogger(chk1.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
