package com.TerBiliLive.Monitor;

import com.TerBiliLive.Function.HFJ_Fun;
import com.TerBiliLive.Info.ConfInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.TerBiliLive.Ui.TerBiliLive_SendBarrage_Ui.HFJ_UiB_Send;
import static com.TerBiliLive.Ui.TerBiliLive_SendBarrage_Ui.HFJ_UiT_Text;

public class HFJ_Monitor implements KeyListener {


    public HFJ_Monitor(){




    HFJ_UiB_Send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiB_Send.setEnabled(false);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setEnabled(false);
                new HFJ_Fun("");
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setText("");

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




