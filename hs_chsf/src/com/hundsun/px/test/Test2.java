package com.hundsun.px.test;

import java.net.StandardSocketOptions;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Tools.DBUtils;
import com.hundsun.px.impl.MatchAlgImpl;
import com.hundsun.px.impl.MatchAlgImpl2;
import com.hundsun.px.impl.MatchAlgThread;
import com.hundsun.px.impl.DistributThread;

public class Test2 {

	public static int[] sumNumber(List<RepBean> listBean) {
		int[] arr = new int[14000];
		for (RepBean bean : listBean) {
			arr[Integer.parseInt(bean.getSells())] += bean.getCjsl();
			arr[Integer.parseInt(bean.getBuyls())] += bean.getCjsl();
		}
		return arr;
	}

	public void updateOracleDealnum(List<RepBean> listBean) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		int a[] = sumNumber(listBean);
		DBUtils.getOracleCon();
		for (int i = 1; i <= a.length; i++) {
			if (i != 0) {
				map1.put("dealnum", a[i]);
				map2.put("tb_id", i);
				DBUtils.updateTbByParams(map1, "tb_sc", map2);
			}
		}
		DBUtils.closeCon();
	}

	public static void main(String[] args) {
		MatchAlgImpl chsf = new MatchAlgImpl();
		MatchAlgImpl2 chsf2 = new MatchAlgImpl2();

		// 读取Oracle委托数据,得到reqBean
		ArrayList<ReqBean> reqList = new ArrayList<ReqBean>();
		 reqList.addAll(getWtDataFromOracle("tb_sc"));

		LinkedList<ReqBean> testList2 = new LinkedList<ReqBean>();
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));

		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList2.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList2.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList2.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList2.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList2.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList2.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList2.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList2.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList2.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));







		LinkedList<ReqBean> testList3 = new LinkedList<ReqBean>();
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));

		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));
		testList3.add(new ReqBean("1", "001", "600104", "111", '1', 1.0, 50));
		testList3.add(new ReqBean("2", "001", "600104", "111", '1', 2.0, 60));
		testList3.add(new ReqBean("3", "001", "600104", "111", '1', 3.5, 70));
		testList3.add(new ReqBean("4", "001", "600104", "111", '1', 4.0, 60));
		testList3.add(new ReqBean("5", "001", "600104", "111", '1', 5.0, 90));
		testList3.add(new ReqBean("6", "001", "600104", "111", '1', 6.0, 100));
		testList3.add(new ReqBean("7", "001", "600104", "111", '1', 7.0, 80));
		testList3.add(new ReqBean("8", "001", "600104", "111", '1', 8.0, 70));
		testList3.add(new ReqBean("9", "001", "600104", "111", '1', 9.0, 50));
		testList3.add(new ReqBean("10", "001", "600104", "111", '1', 10.0, 90));
		testList3.add(new ReqBean("11", "001", "600104", "111", '1', 11.0, 80));
		testList3.add(new ReqBean("12", "001", "600104", "111", '1', 12.0, 220));


		chsf2.init();
		chsf.init();

		long startTime = System.currentTimeMillis(); // 获取开始时间
		List<RepBean> aa = chsf.MatchCalculationMul(reqList);
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("1用时"+(endTime - startTime));

		for (RepBean repBean : aa) {
		//	repBean.sc();
		}

		System.out.println("2222222222222222222222222");
		reqList.clear();
		reqList.addAll(getWtDataFromOracle("tb_sc"));
		startTime = System.currentTimeMillis(); // 获取开始时间
		List<RepBean> bb = chsf2.MatchCalculationMul(reqList);
		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("2用时"+(endTime - startTime));

		for (RepBean repBean : bb) {
			//repBean.sc();
		}

		LinkedList<ReqBean> testList = new LinkedList<ReqBean>();
		testList.add(new ReqBean("1", "001", "600104", "111", '1', 10.0, 50));

		System.out.println("addFirst time:");
		startTime = System.currentTimeMillis(); // 获取开始时间
		int j = 0;
		// while (j++ < 5) {
		// testList.add(new ReqBean("9", "001", "600104", "111", '1', 10.0 + j,
		// 50));
		// }
		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println(endTime - startTime);

		System.out.println("add index time");

		startTime = System.currentTimeMillis(); // 获取开始时间
		// int i = 0;
		// while (i++ < 500) {
		// testList.add( new ReqBean("9", "001", "600104", "111", '2', 10.0 + i,
		// 50));
		// }
		// endTime = System.currentTimeMillis(); // 获取结束时间
		// System.out.println(endTime - startTime);
		//
		System.out.println("cuo he");
		startTime = System.currentTimeMillis(); // 获取开始时间
		aa = chsf.MatchCalculationMul(testList);
		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println(endTime - startTime);
		// for (RepBean repBean : aa) {
		// // repBean.sc();
		// }
		//
		// LinkedList<ReqBean> testList2 = new LinkedList<ReqBean>();
		// testList2.add(new ReqBean("1", "001", "600104", "111", '1', 10.0,
		// 50));
		// testList2.add(new ReqBean("2", "001", "600104", "111", '1', 11.0,
		// 60));
		// testList2.add(new ReqBean("3", "001", "600104", "111", '1', 10.5,
		// 70));
		// testList2.add(new ReqBean("4", "001", "600104", "111", '2', 9.0,
		// 60));
		// testList2.add(new ReqBean("5", "001", "600104", "111", '2', 14.0,
		// 90));
		// testList2.add(new ReqBean("6", "001", "600104", "111", '1', 13.0,
		// 100));
		// testList2.add(new ReqBean("7", "001", "600104", "111", '2', 16.0,
		// 80));
		// testList2.add(new ReqBean("8", "001", "600104", "111", '2', 17.0,
		// 70));
		// testList2.add(new ReqBean("9", "001", "600104", "111", '1', 14.0,
		// 50));
		// testList2.add(new ReqBean("10", "001", "600104", "111", '2', 12.0,
		// 90));
		// testList2.add(new ReqBean("11", "001", "600104", "111", '1', 13.0,
		// 80));
		// testList2.add(new ReqBean("12", "001", "600104", "111", '2', 10.0,
		// 220));
		//
		// // int a[] = sumNumber(aa);
		//
		// DistributThread my = new DistributThread();
		// MatchAlgThread nnn = new MatchAlgThread();
		// my.setValue(testList2);
		// new Thread(my).start();
		// new Thread(nnn).start();
		//
		// nnn.RecReq(new ReqBean("1","001","002","111",'1',10d,50));
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// nnn.RecReq(new ReqBean("1","001","002","111",'1',10d,50));

		// int i = 0;

		// List<Redp> list = ch(reqLIst);

		// 跟据list来写入sqlServer

		// 跟据sqlServer更新Oracle

		// 显示Oracle数据

		// 补充：改委托状态

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
