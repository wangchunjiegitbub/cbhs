package com.sc.dmh.mapper;


import java.util.List;


import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;


public interface ViewCgMapper  extends BaseMapper<ViewCg, ViewCgExample, Long>  {
	
	/**
	   * 通过部门id按照日期汇总收入情况
	   * @param depId
	   * @return
	   */
	  List<ViewCg> selectSumByExample(ViewCgExample example);
	

}