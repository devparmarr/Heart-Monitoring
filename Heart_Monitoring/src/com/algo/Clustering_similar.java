/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algo;

import Master.Dbconn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Clustering_similar {

    public static ArrayList HiddenLayet1 = new ArrayList();
    public static ArrayList outlist = new ArrayList();
    public static double HThreshold = 0.20;
    public static ArrayList Flist = new ArrayList();

    public static ArrayList BuildClustering_similar() {

        ArrayList Input = new ArrayList();

        Input = GetInput();

        String id;

        String val3;
        String val4;
        String val5;
        String val6;
        String val7;
        try {
           
            int size = Input.size();
            System.out.println("S" + size);
            for (int testneuron = 0; testneuron < Input.size(); testneuron++) {
                String[] inputneurons = Input.get(testneuron).toString().split(",");
                id = inputneurons[0];

                val3 = inputneurons[1];
                val4 = inputneurons[2];
                val5 = inputneurons[3];
                val6 = inputneurons[4];
                val7 = inputneurons[5];
                int flag = 0;
                String classlabel = "";
              
                int v3 = Rule_Data.Hrate_process(Integer.valueOf(val3));
                int v4 = Rule_Data.qt_process(Integer.valueOf(val4));
                int v5 = Rule_Data.pr_process(Integer.valueOf(val5));
                int v6 = Rule_Data.oxy_process(Integer.valueOf(val6));
                int v7 = Rule_Data.tempreture_process(Integer.valueOf(val7));
                String values = v3 + "," + v4 + "," + v5 + "," + v6 + "," + v7;
                System.out.println("Values=>" + values);
                int tempMax = getsimilar(values, size);
                if (tempMax == 0) {
                    classlabel = "Critical";
                    System.out.println("ID=>" + id + "Label=>" + classlabel);
                } else {
                    classlabel = "Normal";
                    System.out.println("ID=>" + id + "Label=>" + classlabel);
                }
                Connection con;
                try {
                    con = Dbconn.conn();

                    Statement st = con.createStatement();
                    st.executeUpdate("update sensor_data set Actualclass='" + classlabel + "' where id='" + id + "'");
                } catch (Exception ex) {

                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return outlist;
    }

    public static int getsimilar(String inputArray, int size) {
        String[] val = inputArray.split(",");
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxValue = 0;
        int zero = 0, one = 1;
        for (int i = 0; i < val.length; i++) {
            if (val[i].equals("0")) {
                zero++;
            } else {
                one++;
            }

        }
        int max = 0;
        map.put(0, zero);
        map.put(1, one);
        int maxValueInMap = (Collections.max(map.values()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                max = entry.getKey();
            }
        }
//         System.out.println("Max=>"+max);

        if (max == 0) {
            maxValue = 0;
        } else {
            maxValue = 1;
        }
        return maxValue;
    }

    public static int getequals(String param1, String param2) {
        if (param1 == param2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int getdiffer(String param1, String param2) {
        if (Integer.parseInt(param1) > Integer.parseInt(param2)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static ArrayList GetInput() {
        ArrayList Testset = new ArrayList();
        try {

            Dbconn db = new Dbconn();
            Connection con = null;
            con = db.conn();
            Statement stat = con.createStatement();
            stat.executeQuery("select * from sensor_data");
            ResultSet rs = null;
            rs = stat.getResultSet();
            while (rs.next()) {
                String ss = rs.getString(1)+","+rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(8);
                Testset.add(ss);
            }
        } catch (Exception ex) {

        }
        return Testset;
    }

    public static ArrayList GetHiddenLayer() {
        ArrayList Trainset = new ArrayList();
        try {

            Dbconn db = new Dbconn();
            Connection con = null;
            con = db.conn();
            Statement stat = con.createStatement();
            stat.executeQuery("select * from bsn_sensor_data");
            ResultSet rs = null;
            rs = stat.getResultSet();
            while (rs.next()) {
                String ss = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7);
                Trainset.add(ss);
            }
        } catch (Exception ex) {

        }
        return Trainset;
    }
}
