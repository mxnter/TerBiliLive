package com.TerBiliLive.ExtendInterface;

import com.alibaba.fastjson.JSONObject;

/**
 * 解析弹幕后调用扩展接口
 */
public interface ParsingBarrageExtend {

    public abstract void ParsingBarrage(String msgType,JSONObject object);
}
