package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveConf;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GetInfo {


    private final int DEFAULT_COMMENT_PORT = 788;
    private final int PROTOCOL_VERSION = 1;
    public final int RECEIVE_BUFFER_SIZE = 10 * 1024;
    private Timer heartBeattimer;



    public boolean sendSocketData(Socket socket, int total_len, int head_len, int version, int action, int param5, byte[] data){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(total_len);
            out.writeShort(head_len);
            out.writeShort(version);
            out.writeInt(action);
            out.writeInt(param5);
            if (data != null && data.length > 0) out.write(data);
            out.flush();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean sendJoinRoomMsg(Socket socket, String roomID){
        String uid =  String.valueOf(new Random().nextInt(899999) + 100000);//生成随机Uid
        // 未登录将不执行 防止没有Uid 导致无法连接
        if(null != ConfInfo.liveUserInfo|| !ConfInfo.confData.getCookie().equals("")){
            uid = ConfInfo.liveUserInfo.getUid();
        }
        String jsonBody = "{\"roomid\": " + roomID + ", \"uid\": " + uid + "}";
        try {
            return sendSocketData(socket, jsonBody.length() + 16, 16, PROTOCOL_VERSION, 7, 1, jsonBody.getBytes(StandardCharsets.UTF_8));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public class sendHeartbeat extends TimerTask {
        private Socket socket;
        public sendHeartbeat(Socket sock){
            this.socket = sock;
        }
        public void run(){
            if (!sendSocketData(this.socket, 16, 16, PROTOCOL_VERSION, 2, 1, null)){
                this.cancel();
            }
        }
    }

    public Socket connect(String roomID){
//        String realRoomID = getRealRoomID(roomID);
        ConfInfo.liveConf = new LiveConf(roomID,ConfInfo.confData.getCookie());
        String socketServerUrl = ConfInfo.liveConf.getHost();
        int socketServerPort = (ConfInfo.liveConf.getPort()==0)?DEFAULT_COMMENT_PORT:ConfInfo.liveConf.getPort();
//        LogUtil.putLog(getFormatDay(), getFormatHour(), liveInfo.toString()+ "\n","TerBiliLive Log");
        Socket socket = null;
        InetSocketAddress address = new InetSocketAddress(socketServerUrl, DEFAULT_COMMENT_PORT);
        try {
            socket = new Socket();
            socket.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
            socket.connect(address);
            if(sendJoinRoomMsg(socket, roomID)){
                heartBeattimer = new Timer();
                heartBeattimer.schedule(new sendHeartbeat(socket), 2000, 20000);
                return socket;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return socket;
    }

    public void disconnect(Socket socket){
        try {
            heartBeattimer.cancel();
            socket.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
