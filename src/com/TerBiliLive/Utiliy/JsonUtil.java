package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.TerBiliLive.getSubString;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class JsonUtil {
    public JsonUtil(){

    }



    public boolean readData(){

            String TerBiliLiveData =FileUtil.readFile("Data");
            String sn = DiskUtil.getSerialNumber("C");
            char[] c = TerBiliLiveData.toCharArray();
            //使用for循环给字符数组加密
            for(int i=0;i<c.length;i++){
                c[i] = (char)(c[i]-(Integer.parseInt(sn)%10));
            }
            TerBiliLiveData= new String(c);
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
            String sn = DiskUtil.getSerialNumber("C");
            char[] c = TerBiliLiveData.toCharArray();
            //使用for循环给字符数组加密
            for(int i=0;i<c.length;i++){
                c[i] = (char)(c[i]+(Integer.parseInt(sn)%10));
            }
            TerBiliLiveData= new String(c);

            FileUtil.writeFile("Data",TerBiliLiveData);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return true;
    }
}
