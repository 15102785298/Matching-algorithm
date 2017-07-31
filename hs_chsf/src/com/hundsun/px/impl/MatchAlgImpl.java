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
 * ���ֲ��ҵĴ���㷨
 */
public class MatchAlgImpl implements chsfInterface {

	private static Map<String, TradeList> chdl = new HashMap<String, TradeList>();
	// private static IdWorker idWorker = new IdWorker(1000);
	private static List<String> zqdmList = new ArrayList<>();

	/** ���ó�ʼ���������Լӿ����ִ���ٶȣ�������Ҳ����� =.= */
	public void init() {
		// ����֤ȯ�����ֶγ�ʼ������Map
		String[] allZqdm = PubString.allZQDM.split(",");
		int length = allZqdm.length;
		for (int i = 0; i < length; i++) {
			zqdmList.add(allZqdm[i]); // ֤ȯ���뱣��
			chdl.put(allZqdm[i], new TradeList());
		}
	}

	@Override
	public List<RepBean> MatchCalculation(ReqBean reqBean) {
		String zqdm = reqBean.getZqdm();
		char bs = reqBean.getBs();
		List<RepBean> returnList = null;
		TradeList czdl = chdl.get(zqdm);
		if (czdl == null) { // ����һ������ĳ֤ȯ�ʺ�
			czdl = new TradeList();
			zqdmList.add(zqdm);
			chdl.put(zqdm, czdl);
		}
		switch (bs) {
		case '1': // ���뷽��ί��
			returnList = CalculatBuy(czdl, reqBean);
			break;
		case '2': // ��������ί��
			returnList = CalculatSel(czdl, reqBean);
			break;
		default:
			break;
		}
		return returnList;
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
	private List<RepBean> CalculatSel(TradeList czdl, ReqBean selReq) {
		LinkedList<ReqBean> buyList = czdl.getBuyList();
		List<RepBean> res = new LinkedList<RepBean>();
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
					res.add(repBean);
					// ����������У����ĸ���
					buyList.removeFirst();
					return res;
				} else if (selSl < buySl) { // �����������ί������С�����������������
					// ���������Ϣ
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, selSl, 1, System.currentTimeMillis());
					res.add(repBean);
					// ����������У����¶���ͷ����
					buyReqInList.setWtsl(buySl - selSl); // ���������������
					return res;
				} else { // �����������ί�����������������ͷί�������������������ж�ν���
					// ���������Ϣ
					RepBean repBean = new RepBean();
					repBean.setSelAll(selReq, buyReqInList, buySl, 1, System.currentTimeMillis());
					res.add(repBean);
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
			return res;
		} else {
			// �����������У��ɵ͵�������
			LinkedList<ReqBean> selList = czdl.getSelList();
			int selSize = selList.size();
			double selWtjg = selReq.getWtjg();
			selList.add(selSearch(selList, 0, selSize, selWtjg), selReq);
			return res;
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
			if (selJg <= buyJg) { // �����ǰ�������м۸���͵ļ۸�С�ڵ�������۸�
				int selSl = selReqInList.getWtsl();
				int buySl = buyReq.getWtsl();
				if (selSl == buySl) { // �պ�һ��һ�ɽ���
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					res.add(repBean);
					selList.removeFirst();
					return res;
				} else if (selSl > buySl) { // ��������������������
					RepBean repBean = new RepBean();
					repBean.setBuyAll(selReqInList, buyReq, buySl, 1, System.currentTimeMillis());
					res.add(repBean);
					selReqInList.setWtsl(selSl - buySl);
					return res;
				} else { // ��������С����������
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

	// �ͼ�����
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

	// �߼�����
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
