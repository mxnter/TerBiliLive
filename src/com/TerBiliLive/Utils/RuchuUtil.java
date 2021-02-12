package com.TerBiliLive.Utils;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Inlet.SendBarrage_Inlet;

import java.io.*;
import java.util.*;

import static com.TerBiliLive.Utils.TimeUtil.getFormat;
import static com.TerBiliLive.Utils.TimeUtil.getFormatDay;

/**
 * 给如初写的关注活动
 * @author myetear
 */
public class RuchuUtil {


    public static void main(String[] args) {
        Map<String, Integer> all = new HashMap<>();
        System.out.println(analysisWeightLotterys());

//        for (int i = 0; i < 100; i++) {
//            String p = weightLottery();
//            System.out.print((p==null?"未中奖":p)+"\t");
//        }

    }


    public static List<String> ruchuUserId = new ArrayList<>();
    public static List<WeightLotteryList> weightLotteryLists = null;


    /**
     * 用户关注后进入这个方法
     * @param uid
     * @param name
     * @param time
     */
    public static void userSttention(String uid,String name,String time){
        if(ruchuUserId.indexOf(uid)>-1){
            // 老用户关注
            putLogFile("×××× 老用户关注 UID："+uid+" 昵称： "+name+" 时间： "+time);
            if(ConfInfo.systemState.isFollowDraw){
                new SendBarrage_Inlet("@"+name+" 重复关注不触发奖励");
                putLogFile("重复关注不触发奖励 UID："+uid+" 昵称： "+name+" 时间： "+time);
            }
        }else{
            // 新用户关注
            putLogFile("√√√√ 新用户关注 UID："+uid+" 昵称： "+name+" 时间： "+time);
            putDataFile(uid); // 将新关注的用户写入文件
            if(ConfInfo.systemState.isFollowDraw){
                String prize = weightLottery();
                if(prize!=null&&!prize.equals("")){ // 随机触发奖励
                    new SendBarrage_Inlet(""+name+" 关注触发 "+prize);
                    putWinningPrizeFile(prize+" UID："+uid+" 昵称： "+name+" 时间： "+time);
                    putLogFile(prize+" UID："+uid+" 昵称： "+name+" 时间： "+time);
                }
            }

        }
    }
    public static void share(String uid,String name,String time){
        if(ConfInfo.systemState.isFollowDraw){
            String prize = weightLottery();
            if(prize!=null&&!prize.equals("")){ // 随机触发奖励
                new SendBarrage_Inlet("@"+name+" 分享抽奖(测试) "+prize);
                putWinningPrizeFile(prize+" UID："+uid+" 昵称： "+name+" 时间： "+time);
                putLogFile(prize+" UID："+uid+" 昵称： "+name+" 时间： "+time);
            }
        }
    }


    public static String weightLottery(){
        if(weightLotteryLists==null){
            putLogFile("抽奖信息不存在");
            return null;
        }
        double weight = weightLotteryLists.stream().mapToDouble(WeightLotteryList::getWeight).sum();
        if(weight<=100.0){
            Random random = new Random();
            double sjx = random.nextDouble();
            double prize = sjx*100;
            putLogFile("生产出的随机数 "+prize);
            double forWeight = 0;
            for (int i = 0; i < weightLotteryLists.size(); i++) {
                forWeight += weightLotteryLists.get(i).getWeight();
                if(prize <= forWeight){
                    putLogFile("奖品 "+weightLotteryLists.get(i).getContent());
                    return weightLotteryLists.get(i).getContent();
                }
            }
            return null;
        }else{
            putLogFile("权重大于100，无法抽奖");
            return null;
        }
    }


    /**
     * 解析用户ID信息
     */
    public static int analysisUsers(){
        ruchuUserId = new ArrayList<>();
       String s = readDataFile();
       String[] ss = s.split(",");
       for (int i = 0; i < ss.length; i++) {
           if(!ss[i].equals("")){
               ruchuUserId.add(ss[i].trim());
           }
       }
        putLogFile("解析出关注用户总数 "+ruchuUserId.size());
       return ruchuUserId.size();
    }
    /**
     * 解析奖品信息
     */
    public static int analysisWeightLotterys(){
        weightLotteryLists = new ArrayList<>();

        String encoding = "UTF-8";
        StringBuilder readText = new StringBuilder();
        File file = new File("Ter/Ruchu/奖品.txt");
        try {
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    readText.append(lineTxt);
                }
                read.close();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = readText.toString();
//        System.out.println(str);
        String[] strs = str.split(",");
        for (int i = 0; i < strs.length; i++) {
//            System.out.println(strs[i]);
            if(!strs[i].equals("")){
                String[] strss = strs[i].split("&");
//                System.out.println(strss.length);
                if(strss.length==3){
                    weightLotteryLists.add(new WeightLotteryList(strss[0].trim(),strss[1].trim(), Double.parseDouble(strss[2].trim())));
                }
            }
        }

        putLogFile("解析出奖励总数 "+weightLotteryLists.size());
        return weightLotteryLists.size();
    }



    // 读取用户信息
    public static String readDataFile() {
        try {
            String encoding = "UTF-8";
            StringBuilder readText = new StringBuilder();
            File file = new File("Ter/Ruchu/FocusUserId.txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    readText.append(lineTxt);
                }
                read.close();
                return readText.toString();
            } else {
                InOutPutUtil.outPut("找不到指定的文件");
                putLogFile("找不到指定的文件");
                return "";
            }
        } catch (Exception e) {
            InOutPutUtil.outPut("读取文件内容出错");
            putLogFile("读取文件内容出错");
            e.printStackTrace();
            return "";
        }

    }

    // 关注用户ID存入文件
    public static void putDataFile(String uid) {
        FileWriter fw = null;
        try {
            File f = new File("Ter/Ruchu/FocusUserId.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(uid+",");
        ruchuUserId.add(uid);
        putLogFile("新增已关注用户 UID "+uid);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 关注用户ID日志存入文件
    public static void putLogFile(String log) {
        FileWriter fw = null;
        try {
            File f = new File("Ter/Ruchu/FocusUserId "+getFormatDay()+".log");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(getFormat() +" : "+ log);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 中奖用户信息
    public static void putWinningPrizeFile(String log) {
        FileWriter fw = null;
        try {
            File f = new File("Ter/Ruchu/WinningPrize "+getFormatDay()+".txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(getFormat() +" : "+ log);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    // TODO 关注用户ID和名字存入文件 本次不准备用
    static List<RuchuUser> ruchuUsers = new ArrayList<>();
    public static void putDataFile(String uid,String name) {
        FileWriter fw = null;
        try {
            // 如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File("Ter/Ruchu/FocusUsers.txt");
            fw = new FileWriter(f, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(uid+"|-|"+name+"|,|");
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class RuchuUser{
    String uid;
    String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RuchuUser{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
class WeightLotteryList{
    String id;
    String content;
    double weight;

    public WeightLotteryList(String id, String content, double weight) {
        this.id = id;
        this.content = content;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}