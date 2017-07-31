package com.hundsun.px.Bean;

/**
 * 委托请求对象
 */
public class ReqBean {
	private String wtls; // 委托流水
	private String khzh; // 客户帐号
	private String zqdm; // 证券代码
	private String sbsj; // 申报时间
	private char bs; // 委托方向，买入为0，卖出为1
	private double wtjg; // 委托价格
	private int wtsl; // 委托数量

	public ReqBean(String wtls, String khzh, String zqdm, String sbsj, char bs, Double wtjg, int wtsl) {
	    super();
	    this.wtls = wtls;
	    this.khzh = khzh;
	    this.zqdm = zqdm;
	    this.sbsj = sbsj;
	    this.bs = bs;
	    this.wtjg = wtjg;
	    this.wtsl = wtsl;
	  }

	public ReqBean() {
		// TODO Auto-generated constructor stub
	}

	public String getWtls() {
		return wtls;
	}

	public void setWtls(String wtls) {
		this.wtls = wtls;
	}

	public String getKhzh() {
		return khzh;
	}

	public void setKhzh(String khzh) {
		this.khzh = khzh;
	}

	public String getZqdm() {
		return zqdm;
	}

	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public char getBs() {
		return bs;
	}

	public void setBs(char bs) {
		this.bs = bs;
	}

	public double getWtjg() {
		return wtjg;
	}

	public void setWtjg(double wtjg) {
		this.wtjg = wtjg;
	}

	public int getWtsl() {
		return wtsl;
	}

	public void setWtsl(int wtsl) {
		this.wtsl = wtsl;
	}

}
