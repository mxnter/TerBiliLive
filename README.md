# TerBiliLive - Beta06  [![Ter BiliLive](https://img.shields.io/badge/Ter-BiliLive-orange.svg)]() [![Beta 06](https://img.shields.io/badge/Beta-06-ff69b4.svg)]()  [![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=102)]()

# 致歉:
B站更改了获取直播间信息的接口校验，由于作者校验 cookie 使用的是作者直播间，现在导致不关注作者直播间将显示 未检测到网络，请连接网络再试，现在的解决方案是将弹幕姬账号关注 9938182 这个直播间即可使用，由于作者最近需要考试，将在考试结束后尽快修复这个问题，先准备更新一个并不校验 cookie 的版本，请自行判断 cookie 是否有效，再次表示抱歉感谢您的使用。  




### Bilibili Live Barrage kyi,Using Java .
### 哔哩哔哩直播弹幕姬，使用 Java 。  

##### 作者近期加班和考试，尽力更新。  

# 告知：
Beta06[D.Beta08-0217]-[H.Beta9-0131]-[G.Beta03] 及以后版本  
将记录您的 IP、Mac、主机名等 到服务器，以方便作者统计使用人数。 
 
Beta06[D.Beta08-0217]-[H.Beta9-0131]-[G.Beta03] 及以前版本  
将记录 开关播时间 房间号 发送到服务器(进行统计,将在新版本中已移出项，改用IP地址)。  

如有其他问题可将问题反馈到 TerBiliLive@outlook.com 邮箱。

## 目录
* [一丶简述](#一丶简述)
* [二丶Ui展示](#二丶Ui展示)
* [三丶帮助](#三丶帮助)
* [四丶用户协议](#四丶用户协议)
* [五丶版本记录](#五丶版本记录)

<br>

# 一丶简述

#### 实现功能
1. 手动发送弹幕
2. 定时发送弹幕
3. 接收弹幕
4. 根据礼物感谢
5. 欢迎老爷舰长进入房间
6. 接入图灵机器人可以聊天
7. 钉钉群推送

#### 现存问题
1. 随机出现假死现象

### 发布版本下载：[Beta](https://github.com/mxnter/TerBiliLive/releases)


### 当前下载地址：[GitHub](https://raw.githubusercontent.com/mxnter/TerBiliLive/master/out/artifacts/TerBiliLive_jar/TerBiliLive.jar)


<center>注意：（如需使用请在本机配置好Java环境）</center>

<br><br><br>

### <center>Cookie 请自行在浏览器内获取，作者还没写登陆呢！</center> [获取Cookie方法](#四丶帮助)

<br><br>
# 二丶Ui展示
###### <center>Ui展示</center>

### 1.欢迎界面
![欢迎界面](https://raw.githubusercontent.com/mxnter/TerBiliLive/master/MDImg/hi.png)

### 2.登陆界面
![登陆界面](https://raw.githubusercontent.com/mxnter/TerBiliLive/master/MDImg/dl.png)

### 3.弹幕姬界面
![弹幕姬界面](https://raw.githubusercontent.com/mxnter/TerBiliLive/master/MDImg/dmj.png)






# 三丶帮助

### 弹幕控制
```
(#号后需要一个半角空格)
# 关闭老爷
# 开启老爷
# 开启感谢
# 关闭感谢
# 忽略辣条
# 感谢辣条
# 开启舰长
# 关闭舰长
```

### 获取Cookie
![获取Cookie](https://raw.githubusercontent.com/mxnter/TerBiliLive/master/MDImg/getcookie.png)


# 四丶用户协议

>尊敬的用户：  
 　　您好，感谢您使用 TerBiliLive 弹幕姬。  
 　　在您使用之前我们将告知您，为了统计弹幕姬使用人和次数，  
 我们将统计部分信息，例如 IP、Mac、主机名等。这将发送到我们  
 的服务上，以帮助开发者了解是否有用户使用这个软件。  
 　　开发者不会发送您的 Cookie，您的Cookie存储在本地，我们在  
 某些版本上使用了加密手段加密了您的Cookie（由于部分电脑无法解密，  
 我们暂时将这个功能关闭）。  
 
 >如果您觉得我们可以信赖，您可以选择 “是”。如果您不想统计您的信  
 息，请选择 “否”。  
 
 >再次感谢您的使用。  

# 五丶版本记录

>##### Beta06[D.Beta07]-[H.Beta8]-[G.Beta03]  
> 修复无法发送弹幕

>##### Beta06[D.Beta08-0131]-[H.Beta9-0131]-[G.Beta03]  
> 更新房间禁言关掉全部发送消息 并且钉钉群提示

>##### Beta06[D.Beta08-0200]-[H.Beta9-0131]-[G.Beta03] - 发布版  
> 更新弹幕显示时的颜色

>##### Beta06[D.Beta08-0203]-[H.Beta9-0131]-[G.Beta03]  
> 更新禁言显示

>##### Beta06[D.Beta08-0204]-[H.Beta9-0131]-[G.Beta03]  
> 重写整合礼物 全部整合(不稳定版)

>##### Beta06[D.Beta08-0205]-[H.Beta9-0131]-[G.Beta03] 
> 重写发送弹幕 线程 - 由于线程会重复发送(不稳定版)

>##### Beta06[D.Beta08-0206]-[H.Beta9-0205]-[G.Beta03] 
> 加快显示弹幕速度 (不稳定版)  
> 忽略辣条(不稳定版)  
> 维护回复姬线程 (不稳定版)  

>##### Beta06[D.Beta08-0217]-[H.Beta9-0205]-[G.Beta03]  
> 新增 忽略辣条
> 修改 部分礼物感谢 2 次

>##### V.Beta06[D.Beta08-0317]-[H.Beta9-0317]-[G.Beta03] - 发布版(Beta06 - Issue01)
> 新增 用户协议授权  
> 修复 不开启感谢无法发送弹幕  
> 修改 为了保证稳定 延迟弹幕显示时间  
