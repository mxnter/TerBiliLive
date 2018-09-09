package com.TerBiliLive.Utiliy;

import com.TerBiliLive.Info.ConfInfo;

public class PutShowUtil {
    public PutShowUtil(){

    }

    public void PutDMUtil(String putDM){

        ConfInfo.terBiliLive_dmj_ui.DMJ_UiT_Text.append(putDM+"\r\n");
    }
    public void PutTZUtil(String putTZ){

        ConfInfo.terBiliLive_control_ui.Control_UiB_Text.setText(putTZ+"\r\n");
    }
}
