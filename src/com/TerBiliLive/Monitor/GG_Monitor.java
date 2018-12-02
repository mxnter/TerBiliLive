package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.Control_Fun;
import com.TerBiliLive.Info.ConfInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.TerBiliLive.Ui.TerBiliLive_Adv_Ui.*;

public class GG_Monitor {


    public static  boolean AYO = true ;


    public GG_Monitor(){

        if(ConfInfo.control_fun ==null) ConfInfo.control_fun =new Control_Fun();

        GG_Ui_Start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub


                ConfInfo.control_fun.Start();

            }
        });
        GG_Ui_Suspend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // YJ.gdpd=false;
                ConfInfo.control_fun.Suspend();

            }
        });




    }
}
