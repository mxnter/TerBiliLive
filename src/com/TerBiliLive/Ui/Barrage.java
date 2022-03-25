package com.TerBiliLive.Ui;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Info.Nav.LiveRoomInfoNav;
import com.TerBiliLive.Info.Nav.RelationUPNav;
import com.TerBiliLive.Info.SendBarrageMap;
import com.TerBiliLive.Inlet.SendBarrage_Inlet;
import com.TerBiliLive.TerBiliLive.GetLiveRoomUserInfo;
import com.TerBiliLive.TerBiliLive.HttpClient;
import com.TerBiliLive.TerBiliLive.TerWindowListener;
import com.TerBiliLive.Utils.InOutPutUtil;
import com.TerBiliLive.Utils.PutShowUtil;
import com.TerBiliLive.Utils.RuchuUtil;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.TerBiliLive.Utils.TimeUtil.getFormat;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
/**
 * CODE IS POETRY
 * @Nmae ：弹幕姬主页
 * @Author ：xnter@outlook.com.
 * @Date ：Created in 09:51 2020/09/14
 */

public class Barrage extends JFrame {
    private JPanel panel;
    private JPanel controlPanel;
    private JLabel roomidLabel;
    private JTextField roomid;
    private JButton link;
    private JPanel onePanel;
    private JPanel twoPanel;
    private JLabel background;
    private JPanel msgPanel;
    private JLabel UIDLabel;
    private JLabel ULLabel;
    private JLabel UPLabel;
    private JLabel isLiveMsgState;
    private JLabel isGreetMasterState;
    private JLabel istEffectInfoState;
    private JLabel isIgnoreSpicystripState;
    private JLabel isSend30State;
    private JTextField sendText;
    private JButton send;
    private JPanel sendPanel;
    private JEditorPane editorPane;
    private JPanel threePanel;
    private JLabel sendState;
    private JLabel noticeLabel;
    private JTextField noticeTextField;
    private JButton thank;
    private JLabel popularity;
    private JLabel heartbeat;
    private JScrollPane scrollPane;
    private JLabel sendTextLength;
    private JLabel liveContentLabel;
    private JLabel liveTitle;
    private JLabel isLiveStatus;
    private JLabel isSystemSendLockState;
    private JPanel conPanel;
    private JTextField minorNotice;
    private JLabel roomRank;
    private JLabel isInteractWordStatus;
    private JButton logout;
    private JLabel isThankFollowStatus;
    private JLabel isThankShareStatus;
    private JButton modify;
    private JButton test;
    private JLabel isFollowDrawStatus;
    private ImageIcon backgroundImg;

    public Barrage() {

//        backgroundImg = ImageBroker.loadImageIcon("loginBackground500x750.jpg");
//        background.setIcon(backgroundImg);
//        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
//        background.setBounds(0,0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());//设置背景标签的位置

        this.setContentPane(panel);
        this.setTitle(ConfInfo.Uname + " - " + ConfInfo.AppName.get(ConfInfo.AppSystemId) + " - " +ConfInfo.AppVersion+ "."+ConfInfo.AppVersionBuildNum);
        this.setName("Barrage");
        this.addWindowListener(new TerWindowListener(this));
        this.setIconImage(ImageBroker.loadImages("logoa.png"));  //设置Logo
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);



        if (ConfInfo.putShowUtil == null) ConfInfo.putShowUtil = new PutShowUtil();
        //隐藏边框
//        this.setUndecorated(true);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);s
//        panel.setOpaque(false); //背景透明
        background.setForeground(Color.WHITE);
        background.setOpaque(false); //背景透明
        onePanel.setOpaque(false); //背景透明
        controlPanel.setOpaque(false); //背景透明
        twoPanel.setOpaque(false); //背景透明
        msgPanel.setOpaque(false); //背景透明
        msgPanel.setOpaque(false); //背景透明
        editorPane.setOpaque(false); //背景透明
        sendPanel.setOpaque(false); //背景透明
        threePanel.setOpaque(false); //背景透明
        conPanel.setOpaque(false); //背景透明
        noticeTextField.setBorder(null); //取消边框
        minorNotice.setBorder(null); //取消边框
        scrollPane.setOpaque(false); //背景透明
        scrollPane.getViewport().setOpaque(false);//背景透明
        scrollPane.setBorder(null); //取消边框
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);//隐藏水平滚动


        // 隐藏功能，控制命令开启
