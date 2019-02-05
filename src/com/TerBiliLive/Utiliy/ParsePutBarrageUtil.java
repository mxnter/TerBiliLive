package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.awt.*;

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

    public void ParsePut( String timeline, String GUARD , String  vip, String  isadmin , String medal,String medal_level, String  user_level , String  nickname , String text) throws BadLocationException {
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,12);
        StyledDocument docs = ConfInfo.docsS;//获得文本对象
        StyleConstants.setForeground(attrset, Color.BLACK);
        docs.insertString(ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument().getLength(), "弹幕 ：", attrset);//对文本进行追加
        System.out.println(timeline);
        docs.insertString(ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.getDocument().getLength(), timeline.toString(), attrset);//对文本进行追加
        System.out.println("2");
//        // 船员
//        if(GUARD!=null&&!GUARD.equals("")){
//            System.out.println("3");
//            switch (GUARD){
//                case "3":
//                    StyleConstants.setForeground(attrset, Color.BLUE);
//                    docs.insertString(docs.getLength(), " 舰长 ", attrset);
//                    break;
//                case "2":
//                    StyleConstants.setForeground(attrset, Color.RED);
//                    docs.insertString(docs.getLength(), " 提督 ", attrset);
//                    break;
//                case "1":
//                    StyleConstants.setForeground(attrset, Color.PINK);
//                    docs.insertString(docs.getLength(), " 总督 ", attrset);
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(vip!=null&&!vip.equals("")){
//            System.out.println("4");
//            switch (vip){
//                case "月费老爷":
//                    StyleConstants.setForeground(attrset, Color.BLUE);
//                    docs.insertString(docs.getLength(), " 月费老爷 ", attrset);
//                    break;
//                case "年费老爷":
//                    StyleConstants.setForeground(attrset, Color.YELLOW);
//                    docs.insertString(docs.getLength(), " 年费老爷 ", attrset);
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(isadmin!=null&&!isadmin.equals("")){
//            System.out.println("5");
//            switch (isadmin){
//                case "房主":
//                    StyleConstants.setForeground(attrset, Color.ORANGE);
//                    docs.insertString(docs.getLength(), " 房主 ", attrset);
//                    break;
//                case "房管":
//                    StyleConstants.setForeground(attrset, Color.ORANGE);
//                    docs.insertString(docs.getLength(), " 房管 ", attrset);
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(medal!=null&&!medal.equals("")){
//            System.out.println("6");
//            int lv=Integer.parseInt(medal_level);
//
//            if(lv>0&&lv<5){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#61ddcb"));
//                docs.insertString(docs.getLength(), " ["+medal.toString()+medal_level.toString()+"] ", attrset);
//            }
//            if(lv>4&&lv<9){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#5896de"));
//                docs.insertString(docs.getLength(), " ["+medal.toString()+medal_level.toString()+"] ", attrset);
//            }
//            if(lv>8&&lv<13){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#a068f1"));
//                docs.insertString(docs.getLength(), " ["+medal.toString()+medal_level.toString()+"] ", attrset);
//            }
//            if(lv>12&&lv<17){
//                StyleConstants.setForeground(attrset,ColorUtil.toColorFromString("#ff86b2"));
//                docs.insertString(docs.getLength(), " ["+medal.toString()+medal_level.toString()+"] ", attrset);
//            }
//            if(lv>16&&lv<21){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#f6be18"));
//                docs.insertString(docs.getLength(), " ["+medal.toString()+medal_level.toString()+"] ", attrset);
//            }
//        }
//        if(user_level!=null&&!user_level.equals("")){
//            System.out.println("7");
//            int lv=Integer.parseInt(user_level);
//
//            if(lv>0&&lv<11){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#969696"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//            System.out.println("7.1");
//            if(lv>10&&lv<21){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#61c05a"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//            System.out.println("7.2");
//            if(lv>20&&lv<31){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#5896de"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//            System.out.println("7.3");
//            if(lv>30&&lv<41){
//                StyleConstants.setForeground(attrset,ColorUtil.toColorFromString("#a068f1"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//            System.out.println("7.4");
//            if(lv>40&&lv<51){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#ff86b2"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//            System.out.println("7.5");
//            if(lv>50&&lv<61){
//                StyleConstants.setForeground(attrset, ColorUtil.toColorFromString("#ff9f3d"));
//                docs.insertString(docs.getLength(), " [UL"+user_level.toString()+"] ", attrset);
//            }
//        }



        System.out.println("8");
        StyleConstants.setForeground(attrset, Color.BLACK);
//        docs.insertString(docs.getLength(), "", attrset);
        System.out.println("9");
        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.setCaretPosition(ConfInfo.docs.getLength());
    }
    public void PutDMUtil() throws BadLocationException {
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,12);
        Document docs = ConfInfo.docs;//获得文本对象
//        Document doc = docs ;
        StyleConstants.setForeground(attrset, Color.BLACK);
        docs.insertString(docs.getLength(), "弹幕 ：", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.YELLOW);
        docs.insertString(docs.getLength(), "123", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.BLACK);
        docs.insertString(docs.getLength(), "弹幕 ：", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.YELLOW);
        docs.insertString(docs.getLength(), "123", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.BLACK);
        docs.insertString(docs.getLength(), "弹幕 ：", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.YELLOW);
        docs.insertString(docs.getLength(), "123", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.BLACK);
        docs.insertString(docs.getLength(), "弹幕 ：", attrset);//对文本进行追加
        StyleConstants.setForeground(attrset, Color.YELLOW);
        docs.insertString(docs.getLength(), "123", attrset);//对文本进行追加

//        ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.append(putDM+"\r\n");
    }

}
