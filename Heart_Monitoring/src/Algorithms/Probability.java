/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.text.DecimalFormat;

/**
 *
 * @author JP
 */
public class Probability {

    public static String sysFinalResult = "";
    public static String dysFinalResult = "";
    public static String hpFinalResult = "";
    public static String chFinalResult = "";
    public static String ldlFinalResult = "";
    public static String hdlFinalResult = "";
    public static String stressFinalResult = "";
    public static String sugarFinalResult = "";
    public static String oxyFinalResult = "";
    public static String tempFinalResult = "";
    public static String hemoFinalResult = "";
    public static String prFinalResult = "";
    public static String qtFinalResult = "";

    public static String checkProbability(double data) {
        int a = 80, b = 120;
        int c = 130, d = 160;
        double prob = 0.0;

        if (data <= 120 && data >= 80) {
            prob = 0.0;
            sysFinalResult = "Normal";
        } else if (data > b && data <= c) {
            double prob1 = data - b;
            double prob2 = c - b;
            prob = prob1 / prob2;
            sysFinalResult = "Mild";
        }
        if (data > c && data < d) {
            double prob1 = d - data;
            double prob2 = d - c;
            prob = prob1 / prob2;
            sysFinalResult = "Moderate";
        }
        if (data < 80 || data > 140) {
            prob = 1.0;
            sysFinalResult = "Severe";
        }

        return sysFinalResult + "#" + prob;
    }

    public static String dyscheckProbability(int data) {
        int a = 60, b = 90;
        int c = 120, d = 160;
        double prob = 0.0;
        if (data <= 90 && data >= 60) {
            prob = 0.0;
            dysFinalResult = "Normal";
        } else if (data > b && data <= c) {
            double prob1 = data - b;
            double prob2 = c - b;
            prob = prob1 / prob2;
            dysFinalResult = "Mild";
        } else if (data > c && data < d) {
            double prob1 = d - data;
            double prob2 = d - c;
            prob = prob1 / prob2;
            dysFinalResult = "Moderate";
        } else {
            prob = 1.0;
            dysFinalResult = "Severe";
        }
        return dysFinalResult+"#"+prob;
    }

    public static String HPRatecheckProbability(int data) {
        int a = 60, b = 76;
        int c = 110, d = 160;
        double prob = 0.0;
        if (data >= a && data <= b) {
            prob = 0.0;
            hpFinalResult = "Normal";
        } else if (data > b && data < c) {
            double prob1 = data - b;
            double prob2 = c - b;
            prob = prob1 / prob2;
            hpFinalResult = "Mild";
        } else if (data > c && data < d) {
            double prob1 = d - data;
            double prob2 = d - c;
            prob = prob1 / prob2;
            hpFinalResult = "Moderate";
        } else {
            prob = 1.0;
            hpFinalResult = "Severe";
        }
        return hpFinalResult+"#"+prob;
    }

    public static String checkProbabilitytemp(int data) {
        int a = 36, b = 37;
        int c = 38, d = 39;
        double prob = 0.0;
        if (data >= a && data <= b) {
            prob = 0.0;
            tempFinalResult = "Normal";
        } else if (data > b && data <= c) {
            double prob1 = data - b;
            double prob2 = d - b;
            prob = prob1 / prob2;
            tempFinalResult = "Mild";
        } else if (data >c && data < d) {
            double prob1 = d - data;
            double prob2 = d - c;
            prob = prob1 / prob2;
            tempFinalResult = "Moderate";
        } else {
            prob = 1.0;
            tempFinalResult = "Severe";
        }

        return tempFinalResult+"#"+prob;
    }

    public static String oxyRatecheckProbability(int data) {
        int a = 70, b = 80;
        int c = 90, d = 100;
        double prob = 0.0;
      
        if (data >= c && data <= d) {
            prob = 0.0;
            oxyFinalResult = "Normal";
        } else if (data <= b && data >= a) {
            double prob1 = data - a;
            double prob2 = b - a;
            prob = prob1 / prob2;
            oxyFinalResult = "Mild";
        } else if (data > b && data <= c) {
            double prob1 = c - data;
            double prob2 = c - b;
            prob = prob1 / prob2;
            oxyFinalResult = "Moderate";
        } else {
            prob = 1.0;
            oxyFinalResult = "Severe";
        }
        return oxyFinalResult+"#"+prob;
    }

    public static String prRatecheckProbability(double data) {
        double a = 60, b = 80;
        double c = 100, d = 200;
        double prob = 0.0;
        if (data >= b && data <= c) {
            prob = 0.0;
            prFinalResult = "Normal";
        } else if (data > c && data <= d) {
            double prob1 = data - c;
            double prob2 = d- c;
            prob = prob1 / prob2;
            prFinalResult = "Mild";
        } else if (data < b) {
            double prob1 = b - data;
            double prob2 = b - a;
            prob = prob1 / prob2;
            prFinalResult = "Moderate";
        } else {
            prob = 1.0;
            prFinalResult = "Severe";
        }

        return prFinalResult+"#"+prob;
    }

    public static String qtRatecheckProbability(int data) {
        double a = 380, b = 390;
        double c = 450, d = 465;
        double prob = 0.0;

        if (data >= b && data <= c) {
            prob = 0.0;
            qtFinalResult = "Normal";
        }
        if (data >= a && data <= b) {
            double prob1 = data - a;
            double prob2 = b - a;
            prob = prob1 / prob2;
            qtFinalResult = "Mild";

        } else if (data > c && data <= d) {
            double prob1 = d - data;
            double prob2 = d - c;
            prob = prob1 / prob2;
            qtFinalResult = "Moderate";
        } else {
            prob = 1.0;
            qtFinalResult = "Severe";
        }

        return qtFinalResult+"#"+prob;
    }

    public static void main(String[] args) {

        DecimalFormat df2 = new DecimalFormat("#.##");
        double input = 7.1000000000000005;
        System.out.println("double : " + input);
        System.out.println("double : " + df2.format(input));    //3.14
        
    }

}
