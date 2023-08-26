package Algorithms;

import Master.Dbconn;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Fuzzy {

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    public static int Hrate = 1;
    public static int qt = 1;
    public static int pr = 1;
    public static int tempreture = 1;
    public static int Sysbp = 1;
    public static int dysbp = 1;
    public static int oxy = 1;

    
    public static double hpprobability = 0.0;
    public static double qtporbability = 0.0;
    public static double prprobability = 0.0;
    public static double tempprobability = 0.0;
    public static double oxyprobability = 0.0;

    public static String GenerateTrees(ArrayList Objarray,Connection con) {

        int tot = 0;

       
        for (int StartVal = 0; StartVal < Objarray.size(); StartVal++) {
           
            hpprobability = 0.0;
            qtporbability = 0.0;
            prprobability = 0.0;
            tempprobability = 0.0;
            oxyprobability = 0.0;
            String[] Parts = Objarray.get(StartVal).toString().split(",");

            Hrate = Integer.parseInt(Parts[0]);
            qt = Integer.parseInt(Parts[1]);
//            pr = Integer.parseInt(Parts[2]);
            oxy = Integer.parseInt(Parts[2]);
            tempreture = Integer.parseInt(Parts[3]);
            String id = Parts[4];
            String T_id=Parts[5];

           
            // --------------------- Heart Rate --------------------------------------
            String heartprob = Probability.HPRatecheckProbability(Hrate);
            String[] hp = heartprob.split("#");
            double hpp = Double.parseDouble(hp[1]);
            hpprobability = hpprobability + hpp;

            // --------------------- QT --------------------------------------
            String qtprob = Probability.qtRatecheckProbability(qt);
            String[] qtecg = qtprob.split("#");
            double qtp = Double.parseDouble(qtecg[1]);
            qtporbability = qtporbability + qtp;

            // --------------------- PR --------------------------------------
//            String prprob = Probability.prRatecheckProbability(pr);
//            String[] precg = prprob.split("#");
//            double prp = Double.parseDouble(precg[1]);
//            prprobability = prprobability + prp;

            // --------------------- Tempreture --------------------------------------
            String tempprob = Probability.checkProbabilitytemp(tempreture);
            String[] temps = tempprob.split("#");
            double temp = Double.parseDouble(temps[1]);
            tempprobability = tempprobability + temp;

            // OXY
            String oxyprob = Probability.oxyRatecheckProbability(oxy);
            String[] oxys = oxyprob.split("#");
            double oxyp = Double.parseDouble(oxys[1]);
            oxyprobability = oxyprobability + oxyp;

            tot = tot + 1;


            String s13 = df2.format(hpprobability);
            double nhpprobability = (double) (Double.parseDouble(s13));// / 100);

            String s14 = df2.format(qtporbability);
            double nqtporbability = (double) (Double.parseDouble(s14));// / 100);

           

            String s16 = df2.format(tempprobability);
            double ntempprobability = (double) (Double.parseDouble(s16));// / 100);

            String s17 = df2.format(oxyprobability);
            double noxyprobability = (double) (Double.parseDouble(s17));// / 100);

            int Ncount = 0;
            int Dcount = 0;
            int Fxcount = 5;
            double Th1 = 0.60;
            double w1 = 0;
            double w = 0;
           
            System.out.println("*****Fuzzy*****");
            w1 =  nhpprobability + nqtporbability +  ntempprobability + noxyprobability;//(double) Ncount / Fxcount;
            w = ((double) (w1 / Fxcount));
           if (w >= Th1)//firs threshold
            {
                Dbconn.fuzzyfinalTest = "Critical";
                System.out.println(id+"Score=>" + w + "Final Test=>" + Dbconn.fuzzyfinalTest);
            
            } else {
                Dbconn.fuzzyfinalTest = "Normal";
                System.out.println(id+"Score=>" + w + "Final Test=>" + Dbconn.fuzzyfinalTest);
            }
//            Connection con;
            try {
               

                Statement st = con.createStatement();
                st.executeUpdate("update sensor_data set Fuzzy_label='" + Dbconn.fuzzyfinalTest + "' where pid='"+id+"' and id='"+T_id+"'");
            } catch (Exception ex) {
System.out.println(ex);
            }
        }
        return Dbconn.fuzzyfinalTest;
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

    public static int getMaxs(int[] inputArray) {
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

}
