package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlUtil {
    public XmlUtil(){

    }

    String readXml(){

        return "";
    }

    public boolean readData(){

        String TerBiliLiveData =FileUtil.readFile("xData");
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = null;
        try {
//            builder = factory.newDocumentBuilder();
//            StringReader sr = new StringReader(FileUtil.readFile("Data"));
//            InputSource is = new InputSource(sr);
//            Document doc = builder.parse(is);
//            NodeList nList = doc.getElementsByTagName("TerBiliLiveData");
//            Node node = nList.item(0);
//            Element ele = (Element)node;

            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Ter/data/xData.terda"));
            Element rootElement = document.getDocumentElement();
            char[] c = rootElement.getAttribute("Cookie").toCharArray();
            //使用for循环给字符数组加密
            for(int i=0;i<c.length;i++){
                c[i] = (char)(c[i]-2);
            }

            ConfInfo.confData.setCookie(new String(c));
            ConfInfo.confData.setRoomId(rootElement.getAttribute("Roomid"));
            ConfInfo.confData.setSecond(rootElement.getAttribute("Second"));
            ConfInfo.confData.setText(rootElement.getAttribute("Text"));

//
//            char[] c = GetSubString.GetSubString(TerBiliLiveData, "<Cookie>", "</Cookie>").toCharArray();
//            //使用for循环给字符数组加密
//            for(int i=0;i<c.length;i++){
//                c[i] = (char)(c[i]-2);
//            }
//
//            ConfInfo.confData.setCookie(new String(c));
//            ConfInfo.confData.setRoomId(GetSubString.GetSubString(TerBiliLiveData, "<Roomid>", "</Roomid>"));
//            ConfInfo.confData.setSecond(GetSubString.GetSubString(TerBiliLiveData, "<Second>", "</Second>"));
//            ConfInfo.confData.setText(GetSubString.GetSubString(TerBiliLiveData, "<Text>", "</Text>"));
//            ConfInfo.confData.setCookie(ele.getAttribute("Cookie"));
//            ConfInfo.confData.setRoomId(ele.getAttribute("Roomid"));
//            ConfInfo.confData.setSecond(ele.getAttribute("Second"));
//            ConfInfo.confData.setText(ele.getAttribute("Text"));


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    public boolean writeData(){

//            ele.setAttribute("Cookie",ConfInfo.confData.getCookie());
//            ele.setAttribute("Roomid",ConfInfo.confData.getRoomId());
//            ele.setAttribute("Second",ConfInfo.confData.getSecond());
//            ele.setAttribute("Text",ConfInfo.confData.getText());
        // 创建一个DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 创建一个DocumentBuilder对象
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //DocumentBuilder db=getDocumentBuilder();
        Document document=db.newDocument();
        document.setXmlStandalone(true);
        Element TerBiliLiveData=document.createElement("TerBiliLiveData");
        //向bookstore根节点中添加字节点book
        Element Cookie=document.createElement("Cookie");
        Element Roomid=document.createElement("Roomid");
        Element Second=document.createElement("Second");
        Element Text=document.createElement("Text");
//        book.appendChild(name);
        char[] c = ConfInfo.confData.getCookie().toCharArray();
        //使用for循环给字符数组加密
        for(int i=0;i<c.length;i++){
            c[i] = (char)(c[i]+2);
        }
        Cookie.setTextContent(new String(c));
        Roomid.setTextContent(ConfInfo.confData.getRoomId());
        Second.setTextContent(ConfInfo.confData.getSecond());
        Text.setTextContent(ConfInfo.confData.getText());

//        book.setAttribute("id", "1");

        TerBiliLiveData.appendChild(Cookie);
        TerBiliLiveData.appendChild(Roomid);
        TerBiliLiveData.appendChild(Second);
        TerBiliLiveData.appendChild(Text);
        document.appendChild(TerBiliLiveData);
        //创建TransformerFactory对象
        TransformerFactory tff=TransformerFactory.newInstance();
        //创建Transformer对象
        Transformer tf= null;
        try {
            tf = tff.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        //换行文件内容
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            tf.transform(new DOMSource(document), new StreamResult(new File("Ter/data/xData.terda")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return true;
    }

}
