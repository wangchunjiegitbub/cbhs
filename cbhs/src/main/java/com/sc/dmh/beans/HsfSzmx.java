package com.sc.dmh.beans;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

public class HsfSzmx {
	
	private Date szmxDate;          //日期
	private String zhaiyao; 		//摘要
	private double rs =0;				//人数
	private double rydinge =0;			//人员定额3.43
	
	private BigDecimal shouru = new BigDecimal(0);		//shoru
	private BigDecimal zhichu = new BigDecimal(0);		//支出
	private BigDecimal jieyu = new BigDecimal(0);		//结余
	private String beizhu;			//备注
	
	public double getRydinge() {
		return rydinge;
	}
	public void setRydinge(double rydinge) {
		this.rydinge = rydinge;
	}
	
	public double getRs() {
		return rs;
	}
	public void setRs(double rs) {
		this.rs = rs;
	}
	public Date getSzmxDate() {
		return szmxDate;
	}
	public void setSzmxDate(Date szmxDate) {
		this.szmxDate = szmxDate;
	}
	public String getZhaiyao() {
		return zhaiyao;
	}
	public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
	}
	public BigDecimal getDinge() {
		double d = rs*rydinge;
		
		return new BigDecimal(d, MathContext.DECIMAL32).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getShouru() {
		return shouru;
	}
	public void setShouru(BigDecimal shouru) {
		this.shouru = shouru;
	}
	public BigDecimal getZhichu() {
		return zhichu;
	}
	public void setZhichu(BigDecimal zhichu) {
		this.zhichu = zhichu;
	}
	public BigDecimal getJieyu() {
		return jieyu;
	}
	public void setJieyu(BigDecimal jieyu) {
		this.jieyu = jieyu;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

}