//        isInteractWordStatus.setVisible(false);
//        isThankFollowStatus.setVisible(false);
//        isThankShareStatus.setVisible(false);
        isFollowDrawStatus.setVisible(false); // 如初抽奖

        // 隐藏关注按钮
        modify.setVisible(false);

        // 测试按钮
        test.setVisible(false);

        // 隐藏老爷按钮 (欢迎老爷功能已关闭)
        isGreetMasterState.setVisible(false);


        UIDLabel.setText("UID "+ConfInfo.Uid);
        ULLabel.setText("UL"+ConfInfo.liveUserInfo.getUser_level()+" "+ConfInfo.Uname);
        UPLabel.setText("UP: ");
//        noticeLabel.setText("UP: ");

//        editorPane.setPage("https://blog.csdn.net/nihaoqiulinhe/article/details/78592247");
//        editorPane.setText("<div title=\"我是鼠标悬停展示的内容\" style='color:red'>66666666</div>");
//        editorPane.setText("<div title=\"我是鼠标悬停展示的内容\" style='color:red'>77777</div>");
//        editorPane.setText("<div title=\"我是鼠标悬停展示的内容\" style='color:red'>8888</div>");
//        editorPane.setText("<div title=\"我是鼠标悬停展示的内容\" style='color:red'>99999</div>");
//        editorPane.set
//        System.out.println(editorPane.getText());
//        editorPane.put("<div title=\"我是鼠标悬停展示的内容\" style='color:red'>66666666</div>");



        link.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ConfInfo.systemState.isLink){
                   closeLink();
                }else{
                   startLink();
                }
            }
        });

        thank.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ConfInfo.systemState.isThank){
                  closeThank();
                }else{
                   startThank();
                }
            }
        });

        // 弹幕锁
        isSystemSendLockState.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isSystemSendLock){
                    ConfInfo.systemState.isSystemSendLock=false;
                }else{
                    ConfInfo.systemState.isSystemSendLock=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 直播提醒
        isLiveMsgState.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isLiveMsg){
                    ConfInfo.systemState.isLiveMsg=false;
                }else{
                    ConfInfo.systemState.isLiveMsg=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 欢迎老爷
        isGreetMasterState.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isGreetMaster){
                    ConfInfo.systemState.isGreetMaster=false;
                }else{
                    ConfInfo.systemState.isGreetMaster=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 动画信息
        istEffectInfoState.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.istEffectInfo){
                    ConfInfo.systemState.istEffectInfo=false;
                }else{
                    ConfInfo.systemState.istEffectInfo=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 忽略辣条
        isIgnoreSpicystripState.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isIgnoreSpicystrip){
                    ConfInfo.systemState.isIgnoreSpicystrip=false;
                }else{
                    ConfInfo.systemState.isIgnoreSpicystrip=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 可发送30字
        isSend30State.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isSend30){
                    ConfInfo.systemState.isSend30=false;
                }else{
                    ConfInfo.systemState.isSend30=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 欢迎用户
        isInteractWordStatus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isInteractWord){
                    ConfInfo.systemState.isInteractWord=false;
                }else{
                    ConfInfo.systemState.isInteractWord=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 关注感谢
        isThankFollowStatus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState. isThankFollow){
                    ConfInfo.systemState. isThankFollow=false;
                }else{
                    ConfInfo.systemState. isThankFollow=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        // 分享感谢
        isThankShareStatus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isThankShare){
                    ConfInfo.systemState.isThankShare=false;
                }else{
                    ConfInfo.systemState.isThankShare=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        // TODO 如初关注抽奖
        isFollowDrawStatus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(ConfInfo.systemState.isFollowDraw){
                    ConfInfo.systemState.isFollowDraw=false;
                }else{
                    ConfInfo.systemState.isFollowDraw=true;
                }
                updateStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });


        // 发送按钮事件绑定
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if(sendText.getText().length()>6&&sendText.getText().substring(0,6).equals("> ter ")){
                    controlCommand();
                    return;
                }

                sendText.setEnabled(false);
                send.setEnabled(false);
                new SendBarrage_Inlet("");
                sendText.setText("");
            }
        });

        // 设置发送弹幕改变后展示长度
        sendText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                sendTextLength.setText(sendText.getText().length()+"/"+(ConfInfo.systemState.isSend30?"30":"20"));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                sendTextLength.setText(sendText.getText().length()+"/"+(ConfInfo.systemState.isSend30?"30":"20"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                sendTextLength.setText(sendText.getText().length()+"/"+(ConfInfo.systemState.isSend30?"30":"20"));
            }
        });

        // 设置发送弹幕回车按键和向上按键
        sendText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    sendText.getRootPane().setDefaultButton(send);
                }
                if(e.getKeyCode()==38){
                    sendText.setText(ConfInfo.Upper_barrage);
                }
                if(e.getKeyCode()==113){
                    sendText.setText("> ter ");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // 获取数据库中的房间号
        ResultSet roomIdresultSet =  ConfInfo.databaesUtil.select(ConfInfo.Database_SelectSystemDataWhereName+"'roomId'");
        if(roomIdresultSet!=null){
            try {
                if(roomIdresultSet.next()){
                    if(roomIdresultSet.getString(2)!=null&&!roomIdresultSet.getString(2).equals("")) {
                        roomid.setText(roomIdresultSet.getString(2));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 获取数据库中的个人设置
        ResultSet systemStateresultSet =  ConfInfo.databaesUtil.select(ConfInfo.Database_SelectSystemDataWhereName+"'systemState'");
        if(systemStateresultSet!=null){
            try {
                if(systemStateresultSet.next()){
                    if(systemStateresultSet.getString(2)!=null&&!systemStateresultSet.getString(2).equals("")) {
                        JSONObject data = new JSONObject(systemStateresultSet.getString(2));
                        ConfInfo.systemState.isLiveMsg = data.getBoolean("isLiveMsg");
                        ConfInfo.systemState.isGreetMaster = data.getBoolean("isGreetMaster");
                        ConfInfo.systemState.istEffectInfo = data.getBoolean("istEffectInfo");
                        ConfInfo.systemState.isIgnoreSpicystrip = data.getBoolean("isIgnoreSpicystrip");
//                        ConfInfo.systemState.isSend30 = data.getBoolean("isSend30");
                        ConfInfo.systemState.isSystemSendLock = data.getBoolean("isSystemSendLock");
                        ConfInfo.systemState.isInteractWord = data.getBoolean("isInteractWord");
                        ConfInfo.systemState.isThankFollow = data.getBoolean("isThankFollow");
                        ConfInfo.systemState.isThankShare = data.getBoolean("isThankShare");

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 获取是否自动开启30字
        String isSend30ReturnData = HttpClient.sendGet(ConfInfo.isSend30URL,ConfInfo.confData.getCookie());
        if(!isSend30ReturnData.equals("")){
            try {
                JSONObject data = new JSONObject(isSend30ReturnData);
                if(data.getJSONObject("data").getJSONArray("info").length()>0){
                    ConfInfo.systemState.isSend30 = true;
                }else{
                    ConfInfo.systemState.isSend30 = false;
                    isSend30State.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 更新控制器状态
        updateStatus();

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                ConfInfo.control_inlet.Clear();
            }
        });
        if (ConfInfo.AppSystemId == "A"){
            logout.setVisible(false);
        }


        modify.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelationUPNav relationUPNav = new RelationUPNav();
                if(ConfInfo.RelationUP){
                    relationUPNav.Modify(ConfInfo.getLiveRoomUserInfo.getRoomUseruid(),"2","0");
                }else{
                    relationUPNav.Modify(ConfInfo.getLiveRoomUserInfo.getRoomUseruid(),"1","0");
                }
                relationUPNav.getRelationUP(ConfInfo.getLiveRoomUserInfo.getRoomUseruid());
                updateRelationUP();
            }
        });


        test.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfInfo.putShowUtil.PutDMUtil("定制"+" | "+getFormat()+" | 如初关注抽奖 解析出关注用户总数"+RuchuUtil.analysisUsers(), Color.BLUE);
                ConfInfo.putShowUtil.PutDMUtil("定制"+" | "+getFormat()+" | 如初关注抽奖 解析出奖品总数"+RuchuUtil.analysisWeightLotterys(), Color.BLUE);
            }
        });

        this.setVisible(true);
    }

    /**
     * 开启连接
     */
    public void startLink(){
        String roomidMsg = ConfInfo.liveRoom.isLiveRoom(getRoomid());
        // 判断房间号是否正常
        if(!roomid.getText().equals("")&&roomidMsg==null){
            //开启连接
            ConfInfo.systemState.isLink=true;
            link.setText("断开");
            roomid.setEnabled(false);
            // 判断是否可以发送弹幕是否打开可感谢按钮
            if(null!=ConfInfo.liveUserInfo||!ConfInfo.confData.getCookie().equals("")){
                thank.setEnabled(true);
            }
            ConfInfo.control_inlet.Connect();
            ConfInfo.getLiveRoomUserInfo = new GetLiveRoomUserInfo();
            ConfInfo.SendBarrageList = new ArrayList<SendBarrageMap>();
            UPLabel.setText("UP "+ConfInfo.getLiveRoomUserInfo.getRoomUseruname());
//            liveContentLabel.setText(""+ConfInfo.getLiveRoomUserInfo.getLiveContent()); // 直播通知
            // 获取房间信息
            ConfInfo.liveRoomInfo = new LiveRoomInfoNav().getLiveRoomInfo(ConfInfo.liveRoom.getUid());
            if(ConfInfo.liveRoomInfo!=null){
                liveTitle.setText(ConfInfo.liveRoomInfo.getTitle());
                setLiveStatus(ConfInfo.liveRoomInfo.getLiveStatus()==1);
            }


            ConfInfo.dingtalk.OpenLink();

            // 连接 小兔团 如初 terkong 直播间时自动显示隐藏功能
            if(getRoomid().equals("7471685")||getRoomid().equals("1757608")||getRoomid().equals("9938182")){
//                isInteractWordStatus.setVisible(true);
//                isThankFollowStatus.setVisible(true);
//                isThankShareStatus.setVisible(true);
//                ConfInfo.systemState.isInteractWord = false;
//                ConfInfo.systemState.isThankFollow = false;
//                ConfInfo.systemState.isThankShare = false;

            }else{
//                isInteractWordStatus.setVisible(false);
//                isThankFollowStatus.setVisible(false);
//                isThankShareStatus.setVisible(false);
//                ConfInfo.systemState.isInteractWord = false;
//                ConfInfo.systemState.isThankFollow = false;
//                ConfInfo.systemState.isThankShare = false;
            }

            // TODO 如初关注抽奖
            if(getRoomid().equals("1757608")){
                RuchuUtil.analysisUsers();
                RuchuUtil.analysisWeightLotterys();
                test.setVisible(true);
                isFollowDrawStatus.setVisible(true);
            }

            updateRelationUP();
        }else{
            setNotice(1,roomidMsg,Color.RED);
        }
    }

    public void setLiveTitleTitle(String title){
        liveTitle.setText(title);
    }

    /**
     * 关闭连接
     */
    public void closeLink(){
        //断开连接
        if(ConfInfo.systemState.isThank){
            closeThank();
        }
        ConfInfo.systemState.isLink=false;
        ConfInfo.systemState.isThank=false;
        link.setText("连接");
        roomid.setEnabled(true);
        thank.setEnabled(false);
        ConfInfo.dingtalk.CloseLink();
        ConfInfo.control_inlet.Disconnect();
        ConfInfo.getLiveRoomUserInfo = null;
        ConfInfo.SendBarrageList = new ArrayList<SendBarrageMap>();
        ConfInfo.systemState.isLiveStatus = false; //展示直播状态
        modify.setVisible(false); // 隐藏关注按钮
        ConfInfo.RelationUP = null; //重置关注状态

        // TODO 如初关注抽奖
        if(getRoomid().equals("1757608")){
            isFollowDrawStatus.setVisible(false);
            test.setVisible(false);
        }

        updateStatus();
        emptyPopularity();
    }

    /**
     * 开启感谢
     */
    public void startThank(){
        //开启感谢
        ConfInfo.systemState.isThank=true;
        String putDM =  "系统"+" | "+getFormat()+" | "+"开启感谢" +" | "+"ID："+ConfInfo.liveRoom.getRoom_id() +" | "+"UID："+ConfInfo.liveUserInfo.getUid()+" | "+"昵称："+ ConfInfo.liveUserInfo.getUname()+"\t";
        ConfInfo.putShowUtil.PutDMUtil(putDM, Color.BLUE);
        thank.setText("关闭感谢");
    }

    /**
     * 关闭感谢
     */
    public void closeThank(){
        //关闭感谢
        ConfInfo.systemState.isThank=false;
        String putDM =  "系统"+" | "+getFormat()+" | "+"关闭感谢" +" | "+"ID："+ConfInfo.liveRoom.getRoom_id() +" | "+"UID："+ConfInfo.liveUserInfo.getUid()+" | "+"昵称："+ ConfInfo.liveUserInfo.getUname()+"\t";
        ConfInfo.putShowUtil.PutDMUtil(putDM,Color.RED);
        thank.setText("开启感谢");
    }


    /**
     * 设置直播状态
     * @param status
     */
    public void setLiveStatus(Boolean status){
        ConfInfo.systemState.isLiveStatus = status; //展示直播状态
        updateStatus();
    }

    /**
     * 更新控制器状态
     */
    public void updateStatus(){
        // 弹幕锁
        if(ConfInfo.systemState.isSystemSendLock) isSystemSendLockState.setForeground(Color.green);
        else isSystemSendLockState.setForeground(Color.black);
        // 直播提醒
        if(ConfInfo.systemState.isLiveMsg) isLiveMsgState.setForeground(Color.green);
        else isLiveMsgState.setForeground(Color.black);
        // 欢迎老爷
        if(ConfInfo.systemState.isGreetMaster) isGreetMasterState.setForeground(Color.green);
        else isGreetMasterState.setForeground(Color.black);
        // 动画信息
        if(ConfInfo.systemState.istEffectInfo) istEffectInfoState.setForeground(Color.green);
        else istEffectInfoState.setForeground(Color.black);
        // 忽略辣条
        if(ConfInfo.systemState.isIgnoreSpicystrip) isIgnoreSpicystripState.setForeground(Color.green);
        else isIgnoreSpicystripState.setForeground(Color.black);
        // 忽略辣条
        if(ConfInfo.systemState.isSend30) isSend30State.setForeground(Color.green);
        else isSend30State.setForeground(Color.black);
        // 欢迎用户
        if(ConfInfo.systemState.isInteractWord) isInteractWordStatus.setForeground(Color.green);
        else isInteractWordStatus.setForeground(Color.black);
        // 关注感谢
        if(ConfInfo.systemState. isThankFollow) isThankFollowStatus.setForeground(Color.green);
        else isThankFollowStatus.setForeground(Color.black);
        // 分享感谢
        if(ConfInfo.systemState.isThankShare) isThankShareStatus.setForeground(Color.green);
        else isThankShareStatus.setForeground(Color.black);
        // 是否直播
        if(ConfInfo.systemState.isLiveStatus) isLiveStatus.setForeground(Color.green);
        else isLiveStatus.setForeground(Color.WHITE);


        // TODO 如初关注抽奖
            if(ConfInfo.systemState.isFollowDraw) isFollowDrawStatus.setForeground(Color.green);
            else isFollowDrawStatus.setForeground(Color.black);

    }

    //

    /**
     * 修改发送状态
     * @param state 状态 0发送失败，1发送成功，2发送中，3不能为空
     */
    public void updateSendStatus(int state){
//        if(state==1){
            sendOver();
//        }
        switch (state){
            case 0: // 发送失败
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(100);
                            sendState.setForeground(Color.RED);
                            Thread.sleep(100);
                            sendState.setForeground(Color.black);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case 1: // 发送成功
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(100);
                            sendState.setForeground(Color.green);
                            Thread.sleep(100);
                            sendState.setForeground(Color.black);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case 2: // 发送中
                sendState.setForeground(Color.YELLOW);
                break;
            case 3: // 不能发送空的弹幕
                setNotice(1,"不能发送空的弹幕",Color.RED);
                break;
        }

    }

    /**
     * 解除发送状态
     */
    public void sendOver(){
        sendText.setEnabled(true);
        send.setEnabled(true);
        sendText.grabFocus();
    }

    /**
     * 获取房间号
     * @return 房间号
     */
    public String getRoomid() {
        return roomid.getText();
    }

    /**
     * 获取弹幕发送文本
     * @return 弹幕发送文本
     */
    public String getSendText() {
        return sendText.getText();
    }

    /**
     * 设置弹幕发送文本
     * @param msg 弹幕发送文本
     */
    public void setSendText(String msg) {
        sendText.setText(msg);
    }

    /**
     * 获取弹幕容器
     * @return 弹幕容器
     */
    public JEditorPane getEditorPane() {
        return editorPane;
    }

    /**
     * 设置人气值，并且心跳
     * @param data 人气值信息
     */
    public void setPopularity(String data){
        popularity.setText(data);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    heartbeat.setForeground(Color.YELLOW);
                    popularity.setForeground(Color.YELLOW);
                    Thread.sleep(300);
                    heartbeat.setForeground(Color.green);
                    popularity.setForeground(Color.green);
                    Thread.sleep(300);
                    heartbeat.setForeground(Color.WHITE);
                    popularity.setForeground(Color.BLACK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     * 清空人气值
     */
    public void emptyPopularity(){
        popularity.setText("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    heartbeat.setForeground(Color.YELLOW);
                    popularity.setForeground(Color.YELLOW);
                    Thread.sleep(300);
                    heartbeat.setForeground(Color.green);
                    popularity.setForeground(Color.green);
                    Thread.sleep(300);
                    heartbeat.setForeground(Color.WHITE);
                    popularity.setForeground(Color.BLACK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 设置系统通知
     * @param type 通知样式 1系统，2通知
     * @param msg 通知信息
     * @param color 通知颜色
     */
    public void setNotice(int type, String msg, Color color) {
        switch (type){
            case 1:
                noticeTextField.setText("系统：" + msg);
                break;
            default:
                noticeTextField.setText("通知：" + msg);
                break;
        }
        if(color!=null)noticeTextField.setForeground(color);
        else noticeTextField.setForeground(Color.RED);


    }

    /**
     * 设置次要通知
     * @param type 通知样式
     * @param msg 通知信息
     * @param color 通知颜色
     */
    public void setMinorNotice(int type, String msg, Color color) {
        switch (type){
            default:
                minorNotice.setText(msg);
                break;
        }
        if(color!=null)minorNotice.setForeground(color);
        else minorNotice.setForeground(Color.BLUE);


    }

    /**
     * 设置小时榜
     * @param msg 小时榜信息
     */
    public void setRoomRank(String msg) {
        if(msg!=null){
            roomRank.setForeground(Color.ORANGE);
            roomRank.setText(msg);
        } else {
            roomRank.setForeground(Color.WHITE);
            roomRank.setText("");
        }


    }

    /**
     * 更新关注状态
     */
    public void updateRelationUP(){
        // 判断是否关注主播
        RelationUPNav relationUPNav = new RelationUPNav();
        modify.setVisible(true);
        if(relationUPNav.getRelationUP(ConfInfo.getLiveRoomUserInfo.getRoomUseruid())){
            modify.setText("取关");
        }else{
            modify.setText("关注");
        }
    }



    /**
     * 将弹幕滚到最后一行
     */
    public void scrolltoEnd(){
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    /**
     * 控制命令处理
     */
    public void controlCommand(){
        InOutPutUtil.outPut("执行控制命令" + sendText.getText().substring(6));
        switch (sendText.getText().substring(6)){
            case "open hide":{ //开启隐藏功能呢
                setNotice(1,"此版本没有隐藏功能",Color.RED);
//                isInteractWordStatus.setVisible(true);
//                isThankFollowStatus.setVisible(true);
//                isThankShareStatus.setVisible(true);
                break;
            }
            case "exit system":{ // 退出系统
                System.exit(0);
                break;
            }
            case "start link":{ // 连接
                startLink();
                break;
            }
            case "close link":{ // 断开
                closeLink();
                break;
            }
            case "open send lock":{ // 开启弹幕锁
                ConfInfo.systemState.isSystemSendLock = true;
                ConfInfo.barrage.updateStatus();
                break;
            }
            case "close send lock":{ // 关闭弹幕锁
                ConfInfo.systemState.isSystemSendLock = false;
                ConfInfo.barrage.updateStatus();
                break;
            }
            case "clean up":{ // 清理弹幕
                editorPane.setText("");
                break;
            }
            default:{
                InOutPutUtil.outPut("未知控制命令");
            }
        }
        sendText.setText("");
    }

}
