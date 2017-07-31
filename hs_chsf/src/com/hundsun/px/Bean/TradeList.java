package com.hundsun.px.Bean;

import java.util.LinkedList;


/**
 * 交易队列
 *
 * @see 后期可以加入对象管理方法
 */
public class TradeList{

	private String zqdm;
	private LinkedList<ReqBean> buyList;
	private LinkedList<ReqBean> selList;

	/**
	 * 初始化方法
	 */
	public TradeList() {
		buyList = new LinkedList<ReqBean>();
		selList = new LinkedList<ReqBean>();
		// for (int i = 0; i < PubString.zqdlMax; i++) {
		// buyList.add(new ReqBean());
		// selList.add(new ReqBean());
		// }
	}

	public LinkedList<ReqBean> getBuyList(){
		return buyList;
	}

	public LinkedList<ReqBean> getSelList(){
		return selList;
	}

	/**
	 * 添加请求到买队列
	 */
	public LinkedList<ReqBean> AddToBuyList(ReqBean reqBean) {
		buyList.addLast(reqBean);
		return buyList;
	}

	/**
	 * 添加请求到卖队列
	 */
	public LinkedList<ReqBean> AddToSelList(ReqBean reqBean) {
		return selList;
	}

	/**
	 * 删除卖出队列头并进行留痕操作
	 *
	 * @see 这个留痕指的是在返回队列中加入操作信息
	 */
	public LinkedList<ReqBean> DelFromSelList() {
		return selList;
	}

	/**
	 * 删除买入队列头并进行留痕操作
	 *
	 * @see 这个留痕指的是在返回队列中加入操作信息
	 */
	public LinkedList<ReqBean> DelFromBuyList() {
		return buyList;
	}

	public String getZqdm() {
		return zqdm;
	}

	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}

	public synchronized  void test(){
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
