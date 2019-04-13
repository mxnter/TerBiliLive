package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;
import org.json.JSONException;
import org.json.JSONObject;

import static com.TerBiliLive.Utiliy.TimeUtil.getFormatDay;
import static com.TerBiliLive.Utiliy.TimeUtil.getFormatHour;

public class JsonUtil {
    public JsonUtil(){

    }



    public boolean readData(){

            String TerBiliLiveData =FileUtil.readFile("Data");
        // TODO 加密后部分电脑无法读取数据
            String sn = DiskUtil.getSerialNumber("C");
//            char[] c = TerBiliLiveData.toCharArray();
//            //使用for循环给字符数组加密
//            for(int i=0;i<c.length;i++){
//                c[i] = (char)(c[i]-(Integer.parseInt(sn)%10));
//            }
//            TerBiliLiveData= new String(c);
        try {
            if(!TerBiliLiveData.equals("")) {
                JSONObject jsonObject = new JSONObject(TerBiliLiveData);

                ConfInfo.confData.setCookie(jsonObject.getString("Cookie"));
                ConfInfo.confData.setRoomId(jsonObject.getString("Roomid"));
                ConfInfo.confData.setSecond(jsonObject.getString("Second"));
                ConfInfo.confData.setText(jsonObject.getString("Text"));
                ConfInfo.confData.setTulingApikey(jsonObject.getString("TulingApikey"));
//            ConfInfo.confData.setCookie(ele.getAttribute("Cookie"));
//            ConfInfo.confData.setRoomId(ele.getAttribute("Roomid"));
//            ConfInfo.confData.setSecond(ele.getAttribute("Second"));
//            ConfInfo.confData.setText(ele.getAttribute("Text"));
            }

        } catch (JSONException e) {
            System.out.println("数据为空");
            LogUtil.putLog(getFormatDay(), getFormatHour(), "数据为空" + "\n", "TerBiliLive Out");
            e.printStackTrace();
        }

        return true;
    }

    public boolean writeData(){

        String TerBiliLiveData =null;

        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("Cookie",ConfInfo.confData.getCookie());
            jsonObject.put("Roomid",ConfInfo.confData.getRoomId());
            jsonObject.put("Second",ConfInfo.confData.getSecond());
            jsonObject.put("Text",ConfInfo.confData.getText());
            jsonObject.put("TulingApikey",ConfInfo.confData.getTulingApikey());
//            ConfInfo.confData.setCookie(ele.getAttribute("Cookie"));
//            ConfInfo.confData.setRoomId(ele.getAttribute("Roomid"));
//            ConfInfo.confData.setSecond(ele.getAttribute("Second"));
//            ConfInfo.confData.setText(ele.getAttribute("Text"));
            TerBiliLiveData=jsonObject.toString();

            // TODO 加密后部分电脑无法读取数据
            String sn = DiskUtil.getSerialNumber("C");
            char[] c = TerBiliLiveData.toCharArray();
//            //使用for循环给字符数组加密
//            for(int i=0;i<c.length;i++){
//                c[i] = (char)(c[i]+(Integer.parseInt(sn)%10));
//            }
//            TerBiliLiveData= new String(c);

            FileUtil.writeFile("Data",TerBiliLiveData);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return true;
    }
}
