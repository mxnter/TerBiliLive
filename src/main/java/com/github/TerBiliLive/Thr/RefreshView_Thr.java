package com.github.TerBiliLive.Thr;

/**
 * CODE IS POETRY
 * @Nmae ：
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 11:51 2018/11/4
 */

public class RefreshView_Thr extends Thread {


    @Override
    public void run() {
        super.run();
        while (true) {

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            ConfInfo.terBiliLive_chargeBarrage_ui.DMJ_UiT_Text.setDocument(ConfInfo.docalls);

        }
    }
}
