package com.hundsun.px.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Bean.TradeList;

public class MatchAlgThread implements Runnable {

	private TradeList tradeList = new TradeList();
	private Vector<ReqBean> nowReq = new Vector<ReqBean>();
	private List<RepBean> returnList = new LinkedList<RepBean>();

	public List<RepBean> getResRep(){
		if(nowReq.isEmpty()){
		//	System.out.println(tradeList.getZqdm()+" over");
			return returnList;
		}else{
			return null;
		}
	}

	public void run() {
		ReqBean reqBean = null;
		while (true) {
			if (!nowReq.isEmpty()) {
				reqBean = nowReq.firstElement();
				char bs = reqBean.getBs();
				switch (bs) {
				case '1': // ���뷽��ί��
					CalculatBuy(tradeList, reqBean);
					break;
				case '2': // ��������ί��
					CalculatSel(tradeList, reqBean);
					break;
				default:
					break;
				}
				nowReq.remove(0);
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void RecReq(ReqBean reqBean) {
		this.nowReq.add(reqBean);
	}

	/**
	 * �������������������
	 *
	 * @param czdl
	 *            �ý��׶�Ӧ�Ĺ�Ʊ����
	 * @param selReq
	 *            ��������
	 * @return res ���ض���
	 */
	private void CalculatSel(TradeList czdl, ReqBean selReq) {
		LinkedList<ReqBean> buyList = czdl.getBuyList();
		// ���������в�Ϊ�գ�����������
		boolean isDeal = false;
		ReqBean buyReqInList = null;
		double selJg = selReq.getWtjg();
		double buyJg = -1;
		while (buyList.size() > 0) {
			// ��ʱ�������һ����Ϊ��
			buyReqInList = buyList.getFirst();
			buyJg = buyReqInList.getWtjg();
			if (selJg <= buyJg) { // �����ǰ��������е���߼۸���ڵ�����������۸�,������гɽ�
				int selSl = selReq.getWtsl();
				int buySl = buyReqInList.getWtsl();
				if (selSl == buySl) {
					// ���������Ϣ
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// ����������У����ĸ���
					buyList.removeFirst();
					return ;
				} else if (selSl < buySl) { // �����������ί������С�����������������
					// ���������Ϣ
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// ����������У����¶���ͷ����
					buyReqInList.setWtsl(buySl - selSl); // ���������������
					return ;
				} else { // �����������ί�����������������ͷί�������������������ж�ν���
					// ���������Ϣ
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// ����������
					buyList.removeFirst();
					// ��������������Ϣ
					selReq.setWtsl(selSl - buySl);
					isDeal = true;
				}
			} else {
				break;
			}
		}
		if (isDeal) { // ������ж�ν��������������ʱ������һ���ǲ��뵽��������ͷ
			czdl.getSelList().addFirst(selReq);
			return ;
		} else {
			// �����������У��ɵ͵�������
			LinkedList<ReqBean> selList = czdl.getSelList();
			int selSize = selList.size();
			double selWtjg = selReq.getWtjg();
			int j;
			for (j = 0; j < selSize; j++) {
				if (selList.get(j).getWtjg() > selWtjg) {
					break;
				}
			}
			selList.add(j, selReq);
			return ;
		}
	}

	/**
	 * �������뷽���������
	 *
	 * @param czdl
	 *            �ý��׶�Ӧ�Ĺ�Ʊ����
	 * @param buyReq
	 *            ��������
	 * @return res ���ض���
	 */
	private void CalculatBuy(TradeList czdl, ReqBean buyReq) {
		LinkedList<ReqBean> selList = czdl.getSelList();
		boolean isDeal = false;
		ReqBean selReqInList = null;
		double selJg = 9999999.00;
		double buyJg = buyReq.getWtjg();
		while (selList.size() > 0) {
			selReqInList = selList.getFirst();
			selJg = selReqInList.getWtjg();
			if (selJg <= buyJg) { // �����ǰ�������м۸���͵ļ۸�С�ڵ�������۸�
				int selSl = selReqInList.getWtsl();
				int buySl = buyReq.getWtsl();
				if (selSl == buySl) { // �պ�һ��һ�ɽ���
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					selList.removeFirst();
					return ;
				} else if (selSl > buySl) { // ��������������������
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					selReqInList.setWtsl(selSl - buySl);
					return ;
				} else { // ��������С����������
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, selSl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					selList.removeFirst();
					buyReq.setWtsl(buySl - selSl);
					isDeal = true;
				}
			} else {
				break;
			}
		}
		if (isDeal) {
			czdl.getBuyList().addFirst(buyReq);
			return ;
		} else {
			LinkedList<ReqBean> buyList = czdl.getBuyList();
			int buySize = buyList.size();
			double buyWtjg = buyReq.getWtjg();
			int j;
			for (j = 0; j < buySize; j++) {
				if (buyList.get(j).getWtjg() < buyWtjg) {
					break;
				}
			}
			buyList.add(j, buyReq);
			return ;
		}
	}

}
