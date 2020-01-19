package com.TerBiliLive.Ui;

import com.TerBiliLive.Info.ConfInfo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * @名称 收取弹幕 UI
 * @作用 收取弹幕
 * @作者 Mxnter
 */

public class TerBiliLive_ChargeBarrage_Ui {
    public static String Version = "Beta08-0617";
    public static String Appname = "TerBiliLive ChargeBarrage";
    public static String ProjectName = "TerBiliLive ChargeBarrage";
    String DMJpathUrl = "http://live.bilibili.com/msg/send";


    public static JTextPane DMJ_UiT_Text = new JTextPane();
    public static JPanel DMJ_Ui_Jpanel = new JPanel(new GridLayout(1, 1, 10, 10));



    public TerBiliLive_ChargeBarrage_Ui() {

//      DMJ_UiT_Text.setWrapStyleWord(true);
//      DMJ_UiT_Text.setLineWrap(true);//设置自动换行
        DMJ_UiT_Text.setBorder(new TitledBorder("信息："));//设置标题
//      DMJ_UiT_Text.setWrapStyleWord(false);//设置以单词为整体换行，(即不会将单词切割成两半)
        DMJ_UiT_Text.setEditable(false);//不可编辑
        JScrollPane scrollPane = new JScrollPane(DMJ_UiT_Text);//添加滚动条
        scrollPane.setBounds(5, 0, 250, 160);
        DMJ_Ui_Jpanel.add(scrollPane);
    }
}
