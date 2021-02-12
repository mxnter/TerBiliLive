package com.TerBiliLive.TerBiliLive;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.TimerTask;

public interface ConnectSocket {


    public Socket connect(String roomID);
    public void disconnect(Socket socket);




}
