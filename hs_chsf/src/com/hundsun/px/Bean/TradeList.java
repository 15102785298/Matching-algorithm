package com.hundsun.px.Bean;

import java.util.LinkedList;


/**
 * ���׶���
 *
 * @see ���ڿ��Լ�����������
 */
public class TradeList{

	private String zqdm;
	private LinkedList<ReqBean> buyList;
	private LinkedList<ReqBean> selList;

	/**
	 * ��ʼ������
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
	 * ������������
	 */
	public LinkedList<ReqBean> AddToBuyList(ReqBean reqBean) {
		buyList.addLast(reqBean);
		return buyList;
	}

	/**
	 * �������������
	 */
	public LinkedList<ReqBean> AddToSelList(ReqBean reqBean) {
		return selList;
	}

	/**
	 * ɾ����������ͷ���������۲���
	 *
	 * @see �������ָ�����ڷ��ض����м��������Ϣ
	 */
	public LinkedList<ReqBean> DelFromSelList() {
		return selList;
	}

	/**
	 * ɾ���������ͷ���������۲���
	 *
	 * @see �������ָ�����ڷ��ض����м��������Ϣ
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
