package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.Control_Fun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.TerBiliLive.Ui.TerBiliLive_Control_Ui.*;


public class Control_Monitor {


    public Control_Monitor(){

        Control_Fun CF =new Control_Fun();


        Control_UiB_Preservation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                CF.Preservation();


            }
        });
        Control_UiB_Connect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                CF.Connect();


            }
        });
        Control_UiB_Disconnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                CF.Disconnect();

            }
        });




    }
}
