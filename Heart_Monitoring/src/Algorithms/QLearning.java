package Algorithms;

import static Algorithms.Fuzzy.getMaxs;
import Master.Dbconn;
import static Master.Dbconn.*;
import static Master.Dbconn.emailid;
import static Master.Dbconn.pid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class QLearning {

    public static String Heart_Rate = null, QT_Interval = null, PR_Interval = null, Oxygen_saturation = null;
    public static int Hrate = 1;
    public static double qt = 1;
   
    public static int tempreture = 1;

    public static int oxy = 1;
    public static String oxyFinalResult = "";
    public static String tempFinalResult = "";

    public static String ProcessData(ArrayList Objarray, Connection con) {
        int HrateNormal = 0, HrateMild = 0, HrateModerate = 0, HrateSevere = 0;
        int OxyNormal = 0, oxyBelowNormal = 0, oxyHypoxemia = 0, oxyHypoxemiaservere = 0;
        int tempNormal = 0, tempMild = 0, tempModerate = 0, tempSevere = 0;
        double qtnormal = 0, qtabnormal = 0;
        double prnormal = 0, prabnormal = 0;

        int tot = 30;
        Dbconn.rule();
        StringBuilder sb = new StringBuilder();
        for (int StartVal = 0; StartVal < Objarray.size(); StartVal++) {
            String[] Parts = Objarray.get(StartVal).toString().split(",");
            Hrate = Integer.parseInt(Parts[0]);
            qt = Integer.parseInt(Parts[1]);
//            pr = Integer.parseInt(Parts[2]);
            oxy = Integer.parseInt(Parts[2]);
            tempreture = Integer.parseInt(Parts[3]);
            String id = Parts[4];
            String T_id=Parts[5];
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
                oxyBelowNormal = oxyBelowNormal + 1;
            }
            if (oxy >= 70 && oxy < 90) {
                oxyHypoxemia = oxyHypoxemia + 1;
            }
            if (oxy < 90 || oxy > 100) {
                oxyHypoxemiaservere = oxyHypoxemiaservere + 1;
            }
            //---------------------------------------------------------------------------
            tot = tot + 1;

            int Reward = 0;
            int Penalty = 0;
            int Fxcount = 4;

            double Th2 = 0.60;

            //--------------------------------------------------
            double HrateNormal1 = (double) (Double.valueOf(HrateNormal) / tot);
            double HrateMildw2 = (double) (Double.valueOf(HrateMild) / tot);
            double HrateMildw3 = (double) (Double.valueOf(HrateModerate) / tot);
            double HrateMildw4 = (double) (Double.valueOf(HrateSevere) / tot);
            double HPRate[] = new double[]{HrateNormal1, HrateMildw2, HrateMildw3, HrateMildw4};
            double HPMax = getMax(HPRate);
            String hpFinalResult = "";
            if (HrateNormal1 == HPMax) {
                hpFinalResult = "Normal";
                Reward = Reward + 1;
            }
            if (HrateMildw2 == HPMax) {
                hpFinalResult = "Mild";
                Reward = Reward + 1;
            }
            if (HrateMildw3 == HPMax) {
                hpFinalResult = "Moderate";
                Penalty = Penalty + 1;
            }
            if (HrateMildw4 == HPMax) {
                hpFinalResult = "Severe";
                Penalty = Penalty + 1;
            }

            //--------------------------------------------------
            double oxyw1 = (double) (Double.valueOf(OxyNormal) / tot);
            double oxyw2 = (double) (Double.valueOf(oxyBelowNormal) / tot);
            double oxyw3 = (double) (Double.valueOf(oxyHypoxemia) / tot);
            double oxyw4 = (double) (Double.valueOf(oxyHypoxemiaservere) / tot);
            double oxyRate[] = new double[]{oxyw1, oxyw2, oxyw3, oxyw4};
            double oxyMax = getMax(oxyRate);
            String oxyFinalResult = "";
            if (oxyw1 == oxyMax) {
                oxyFinalResult = "Normal";
                Reward = Reward + 1;
            }
            if (oxyw2 == oxyMax) {
                oxyFinalResult = "Below-Normal";
                Reward = Reward + 1;
            }
            if (oxyw3 == oxyMax) {
                oxyFinalResult = "Hypoxemia";
                Penalty = Penalty + 1;
            }
            if (oxyw4 == oxyMax) {
                oxyFinalResult = "Servere";
                Penalty = Penalty + 1;
            }

            //--------------------------------------------------
           
            //--------------------------------------------------
            double qtw1 = (double) (qtnormal / tot);
            double qtw2 = (double) (qtabnormal / tot);
            double qtRate[] = new double[]{qtw1, qtw2};
            double qtMax = getMax(qtRate);
            String qtFinalResult = "";
            if (qtw1 == qtMax) {
                qtFinalResult = "Normal";
                Reward = Reward + 1;
            }
            if (qtw2 == qtMax) {
                qtFinalResult = "Abnormal";
                Penalty = Penalty + 1;
            }
            //--------------------------------------------------
            double tempNormalw1 = (double) (Double.valueOf(tempNormal) / tot);
            double tempNormalw2 = (double) (Double.valueOf(tempMild) / tot);
            double tempNormalw3 = (double) (Double.valueOf(tempModerate) / tot);
            double tempNormalw4 = (double) (Double.valueOf(tempSevere) / tot);
            double tempvalues[] = new double[]{tempNormalw1, tempNormalw2, tempNormalw3, tempNormalw4};
            double tempMax = getMax(tempvalues);
            String tempFinalResult = "";
            if (tempNormalw1 == tempMax) {
                tempFinalResult = "Normal";
                Reward = Reward + 1;
            }
            if (tempNormalw2 == tempMax) {
                tempFinalResult = "Mild";
                Reward = Reward + 1;
            }
            if (tempNormalw3 == tempMax) {
                tempFinalResult = "Moderate";
                Penalty = Penalty + 1;
            }
            if (tempNormalw4 == tempMax) {
                tempFinalResult = "Severe";
                Penalty = Penalty + 1;

            }

            System.out.println("Penalty" + Penalty + "Fxcount" + Fxcount);
            double currentState = (double) Penalty / Fxcount;
            System.out.println("*****QLearning*****");
            String finalTest = "";

            if (currentState >= Th2) {
                finalTest = "Critical";
                Dbconn.qlfinalTest = finalTest;
                System.out.println("Score=>" + currentState + "Final Test=>" + finalTest);
                } else {
                finalTest = "Normal";
                Dbconn.qlfinalTest = finalTest;
                System.out.println("Score=>" + currentState + "Final Test=>" + finalTest);
                 }

            try {

                Statement st = con.createStatement();
                st.executeUpdate("update sensor_data set Qlearning='" + Dbconn.qlfinalTest + "' where pid='"+id+"' and id='"+T_id+"'");
            } catch (Exception ex) {

            }
        }
        return Dbconn.qlfinalTest;
    }

    // Function for sorting the weight
    public static double getMax(double[] inputArray) {
        double maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

}
