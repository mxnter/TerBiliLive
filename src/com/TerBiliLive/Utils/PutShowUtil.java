package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;


public class PutShowUtil {
    public PutShowUtil(){

    }

    public void PutDMUtil(String putDM,Color ColorDM){
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,12);
        StyleConstants.setForeground(attrset, ColorDM);
//        ConfInfo.docs
//        Document docs = ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument();//获得文本对象
        try {
            ConfInfo.docs.insertString(ConfInfo.docs.getLength(), putDM+"\n", attrset);//对文本进行追加
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.setCaretPosition(ConfInfo.docs.getLength());
//        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.append(putDM+"\r\n");
    }

    public void PutTZUtil(String putTZ){

        ConfInfo.terBiliLive_control_ui.Control_UiB_Text.setText(putTZ+"\r\n");
    }
}
