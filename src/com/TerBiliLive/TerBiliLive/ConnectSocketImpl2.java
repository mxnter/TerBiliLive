package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.LiveConf;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 连接弹幕服务器 - 新版本
 * 2021年2月4日 添加
 * @author Mxnter Ye
 *
 */
public class ConnectSocketImpl2 implements ConnectSocket {

    private final int DEFAULT_COMMENT_PORT = 788;
    private final int PROTOCOL_VERSION = 1;
    public final int RECEIVE_BUFFER_SIZE = 10 * 1024;
    private Timer heartBeattimer;

    private int defaultCommentPort = 788;
    private String socketServerUrl;
    private String uid;
    private String token;

    public ConnectSocketImpl2(int defaultCommentPort, String socketServerUrl, String uid, String token) {
        this.defaultCommentPort = defaultCommentPort;
        this.socketServerUrl = socketServerUrl;
        this.uid = uid;
        this.token = token;
    }

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
    //未登录进入直播间使用UID = 0
    public boolean sendJoinRoomMsg(Socket socket, String roomID){
        // 未登录将不执行 防止没有Uid 导致无法连接
        String jsonBody = "{\"uid\":"+uid+",\"roomid\":"+roomID+",\"protover\":2,\"platform\":\"web\",\"clientver\":\"2.6.20\",\"type\":2,\"key\":\""+token+"\"}";
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
            if (!sendSocketData(this.socket, "[object Object]".length()+16, 16, PROTOCOL_VERSION, 2, 1, "[object Object]".getBytes(StandardCharsets.UTF_8))){
                this.cancel();
            }
        }
    }

    public Socket connect(String roomID){
        Socket socket = null;
        InetSocketAddress address = new InetSocketAddress(socketServerUrl, defaultCommentPort);
        try {
            socket = new Socket();
            socket.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
            socket.connect(address);
            if(sendJoinRoomMsg(socket, roomID)){
                heartBeattimer = new Timer();
                heartBeattimer.schedule(new ConnectSocketImpl2.sendHeartbeat(socket), 2000, 20000);
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
