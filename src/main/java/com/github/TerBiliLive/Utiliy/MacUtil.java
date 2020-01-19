package com.github.TerBiliLive.Utiliy;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class MacUtil {

    public static String getMac(){
        try {

            Process process = Runtime.getRuntime().exec("ipconfig /all");

            InputStreamReader ir = new InputStreamReader(process.getInputStream());

            LineNumberReader input = new LineNumberReader(ir);

            String line;

            while ((line = input.readLine()) != null)


                if (line.indexOf("Physical Address") > 0) {

                    String MACAddr = line.substring(line.indexOf("-") - 2);

                    System.out.println("MAC address = [" + MACAddr + "]");
                    return MACAddr;

                }

        } catch (java.io.IOException e) {

            System.err.println("IOException " + e.getMessage());

        }
        return "获取失败";
    }

    public static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            byte[] mac = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                    continue;
                } else {
                    mac = netInterface.getHardwareAddress();
                    if (mac != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        if (sb.length() > 0) {
                            return sb.toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("MAC地址获取失败");
        }
        return "获取失败";
    }
}