package com.hundsun.px.Bean;

/*
 * ί�м�¼����
 */
public class EntrustedRecordBean {
	private int rec_num; //��¼��ţ���������
	private String date; //��¼д�����ڣ���ʽΪYYYYMMDD
	private String time; //��¼д��ʱ�䣬��ʽΪHH:MM:SS
	private String reff; //��Ա�ڲ�������
	private String acc;  //֤ȯ�˻�
	private String stock;  //֤ȯ����
	private char bs;  //��������,��B�����ߡ�b���������룬��S�����ߡ�s����������
	private String price; //�걨�۸�
	private String qty; //�걨����
	private char status; //����״̬
	private String owflag;  //�������ͱ�־
	private String ordrec;  //�������
	private String firmid;  //B�ɽ����Ա����
	private String branchid;  //Ӫҵ������
	private byte checkord;  //У����
	
	
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
