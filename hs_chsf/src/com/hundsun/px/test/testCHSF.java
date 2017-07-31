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
	 * �����Ż���δ��ɣ������㷨���ܻ��е��ң���
	 */
	public static void main(String[] args) {
		MatchAlgImpl chsf = new MatchAlgImpl();
		MatchAlgImpl2 chsf2 = new MatchAlgImpl2();

		// ��ȡOracleί������,�õ�reqBean
		List<ReqBean> reqListData1 = getWtDataFromOracle("tb_sc");
		List<ReqBean> reqListData2 = new LinkedList<ReqBean>();
		reqListData2.addAll(reqListData1);

		chsf2.init();
		chsf.init();

		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��

		List<RepBean> ptch = chsf2.MatchCalculationMul(reqListData2);
		long endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
		System.out.println("��ͨ����㷨��ʱ" + (endTime - startTime));

		startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		List<RepBean> efch = chsf.MatchCalculationMul(reqListData1);
		endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
		System.out.println("ʹ�ö��ֲ��Һ����ʱ" + (endTime - startTime));
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
