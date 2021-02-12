package com.TerBiliLive.ExtendRealization;

import com.TerBiliLive.ExtendInterface.ParsingBarrageExtend;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.Nav.RelationUPNav;
import com.TerBiliLive.Inlet.SendBarrage_Inlet;
import com.TerBiliLive.Utils.RandomUtil;
import com.TerBiliLive.Utils.RuchuUtil;
import com.TerBiliLive.Utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 如初关注抽奖扩展
 * @author myetear
 */
// TODO 如初关注抽奖
public class RuchuExtend implements ParsingBarrageExtend {
    @Override
    public void ParsingBarrage(String msgType,JSONObject object) {
        switch (msgType){
            case "INTERACT_WORD": {
                switch (object.getJSONObject("data").getInteger("msg_type")){
                    case 1:{

                        if(ConfInfo.barrage.getRoomid().equals("1757608")){
                            // 已关注的保存数据
                            RelationUPNav relationUPNav = new RelationUPNav();
                            int r = relationUPNav.getJudgmentFocus(object.getJSONObject("data").getString("uid"),ConfInfo.getLiveRoomUserInfo.getRoomUseruid());
                            if(r==1){ // 是否已经关注
                                RuchuUtil.putLogFile("进入直播间识别到已关注 UID:"+object.getJSONObject("data").getString("uid")+" NAME:"+object.getJSONObject("data").getString("uname"));
                                if(RuchuUtil.ruchuUserId.indexOf(object.getJSONObject("data").getString("uid"))==-1){
                                    RuchuUtil.putDataFile(object.getJSONObject("data").getString("uid"));
                                }
                            }
                        }
                        break;
                    }
                    case 2:{
                        if(ConfInfo.barrage.getRoomid().equals("1757608")){
                            // 新的关注进行处理
                            RuchuUtil.userSttention(object.getJSONObject("data").getString("uid"),object.getJSONObject("data").getString("uname"),TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("timestamp"), null));
                        }
                        break;
                    }
                    case 3:{
                        if(ConfInfo.barrage.getRoomid().equals("1757608")){
                            // 分享进行处理
//                            RuchuUtil.share(object.getJSONObject("data").getString("uid"),object.getJSONObject("data").getString("uname"),TimeUtil.timeStamp2Date(object.getJSONObject("data").getString("timestamp"), null));
                        }

                        break;
                    }
                }
                break;

            }
        }

    }
}
