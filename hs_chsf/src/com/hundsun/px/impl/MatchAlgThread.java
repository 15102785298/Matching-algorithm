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
				case '1': // 买入方向委托
					CalculatBuy(tradeList, reqBean);
					break;
				case '2': // 卖出方向委托
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
	 * 对于卖出方向的请求处理
	 *
	 * @param czdl
	 *            该交易对应的股票队列
	 * @param selReq
	 *            卖出请求
	 * @return res 返回对象
	 */
	private void CalculatSel(TradeList czdl, ReqBean selReq) {
		LinkedList<ReqBean> buyList = czdl.getBuyList();
		// 如果买入队列不为空，则逐条计算
		boolean isDeal = false;
		ReqBean buyReqInList = null;
		double selJg = selReq.getWtjg();
		double buyJg = -1;
		while (buyList.size() > 0) {
			// 此时买入队列一定不为空
			buyReqInList = buyList.getFirst();
			buyJg = buyReqInList.getWtjg();
			if (selJg <= buyJg) { // 如果当前买入队列中的最高价格大于等于卖出请求价格,代表会有成交
				int selSl = selReq.getWtsl();
				int buySl = buyReqInList.getWtsl();
				if (selSl == buySl) {
					// 打包返回信息
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// 操作买入队列，消耗该行
					buyList.removeFirst();
					return ;
				} else if (selSl < buySl) { // 如果卖出请求委托数量小于买入队列买入数量
					// 打包返回信息
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// 操作买入队列，更新队列头数据
					buyReqInList.setWtsl(buySl - selSl); // 更新买入队列数据
					return ;
				} else { // 如果卖出请求委托数量大于买入队列头委托买入数量，则代表会有多次交易
					// 打包返回信息
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					// 消耗买入行
					buyList.removeFirst();
					// 更新卖出请求信息
					selReq.setWtsl(selSl - buySl);
					isDeal = true;
				}
			} else {
				break;
			}
		}
		if (isDeal) { // 如果是有多次交易这种情况，此时该任务一定是插入到卖出队列头
			czdl.getSelList().addFirst(selReq);
			return ;
		} else {
			// 插入卖出队列（由低到高排序）
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
	 * 对于买入方向的请求处理
	 *
	 * @param czdl
	 *            该交易对应的股票队列
	 * @param buyReq
	 *            买入请求
	 * @return res 返回对象
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
			if (selJg <= buyJg) { // 如果当前卖出表中价格最低的价格小于等于买入价格
				int selSl = selReqInList.getWtsl();
				int buySl = buyReq.getWtsl();
				if (selSl == buySl) { // 刚好一对一成交了
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					selList.removeFirst();
					return ;
				} else if (selSl > buySl) { // 卖出数量大于买入数量
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					returnList.add(repBean);
					selReqInList.setWtsl(selSl - buySl);
					return ;
				} else { // 卖出数量小于买入数量
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
