package com.sc.dmh.beans;

import java.math.BigDecimal;

public class ViewCgSum extends ViewCg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取单价
	 * @return
	 */
	public String getDj(){
		
		if(super.getCgMoney() != null && super.getCgSl() != null && super.getCgSl().equals(BigDecimal.ZERO)){
			return super.getCgMoney().divide(super.getCgSl()).toString();
		}else{
			return null;
		}
	}

}
