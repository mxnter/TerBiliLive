package com.TerBiliLive.TerBiliLive;

import com.TerBiliLive.Info.ConfInfo;
import com.TerBiliLive.Utiliy.DevLogUtil;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

public class TerWindowListener implements WindowListener {

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
		ConfInfo.control_fun.Preservation();
		DevLogUtil.printf("您正在关闭窗口,启动保存信息");

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
		DevLogUtil.printf("您把窗口最小化了....");
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