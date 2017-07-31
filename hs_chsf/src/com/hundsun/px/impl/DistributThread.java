package com.hundsun.px.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Pub.PubString;

public class DistributThread implements Runnable {

	private List<ReqBean> reqList = null;
	public static Map<String, MatchAlgThread> chdl = new ConcurrentHashMap<String, MatchAlgThread>();
	public static List<RepBean> res = new LinkedList<RepBean>();

	public void setValue(List<ReqBean> reqList) {
		this.reqList = reqList;
		// 根据证券代码字段初始化交易Map
		String[] allZqdm = PubString.allZQDM.split(",");
		int length = allZqdm.length;
		for (int i = 0; i < length; i++) {
			MatchAlgThread the = new MatchAlgThread();
			chdl.put(allZqdm[i], the);
			new Thread(the).start();
		}
	}

	@Override
	public void run() {

		MatchAlgThread matchAlgThread = null;
		// 分发请求
		for (Iterator<ReqBean> iterator = reqList.iterator(); iterator.hasNext();) {
			ReqBean nowReq = iterator.next();
			matchAlgThread = chdl.get(nowReq.getZqdm());
			matchAlgThread.RecReq(nowReq);
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<RepBean> repList = new LinkedList<RepBean>();
		String[] allZqdm = PubString.allZQDM.split(",");
		int length = allZqdm.length;

		for (int i = 0; i < length; i++) {
			MatchAlgThread the = chdl.get(allZqdm[i]);
			repList.addAll(the.getResRep());
		}
		// 打印结果
		for (RepBean repBean : repList) {
			repBean.sc();
		}
	}

}
