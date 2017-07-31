package com.hundsun.px.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Bean.TradeList;
import com.hundsun.px.Interface.chsfInterface;
import com.hundsun.px.Pub.PubString;
// import com.hundsun.px.Tools.IdWorker;

/**
 * 二分查找的撮合算法
 */
public class MatchAlgImpl implements chsfInterface {

	private static Map<String, TradeList> chdl = new HashMap<String, TradeList>();
	// private static IdWorker idWorker = new IdWorker(1000);
	private static List<String> zqdmList = new ArrayList<>();

	/** 调用初始化方法可以加快代码执行速度，不调用也不会崩 =.= */
	public void init() {
		// 根据证券代码字段初始化交易Map
		String[] allZqdm = PubString.allZQDM.split(",");
		int length = allZqdm.length;
		for (int i = 0; i < length; i++) {
			zqdmList.add(allZqdm[i]); // 证券代码保存
			chdl.put(allZqdm[i], new TradeList());
		}
	}

	@Override
	public List<RepBean> MatchCalculation(ReqBean reqBean) {
		String zqdm = reqBean.getZqdm();
		char bs = reqBean.getBs();
		List<RepBean> returnList = null;
		TradeList czdl = chdl.get(zqdm);
		if (czdl == null) { // 当第一次生成某证券帐号
			czdl = new TradeList();
			zqdmList.add(zqdm);
			chdl.put(zqdm, czdl);
		}
		switch (bs) {
		case '1': // 买入方向委托
			returnList = CalculatBuy(czdl, reqBean);
			break;
		case '2': // 卖出方向委托
			returnList = CalculatSel(czdl, reqBean);
			break;
		default:
			break;
		}
		return returnList;
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
	private List<RepBean> CalculatSel(TradeList czdl, ReqBean selReq) {
		LinkedList<ReqBean> buyList = czdl.getBuyList();
		List<RepBean> res = new LinkedList<RepBean>();
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
					res.add(repBean);
					// 操作买入队列，消耗该行
					buyList.removeFirst();
					return res;
				} else if (selSl < buySl) { // 如果卖出请求委托数量小于买入队列买入数量
					// 打包返回信息
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					res.add(repBean);
					// 操作买入队列，更新队列头数据
					buyReqInList.setWtsl(buySl - selSl); // 更新买入队列数据
					return res;
				} else { // 如果卖出请求委托数量大于买入队列头委托买入数量，则代表会有多次交易
					// 打包返回信息
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, buySl, 1, System.currentTimeMillis());
					res.add(repBean);
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
			return res;
		} else {
			// 插入卖出队列（由低到高排序）
			LinkedList<ReqBean> selList = czdl.getSelList();
			int selSize = selList.size();
			double selWtjg = selReq.getWtjg();
			selList.add(selSearch(selList, 0, selSize, selWtjg), selReq);
			return res;
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
	private List<RepBean> CalculatBuy(TradeList czdl, ReqBean buyReq) {
		LinkedList<ReqBean> selList = czdl.getSelList();
		List<RepBean> res = new LinkedList<RepBean>();
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
					res.add(repBean);
					selList.removeFirst();
					return res;
				} else if (selSl > buySl) { // 卖出数量大于买入数量
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					res.add(repBean);
					selReqInList.setWtsl(selSl - buySl);
					return res;
				} else { // 卖出数量小于买入数量
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, selSl, 1, System.currentTimeMillis());
					res.add(repBean);
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
			return res;
		} else {
			LinkedList<ReqBean> buyList = czdl.getBuyList();
			int buySize = buyList.size();
			double buyWtjg = buyReq.getWtjg();
			buyList.add(buySearch(buyList, 0, buySize, buyWtjg), buyReq);
			return res;
		}
	}

	// 低价优先
	public static int selSearch(LinkedList<ReqBean> a, int fromIndex, int toIndex, double value) {
		if (a.isEmpty() || a.getFirst().getWtjg() > value) {
			return 0;
		}
		if (a.getLast().getWtjg() < value) {
			return toIndex;
		}
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			double midVal = a.get(mid).getWtjg();
			if (midVal < value) {
				low = mid + 1;
			} else if (midVal > value) {
				high = mid - 1;
			} else {
				int rang = toIndex - 1;
				while (a.get(mid).getWtjg() == value) {
					if (mid == rang) {
						return ++mid;
					}
					mid++;
				}
				return mid;
			}
		}
		return Math.max(high, low);
	}

	// 高价优先
	public static int buySearch(LinkedList<ReqBean> a, int fromIndex, int toIndex, double value) {
		if (a.isEmpty() || a.getFirst().getWtjg() < value) {
			return 0;
		}
		if (a.getLast().getWtjg() > value) {
			return toIndex;
		}
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			double midVal = a.get(mid).getWtjg();
			if (midVal > value) {
				low = mid + 1;
			} else if (midVal < value) {
				high = mid - 1;
			} else {
				int rang = toIndex - 1;
				while (a.get(mid).getWtjg() == value) {
					if (mid == rang) {
						return ++mid;
					}
					mid++;
				}
				return mid;
			}
		}
		return Math.max(high, low);
	}

	@Override
	public List<RepBean> MatchCalculationMul(List<ReqBean> mulReqBean) {
		List<RepBean> res = new LinkedList<RepBean>();
		for (Iterator<ReqBean> iterator = mulReqBean.iterator(); iterator.hasNext();) {
			res.addAll(MatchCalculation(iterator.next()));
		}
		return res;
	}
}
