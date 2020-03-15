package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Img.ImageBroker;
import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utils.DevLogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TerWindowListener implements WindowListener {

	public JFrame win;

	public TerWindowListener(JFrame win) {
		this.win = win;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/*
	 * 关闭窗口时，自动调用此方法
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		switch (win.getName()){
			case "master":{
				win.dispose();
				break;
			}
			default:{
				ConfInfo.control_fun.Preservation();
				DevLogUtil.printf("您正在关闭窗口,启动保存信息");
			}
		}



	}

	/*
	 * 关闭窗口之后，自动调用此方法
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		DevLogUtil.printf("您关闭了窗口...");
	}

	/*
	 * 最小化窗口之后，自动调用此方法
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

		switch (win.getName()){
			case "Console":{
				win.setVisible(false);
				ImageIcon trayImg = ImageBroker.loadImageIcon("logoa.png");// 托盘图标
				TrayIcon trayIcon = new TrayIcon(trayImg.getImage(), ConfInfo.AppName.get(ConfInfo.AppSystemId), new PopupMenu());
				trayIcon.setImageAutoSize(true);
				trayIcon.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 1) {// 单击 1 双击 2
							win.setVisible(true);
							win.setExtendedState(JFrame.NORMAL);
							win.toFront();
							ConfInfo.systemTray.remove(trayIcon);
						}
					}

				});

				try {
					ConfInfo.systemTray.add(trayIcon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
			default:{
				DevLogUtil.printf("您把窗口最小化了....");
			}
		}




	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}