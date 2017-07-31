package com.hundsun.px.Bean;

/**
 * 单条回复对象
 */
public class RepBean {

	private String selkh; // 卖方客户
	private String buykh; // 买方客户
	private String zqdm; // 证券代码
	private int cjsl; // 成交数量
	private long cjbh; // 成交编号
	private String sbsj; // 申报时间
	private long cjsj; // 成交时间
	private double cjjg; // 成交价格
	private char bs; // 委托方向，买入为0，卖出为1
	private double wtjg; // 委托价格
	private String sells; // 卖方委托流水
	private String buyls; // 买房委托流水

	public String getSelkh() {
		return selkh;
	}

	public void setSelkh(String selkh) {
		this.selkh = selkh;
	}

	public String getBuykh() {
		return buykh;
	}

	public void setBuykh(String buykh) {
		this.buykh = buykh;
	}

	public String getZqdm() {
		return zqdm;
	}

	public void setZqdm(String zqdm) {
		this.zqdm = zqdm;
	}

	public long getCjbh() {
		return cjbh;
	}

	public void setCjbh(long cjbh) {
		this.cjbh = cjbh;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public long getCjsj() {
		return cjsj;
	}

	public void setCjsj(long cjsj) {
		this.cjsj = cjsj;
	}

	public String getSells() {
		return sells;
	}

	public void setSells(String sells) {
		this.sells = sells;
	}

	public String getBuyls() {
		return buyls;
	}

	public void setBuyls(String buyls) {
		this.buyls = buyls;
	}

	public int getCjsl() {
		return cjsl;
	}

	public void setCjsl(int cjsl) {
		this.cjsl = cjsl;
	}

	public double getCjjg() {
		return cjjg;
	}

	public void setCjjg(double cjjg) {
		this.cjjg = cjjg;
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

	public void setSelAll(ReqBean selReq, ReqBean buyReq, int cjsl, long cjbh, long cjsj) {
		selkh = selReq.getKhzh(); // 相同
		buykh = buyReq.getKhzh(); // 相同
		zqdm = selReq.getZqdm(); // 相同
		this.cjsl = cjsl; // 相同
		this.cjbh = cjbh; // 相同
		sbsj = selReq.getSbsj(); // 不同
		this.cjsj = cjsj; // 相同
		cjjg = buyReq.getWtjg(); // 不同
		bs = 1; // 不同
		wtjg = selReq.getWtjg(); // 不同
		sells = selReq.getWtls(); // 相同
		buyls = buyReq.getWtls(); // 相同

	}

	public void setBuyAll(ReqBean selReq, ReqBean buyReq, int cjsl, long cjbh, long cjsj) {
		selkh = selReq.getKhzh();
		buykh = buyReq.getKhzh();
		zqdm = buyReq.getZqdm();
		this.cjsl = cjsl;
		this.cjbh = cjbh;
		sbsj = buyReq.getSbsj();
		this.cjsj = cjsj;
		cjjg = selReq.getWtjg();
		bs = 0;
		wtjg = buyReq.getWtjg();
		sells = selReq.getWtls();
		buyls = buyReq.getWtls();
	}

	public void sc() {
		System.out.println("sells=" + sells + " " + "buyls=" + buyls + " " + "cjjg=" + cjjg + " " + "cjsl=" + cjsl);
	}

}
