package com.TerBiliLive.Inlet;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveRoom;
import com.TerBiliLive.Info.SendBarrageMap;
import com.TerBiliLive.Thr.ChargeNoticeS_Thr;
import com.TerBiliLive.Thr.GetSendBarrageList_Thr;
import com.TerBiliLive.Thr.ParsingBarrage_Thr;
import com.TerBiliLive.Utils.DevLogUtil;
import com.TerBiliLive.Utils.FileUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 前置入口
 * 启动弹幕连接和关闭
 */
public class Control_Inlet {
    ChargeNoticeS_Thr chargeNoticeS_thr;

    public void Connect(){

        chargeNoticeS_thr= new ChargeNoticeS_Thr();
        ConfInfo.GBT =new GetSendBarrageList_Thr();
        ConfInfo.PBT =new ParsingBarrage_Thr();
//        ConfInfo.liveRoom.getLiveRoom(ConfInfo.barrage.getRoomid());
        ConfInfo.ChargeBarrageList.clear();
        chargeNoticeS_thr.start(ConfInfo.liveRoom.getRoom_id(),true);
        ConfInfo.GBT.start();
        ConfInfo.PBT.start();
    }

    @SuppressWarnings("deprecation")
    public void Disconnect(){
        chargeNoticeS_thr.stop();
        ConfInfo.GBT.stop();
        ConfInfo.PBT.stop();
        //DT.interrupt();

    }

    /**
     * 保存基础信息
     */
    public void Preservation(){
        List<String> roomId = new ArrayList<>();
        roomId.add(ConfInfo.barrage.getRoomid());
        roomId.add("roomId");
        ConfInfo.databaesUtil.executeUpdate(ConfInfo.Database_UpdateSystemDataWhereName,roomId);

        List<String> systemState = new ArrayList<>();
        systemState.add(ConfInfo.systemState.toString());
        systemState.add("systemState");
        ConfInfo.databaesUtil.executeUpdate(ConfInfo.Database_UpdateSystemDataWhereName,systemState);

        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");

        if(ConfInfo.dev){ FileUtil.writeFile("Cookie",ConfInfo.confData.getCookie());}

        ConfInfo.confData.setCookie(ConfInfo.confData.getCookie());
        ConfInfo.confData.setRoomId(ConfInfo.barrage.getRoomid());

        ConfInfo.confData.writeConfData(); // 保存到数据库

    }

    /**
     * 清除基础信息
     */
    public void Clear(){
        //创建目录
        FileUtil.createDir("Ter/data/");
        FileUtil.createDir("Ter/log/");
        FileUtil.createDir("Ter/Dm/");

        //写入数据
        if(ConfInfo.dev){ FileUtil.writeFile("Cookie",""); }

        ConfInfo.confData.setCookie("");
        ConfInfo.confData.writeConfData();
        JOptionPane.showMessageDialog(null,"已经退出登录,请手动启动软件!");
        System.exit(0);

    }
}
