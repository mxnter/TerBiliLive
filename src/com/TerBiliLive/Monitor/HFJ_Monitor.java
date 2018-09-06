package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.HFJ_Fun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.TerBiliLive.Ui.TerBiliLive_HFJ_Ui.HFJ_UiB_Send;
import static com.TerBiliLive.Ui.TerBiliLive_HFJ_Ui.HFJ_UiT_Text;

public class HFJ_Monitor implements KeyListener {


    public HFJ_Monitor(){




    HFJ_UiB_Send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                new HFJ_Fun("");

            }
        });

        HFJ_UiT_Text.addKeyListener(this);




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==10){
            HFJ_UiT_Text.getRootPane().setDefaultButton(HFJ_UiB_Send);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




