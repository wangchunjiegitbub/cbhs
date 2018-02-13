package com.sc.dmh.service.inter;

import java.util.List;

import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;


public interface ViewCgServiceI extends BaseService<ViewCg,ViewCgExample,Long> {
	
	
	/**
	   * 通过部门id按照日期汇总收入情况
	   * @param depId
	   * @return
	   */
	  List<ViewCg> selectSumByExample(ViewCgExample example);
    
}
