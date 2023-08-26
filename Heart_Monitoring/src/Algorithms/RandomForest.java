/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import static Algorithms.Fuzzy.getMaxs;
import static Algorithms.ReportShow.createreport;
import static Algorithms.ReportShow.*;
import Master.Dbconn;
import static Master.Dbconn.*;
import static Master.Dbconn.*;
import static Master.Dbconn.pid;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RandomForest {

    public static int Hrate = 1;
    public static double qt = 1;
    
    public static int tempreture = 1;
  
    public static int oxy = 1;

    public static int normalcount = 0, Mildcount = 0, Moderatecount = 0, Severecount = 0;
    public static Map<String, Integer> step1 = new HashMap<>();

    public static String GenerateTrees(ArrayList Objarray,Connection con) {
        Dbconn.rule();
        int HrateNormal = 0, HrateMild = 0, HrateModerate = 0, HrateSevere = 0;
        int tempNormal = 0, tempMild = 0, tempModerate = 0, tempSevere = 0;
        double qtnormal = 0, qtabnormal = 0;
      
        int tot = 30;
        int OxyNormal = 0;
        int OxyRN = 0, OxyRBN = 0, oxyPH = 0;
        StringBuilder sb = new StringBuilder();
        for (int StartVal = 0; StartVal < Objarray.size(); StartVal++) {

            String[] Parts = Objarray.get(StartVal).toString().split(",");
           Hrate = Integer.parseInt(Parts[0]);
            qt = Integer.parseInt(Parts[1]);
           
            oxy = Integer.parseInt(Parts[2]);
            tempreture = Integer.parseInt(Parts[3]);
            String id = Parts[4];
            String T_id=Parts[5];

            
           
            //
            if (Hrate >= 60 && Hrate <= 70) {
                HrateNormal = HrateNormal + 1;
            }
            if (Hrate > 70 && Hrate <= 80) {
                HrateMild = HrateMild + 1;
            }
            if (Hrate < 40 && Hrate < 60) {
               HrateModerate = HrateModerate + 1;
            }
            if (Hrate < 60 || Hrate > 80) {
               HrateSevere = HrateSevere + 1;
            }
            //

            if (qt >= 390 && qt <= 450) {
                qtnormal = qtnormal + 1;
            }
            if (qt > 450 || qt < 390) {
               qtabnormal = qtabnormal + 1;
            }
           
//
            if (tempreture == 36 || tempreture == 37) {
                tempNormal = tempNormal + 1;
            }
            if (tempreture >= 35 && tempreture <= 37) {
               tempMild = tempMild + 1;
            }

            if (tempreture < 35) {
               tempModerate = tempModerate + 1;
            }
            if (tempreture > 37) {
                tempSevere = tempSevere + 1;
            }
            if (oxy <= 100 && oxy >= 95) {
                OxyNormal = OxyNormal + 1;
            }
            if (oxy < 95 && oxy >= 90) {
               OxyRN = OxyRN + 1;
            }
            if (oxy >= 70 && oxy < 90) {
                OxyRBN = OxyRBN + 1;
            }
            if (oxy < 90 || oxy > 100) {
                oxyPH = oxyPH + 1;
            }
           

            ///////////////////////////////////////////   
            tot = tot + 1;

            

            //--------------------------------------------------
            double HPRate[] = new double[]{HrateNormal, HrateMild, HrateModerate, HrateSevere};
            double HPMax = Calculate_Belief(HPRate);
           if (HPMax == HrateNormal) {
                hpFinalResult = "Normal";
            }
            if (HPMax == HrateModerate) {
                hpFinalResult = "Mild";
            }
            if (HPMax == HrateMild) {
                hpFinalResult = "Moderate";
            }
            if (HPMax == HrateSevere) {
                hpFinalResult = "Severe";
            }

            
            //--------------------------------------------------

            double qtRate[] = new double[]{qtnormal, qtabnormal};
            double qtMax = Calculate_Belief(qtRate);
            //String qtFinalResult = "";
            if (qtMax == qtnormal) {
                qtFinalResult = "Normal";
            }
            if (qtMax == qtabnormal) {
                qtFinalResult = "Abnormal";
            }
            //--------------------------------------------------
            int tempp[] = new int[]{tempNormal, tempMild, tempModerate, tempSevere};
            int tempmax = getMaxs(tempp);

            if (tempmax == tempNormal) {
                tempFinalResult = "Normal";
            }
            if (tempmax == tempMild) {
                tempFinalResult = "Mild";
            }
            if (tempmax == tempModerate) {
                tempFinalResult = "Moderate";
            }
            if (tempmax == tempSevere) {
                tempFinalResult = "Severe";
            }
            
            
            //--------------------------------------------------
           
            int oxyRate[] = new int[]{OxyNormal, OxyRN,OxyRBN,oxyPH};
            int oxyMax = getMax(oxyRate);
            // String oxyFinalResult = "";
            if (oxyMax == OxyNormal) {
                oxyFinalResult = "Normal";
            }
            if (oxyMax == OxyRN) {
                oxyFinalResult = "Mild";
            }
            if (oxyMax == OxyRBN) {
                oxyFinalResult = "Moderate";
            }
            if (oxyMax == oxyPH) {
                oxyFinalResult = "Severe";
            }
           
            String Final_Rule = sysFinalResult + "," + dysFinalResult + "," + hpFinalResult + ","  + oxyFinalResult + "," + prFinalResult + "," + qtFinalResult;
            String test = result(Final_Rule);
            String[] score = test.split("#");

            System.out.println("*****RF*****");
            System.out.println("Score=>" + score[1] + "Final Test=>" + Dbconn.rffinalTest);
         
            try {
             
            Statement st=con.createStatement();
            st.executeUpdate("update sensor_data set RF_label='"+Dbconn.rffinalTest+"' where pid='"+id+"' and id='"+T_id+"'");
         } catch (Exception ex) {
                System.out.println(ex);
            }
          }
      

        return Dbconn.rffinalTest;
    }

    // Function for CALCULATE BELIEF
    public static double Calculate_Belief(double[] inputArray) {
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            list.add(inputArray[i]);
        }
        return (double) Collections.max(list);
    }

    public static String result(String Final_Rule) {
        String count = null;
        String[] F_Rule = Final_Rule.split(",");
        normalcount = 0;
        Mildcount = 0;
        Moderatecount = 0;
        Severecount = 0;
        for (String msg : F_Rule) {

            if (msg.equals("Normal") || msg.equals("Below-Normal")) {
                normalcount++;
            } else if (msg.equals("Mild")) {
                Mildcount++;
            } else if (msg.equals("Moderate") || msg.equals("Moderately-Low") || msg.equals("High")) {
                Moderatecount++;
            } else if (msg.equals("Severe") || msg.equals("Critically-Low") || msg.equals("Hypoxemia") || msg.equals("dangerous") || msg.equals("Worst") || msg.equals("VerySevere") || msg.equals("critical")) {
                Severecount++;
            }

        }
        // count = normalcount + "," + Mildcount + "," + Moderatecount + "," + Severecount;
        //step1.put("Normal", normalcount);
        //step1.put("Critical", Severecount);
        //System.out.println("Count=>" + count);
        String finaltest = "";
        int normal = normalcount + Mildcount;
        int critical = Moderatecount + Severecount;
        int fcount[] = new int[]{normal, critical};
        int maxcount = getMax(fcount);
        if (maxcount == normal) {
            Dbconn.rffinalTest = "Normal";
        } else {
            Dbconn.rffinalTest = "Critical";
        }
        return String.valueOf(finaltest + "#" + maxcount);
    }

    public static String result1(String Final_Rule) {
        String count = null;
        String[] F_Rule = Final_Rule.split(",");
        for (String msg : F_Rule) {

            if (msg.equals("Normal") || msg.equals("Below-Normal")) {
                normalcount++;
            } else if (msg.equals("Mild")) {
                Mildcount++;
            } else if (msg.equals("Moderate") || msg.equals("Moderately-Low") || msg.equals("Below-Normal") || msg.equals("High")) {
                Moderatecount++;
            } else if (msg.equals("Severe") || msg.equals("Critically-Low") || msg.equals("Hypoxemia") || msg.equals("dangerous") || msg.equals("Worst") || msg.equals("VerySevere") || msg.equals("critical")) {
                Severecount++;
            }

        }
        count = normalcount + "," + Mildcount + "," + Moderatecount + "," + Severecount;
        step1.put("Normal", normalcount);
//        step1.put("Mild", Mildcount);
//        step1.put("Moderate", Moderatecount);
        step1.put("Critical", Severecount);
        System.out.println("Count=>" + count);
        int fcount[] = new int[]{normalcount, Mildcount, Moderatecount, Severecount};
        int maxcount = getMax(fcount);
        return String.valueOf(maxcount);
    }

    public static int getMax(int[] inputArray) {
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
}
