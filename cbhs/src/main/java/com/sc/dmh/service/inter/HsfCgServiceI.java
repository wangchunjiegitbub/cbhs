package com.sc.dmh.service.inter;

import java.util.List;

import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;


public interface HsfCgServiceI extends BaseService<TabHsfCg,TabHsfCgExample,Long> {
	
	/**
	   * 通过部门id按照日期汇总收入情况
	   * @param depId
	   * @return
	   */
	 List<TabHsfCg> selectSumByDepIdAndDate(TabHsfCgExample example);
}
