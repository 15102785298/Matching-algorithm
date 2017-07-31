package com.hundsun.px.test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;
import com.hundsun.px.Bean.TradeList;
import com.hundsun.px.Tools.IdWorker;
import com.hundsun.px.impl.MatchAlgImpl;
import com.hundsun.px.impl.MatchAlgThread;
import com.hundsun.px.impl.DistributThread;

public class test {
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
		return low;
	}

	public static void main(String[] args) {

		LinkedList<ReqBean> testList2 = new LinkedList<ReqBean>();
		testList2.add(new ReqBean("1", "001", "600104", "111", '1', 4.5, 50));
		testList2.add(new ReqBean("2", "001", "600104", "111", '1', 5.5, 60));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 6.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));

		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 7.5, 70));

		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 8.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 9.5, 70));
		testList2.add(new ReqBean("3", "001", "600104", "111", '1', 10.5, 70));

		while (true) {
			Scanner sc = new Scanner(System.in);
			String s = sc.next();
			Integer aa = Integer.parseInt(s);
			int index = selSearch(testList2, 0, testList2.size(), aa);
			System.out.println(index);
			//testList2.add(index, new ReqBean("3", "001", "600104", "111", '1', aa.doubleValue(), 70));
		}
	}
}
