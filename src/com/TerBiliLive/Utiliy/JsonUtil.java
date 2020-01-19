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
        String readDatas =FileUtil.readFile("Data");
        // TODO 加密后部分电脑无法读取数据 更换加密方式
        String TerBiliLiveData = "";
        try {
            TerBiliLiveData = AESUtil.Decrypt(readDatas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if(TerBiliLiveData!=null && (!TerBiliLiveData.equals(""))) {
                JSONObject jsonObject = new JSONObject(TerBiliLiveData);
                ConfInfo.confData.setCookie(jsonObject.getString("Cookie"));
                ConfInfo.confData.setRoomId(jsonObject.getString("Roomid"));
                ConfInfo.confData.setSecond(jsonObject.getString("Second"));
                ConfInfo.confData.setText(jsonObject.getString("Text"));
                ConfInfo.confData.setTulingApikey(jsonObject.getString("TulingApikey"));
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
            TerBiliLiveData=jsonObject.toString();
            DevLogUtil.printf(TerBiliLiveData);
            // TODO 加密后部分电脑无法读取数据 更换加密方式
            String writeDatas = "";
            try {
                writeDatas = AESUtil.Encryption(TerBiliLiveData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FileUtil.writeFile("Data",writeDatas);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
