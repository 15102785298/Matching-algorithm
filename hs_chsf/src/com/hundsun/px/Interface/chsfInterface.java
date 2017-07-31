package com.hundsun.px.Interface;

import java.util.List;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;

public interface chsfInterface {

	// 用于将买入请求加入买入队列
	// public boolean AddToBuyTrade(TradeReq tradeReq);

	// 用于将卖出请求加入卖出队列
	// public boolean AddToSalTrade(TradeReq tradeReq);

	/***
	 * 输入单条撮合请求，返回撮合结果，多次成交以list返回
	 *
	 * @param reqBean 委托请求对象
	 *
	 * @return List<RepBean> 回复队列
	 */
	public List<RepBean> MatchCalculation(ReqBean reqBean);

	/***
	 * 输入多条撮合请求，返回最终撮合结果，以list返回
	 *
	 * @param List<ReqBean> 委托请求对象
	 *
	 * @return List<RepBean> 回复队列
	 */
	public List<RepBean> MatchCalculationMul(List<ReqBean> mulReqBean);
}
