package com.hundsun.px.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Tools.DBUtils;
import com.hundsun.px.impl.MatchAlgImpl;
import com.hundsun.px.impl.MatchAlgImpl2;

public class testCHSF {

	/**
	 * 由于优化还未完成，具体算法可能还有点乱，，
	 */
	public static void main(String[] args) {
		MatchAlgImpl chsf = new MatchAlgImpl();
		MatchAlgImpl2 chsf2 = new MatchAlgImpl2();

		// 读取Oracle委托数据,得到reqBean
		List<ReqBean> reqListData1 = getWtDataFromOracle("tb_sc");
		List<ReqBean> reqListData2 = new LinkedList<ReqBean>();
		reqListData2.addAll(reqListData1);

		chsf2.init();
		chsf.init();

		long startTime = System.currentTimeMillis(); // 获取开始时间

		List<RepBean> ptch = chsf2.MatchCalculationMul(reqListData2);
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("普通撮合算法用时" + (endTime - startTime));

		startTime = System.currentTimeMillis(); // 获取开始时间
		List<RepBean> efch = chsf.MatchCalculationMul(reqListData1);
		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("使用二分查找后的用时" + (endTime - startTime));
	}

	private static List<ReqBean> getWtDataFromOracle(String tbName) {
		// TODO Auto-generated method stub
		List<ReqBean> list = new ArrayList<ReqBean>();
		DBUtils.getOracleCon();
		list = DBUtils.queryAllWt(tbName);
		DBUtils.closeCon();
		return list;
	}

}
