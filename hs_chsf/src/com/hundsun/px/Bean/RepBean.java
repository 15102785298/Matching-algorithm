package com.hundsun.px.Bean;

/**
 * �����ظ�����
 */
public class RepBean {

	private String selkh; // �����ͻ�
	private String buykh; // �򷽿ͻ�
	private String zqdm; // ֤ȯ����
	private int cjsl; // �ɽ�����
	private long cjbh; // �ɽ����
	private String sbsj; // �걨ʱ��
	private long cjsj; // �ɽ�ʱ��
	private double cjjg; // �ɽ��۸�
	private char bs; // ί�з�������Ϊ0������Ϊ1
	private double wtjg; // ί�м۸�
	private String sells; // ����ί����ˮ
	private String buyls; // ��ί����ˮ

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
		selkh = selReq.getKhzh(); // ��ͬ
		buykh = buyReq.getKhzh(); // ��ͬ
		zqdm = selReq.getZqdm(); // ��ͬ
		this.cjsl = cjsl; // ��ͬ
		this.cjbh = cjbh; // ��ͬ
		sbsj = selReq.getSbsj(); // ��ͬ
		this.cjsj = cjsj; // ��ͬ
		cjjg = buyReq.getWtjg(); // ��ͬ
		bs = 1; // ��ͬ
		wtjg = selReq.getWtjg(); // ��ͬ
		sells = selReq.getWtls(); // ��ͬ
		buyls = buyReq.getWtls(); // ��ͬ

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
