package com.hundsun.px.Interface;

import java.util.List;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;

public interface chsfInterface {

	// ���ڽ�������������������
	// public boolean AddToBuyTrade(TradeReq tradeReq);

	// ���ڽ��������������������
	// public boolean AddToSalTrade(TradeReq tradeReq);

	/***
	 * ���뵥��������󣬷��ش�Ͻ������γɽ���list����
	 *
	 * @param reqBean ί���������
	 *
	 * @return List<RepBean> �ظ�����
	 */
	public List<RepBean> MatchCalculation(ReqBean reqBean);

	/***
	 * �������������󣬷������մ�Ͻ������list����
	 *
	 * @param List<ReqBean> ί���������
	 *
	 * @return List<RepBean> �ظ�����
	 */
	public List<RepBean> MatchCalculationMul(List<ReqBean> mulReqBean);
}
