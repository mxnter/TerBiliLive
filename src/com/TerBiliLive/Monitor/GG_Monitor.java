package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Thr.DMJ_Thr;
import com.TerBiliLive.Thr.GG_Thr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.TerBiliLive.Ui.TerBiliLive_GG_Ui.*;

public class GG_Monitor {


    public static  boolean AYO = true ;


    public GG_Monitor(){



        GG_Ui_Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                Control_Fun CF =new Control_Fun();
                CF.Start();

            }
        });
        GG_Ui_Suspend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // YJ.gdpd=false;
                Control_Fun CF =new Control_Fun();
                CF.Suspend();

            }
        });




    }
}
