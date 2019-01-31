package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * CODE IS POETRY
 *
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 14:05 2019/1/31
 * 用于解析弹幕数据 并且已不同颜色显示
 */

// TODO 未实现
public class ParsePutBarrageUtil {
    public ParsePutBarrageUtil(){}

    public void ParsePut(){
        Document docs = ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument();//获得文本对象

//
//        try {
//            docs.insertString(docs.getLength(), putDM+"\n", attrset);//对文本进行追加
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
//        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.setCaretPosition(ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument().getLength());
    }
    public void PutDMUtil(Document doc){

//        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.append(putDM+"\r\n");
    }

}
