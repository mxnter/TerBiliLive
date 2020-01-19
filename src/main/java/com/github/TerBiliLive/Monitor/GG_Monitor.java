package com.github.TerBiliLive.Monitor;

import com.github.TerBiliLive.Function.Control_Fun;
import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.Ui.TerBiliLive_Adv_Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GG_Monitor {


    public static  boolean AYO = true ;


    public GG_Monitor(){

        if(ConfInfo.control_fun ==null) ConfInfo.control_fun =new Control_Fun();

        TerBiliLive_Adv_Ui.GG_Ui_Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub


                ConfInfo.control_fun.Start();

            }
        });
        TerBiliLive_Adv_Ui.GG_Ui_Suspend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // YJ.gdpd=false;
                ConfInfo.control_fun.Suspend();

            }
        });




    }
}
