package com.TerBiliLive.Function;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.SendBarrageMap;
import com.TerBiliLive.Thr.SendBarrage_Thr;

import java.util.HashMap;
import java.util.Map;

//import static com.TerBiliLive.Ui.TerBiliLive_SendBarrage_Ui;


public class HFJ_Fun {
    String Parameter= "" ;
    String pathUrl = "http://live.bilibili.com/msg/send";

    public HFJ_Fun() {
    }
    public HFJ_Fun(String msg) {
        new HFJ_Fun(msg,1);
    }

    public HFJ_Fun(String msg,int type){

        if(!ConfInfo.SBLT.isAlive()) ConfInfo.SBLT.start();//线程死亡启动线程
       // HFJ.HFJ_UiT_RoomId.setText(C.Control_UiT_RoomId.getText());
       // HFJ.HFJ_UiT_Cookie.setText(C.Control_UiT_Cookie.getText());

    Map<String, String> mapParam = new HashMap<String, String>();
    String roomid, cookie,url;

    url=pathUrl;
    roomid = ConfInfo.terBiliLive_control_ui.Control_UiT_RoomId.getText();
//
//                try {
//                    if(msg.equals("")){

//                    }else{
//                        msg = URLEncoder.encode(msg, "UTF-8");
//                    }
//                    if(msg.length()>20){
//                        String msgg=msg.substring(20);
//                        msg=msg.substring(0, 20);
//                        new HFJ_Fun(msgg);
//                    }



//                    mapParam.put("msg", msg);
//    } catch (UnsupportedEncodingException e1) {
//        // TODO Auto-generated catch block
//        e1.printStackTrace();
//    }
    //	System.out.print("cookie:");

        if(msg.equals("")){
            msg =ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.getText();
            type = 2;
            if(msg.equals("")){
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("不能发送空的弹幕");
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiB_Send.setEnabled(true);
                ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_Text.grabFocus();
                return;
            }
        }
        System.out.println(msg);
//        if(msg.length()>20){
//            String msgg=msg.substring(20);
//            msg=msg.substring(0, 20);
//            new HFJ_Fun(msgg);
//        }

            cookie = ConfInfo.cookie;
//                mapParam.put("roomid", roomid);
//    int x=(int)(Math.random()*1000000);
//                mapParam.put("rnd", Integer.toString(x));

/*    //创建目录
                FileUtil.createDir("Ter/data/");
                FileUtil.createDir("Ter/log/");*/
/*    //写入数据
                FileUtil.writeFile("RoomId",HFJ.HFJ_UiT_RoomId.getText());
                FileUtil.writeFile("Text",HFJ.HFJ_UiT_Text.getText());
                FileUtil.writeFile("Cookie",HFJ.HFJ_UiT_Cookie.getText());*/


    /*  cookie----->>DedeUserID=  后的值很重要*/

//    SendPost sp=new SendPost();



        ConfInfo.terBiliLive_sendBarrage_ui.HFJ_UiT_State.setText("发送中");
        int ReplyLength=20;
        if (ConfInfo.terBiliLive_control_ui.Reply_30.isSelected())ReplyLength=30;
        if(msg.length()<ReplyLength){
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg,type));
            System.out.println(msg);}
        else{
            while(msg.length()>ReplyLength){
                ConfInfo.SendBarrageList.add(new SendBarrageMap(msg.substring(0, ReplyLength),type));
                msg=msg.substring(ReplyLength);
            }
            ConfInfo.SendBarrageList.add(new SendBarrageMap(msg,type));
        }

        synchronized (ConfInfo.SBLT) {
            ConfInfo.SBLT.notify();
        }



//            = sp.SendPost(roomid,cookie,msg);
//    getSubString gs=new getSubString();


    /*使用JSON 获取返回值中的的信息
     * gs.getSubString(RTData, "\"code\":", ",\"msg\""),  <——老方法获取值
     * CodingUtil.ascii2native(gs.getSubString(RTData, "\"code\":", ",\"msg\""))  <——老方法获取值
     * JSONObject jsonObject = new JSONObject(RTData);<——新方法获取值
     *  jsonObject.getString("code")<——新方法获取值
     *
     *
     * */


}
}