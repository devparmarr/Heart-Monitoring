/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algo;

import static Master.Dbconn.*;

/**
 *
 * @author admin
 */
public class Rule_Data {

    public static int sys_bp(int Sysbp) {
        int sys = 0;
        if (Sysbp <= 120 && Sysbp >= 80) {
            sys = 1;
        } else if (Sysbp > 120 && Sysbp <= 130) {
            sys = 1;
        } else if (Sysbp > 130 && Sysbp < 150) {
            sys = 0;
        } else if (Sysbp < 80 || Sysbp > 150) {
            sys = 0;
        }
        return sys;

    }

    //
    public static int dys_bp(int dysbp) {
        int dys = 0;
        if (dysbp <= 90 && dysbp >= 80) {
            dys = 1;
        }
      else  if (dysbp > 90 && dysbp <= 120) {
            dys = 1;
        }
        if (dysbp > 120 && dysbp < 150) {
            dys = 0;
        }
        if (dysbp < 80 && dysbp > 150) {
            dys =0;
        }
        return dys;

    }

    ///
    public static int Hrate_process(int Hrate) {
        int Hrate_values = 0;
         if (Hrate >=60 && Hrate <= 70) {
                Hrate_values= 1;
            }
           else if (Hrate > 70 && Hrate <= 80) {
                Hrate_values= 1;
            }
          else  if (Hrate < 40 && Hrate <60) {
                Hrate_values= 0;
            }
          else  if (Hrate < 60 || Hrate > 80) {
                Hrate_values=0;
            }
        return Hrate_values;

    }

    ////
    public static int qt_process(int qt) {
        int qt_values = 0;
        if (qt >= 390 && qt <= 450) {
                qt_values = 1;
            }
            if (qt > 450 || qt < 390) {
                qt_values = 0;
            }
        return qt_values;

    }
///

    public static int pr_process(int pr) {
        int Pr = 0;
       if (pr <= 200 && pr >= 80) {
                Pr=1;
            }
            if (pr > 200 || pr < 80) {
                Pr=0;
            }
        return Pr;

    }

    ///
    public static int tempreture_process(int tempreture) {
        int temp = 0;
         if (tempreture == 36 || tempreture == 37) {
                temp=1;
            }
            if (tempreture >= 35 && tempreture <= 37) {
               temp=1;
            }

            if (tempreture < 35) {
                temp=0;
            }
            if (tempreture >37) {
                temp=0;
            }
        return temp;

    }

    ///
    public static int oxy_process(int oxy)
    {
        int oxy_values = 0;
        if (oxy <= 100 && oxy >= 95) {
                oxy_values=1;
            }
            if (oxy < 95 && oxy >= 90) {
                oxy_values= 1;
            }
            if (oxy >= 70 && oxy <90) {
               oxy_values=0;
            }
            if (oxy < 90 || oxy > 100) {
                oxy_values=0;
            }
        return oxy_values;

    }
}
