package com.github.TerBiliLive.Monitor;

import com.github.TerBiliLive.Function.HFJ_Fun;
import com.github.TerBiliLive.Info.ConfInfo;
import com.github.TerBiliLive.Ui.TerBiliLive_SendBarrage_Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HFJ_Monitor implements KeyListener {


    public HFJ_Monitor(){




    TerBiliLive_SendBarrage_Ui.HFJ_UiB_Send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiB_Send.setEnabled(false);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setEnabled(false);
                new HFJ_Fun("");//在里面写的获取发送文字
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setText("");

            }
        });

        TerBiliLive_SendBarrage_Ui.HFJ_UiT_Text.addKeyListener(this);




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==10){
            TerBiliLive_SendBarrage_Ui.HFJ_UiT_Text.getRootPane().setDefaultButton(TerBiliLive_SendBarrage_Ui.HFJ_UiB_Send);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}




