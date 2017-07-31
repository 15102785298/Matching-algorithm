package com.hundsun.px.Bean;

/*
 * 委托记录对象
 */
public class EntrustedRecordBean {
	private int rec_num; //记录编号，连续递增
	private String date; //记录写入日期，格式为YYYYMMDD
	private String time; //记录写入时间，格式为HH:MM:SS
	private String reff; //会员内部订单号
	private String acc;  //证券账户
	private String stock;  //证券代码
	private char bs;  //买卖方向,‘B’或者‘b’代表买入，‘S’或者‘s’代表卖出
	private String price; //申报价格
	private String qty; //申报数量
	private char status; //发送状态
	private String owflag;  //订单类型标志
	private String ordrec;  //撤单编号
	private String firmid;  //B股结算会员代码
	private String branchid;  //营业部代码
	private byte checkord;  //校验码
	
	
	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReff() {
		return reff;
	}
	public void setReff(String reff) {
		this.reff = reff;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public char getBs() {
		return bs;
	}
	public void setBs(char bs) {
		this.bs = bs;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getOwflag() {
		return owflag;
	}
	public void setOwflag(String owflag) {
		this.owflag = owflag;
	}
	public String getOrdrec() {
		return ordrec;
	}
	public void setOrdrec(String ordrec) {
		this.ordrec = ordrec;
	}
	public String getFirmid() {
		return firmid;
	}
	public void setFirmid(String firmid) {
		this.firmid = firmid;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public byte getCheckord() {
		return checkord;
	}
	public void setCheckord(byte checkord) {
		this.checkord = checkord;
	}
}
