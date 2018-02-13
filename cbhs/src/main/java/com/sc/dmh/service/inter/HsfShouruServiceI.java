package com.sc.dmh.service.inter;


import java.util.List;



import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;

public interface HsfShouruServiceI extends BaseService<TabHsfShouru, TabHsfShouruExample, Long> {

	/**
	   * 通过部门id按照日期汇总收入情况
	   * @param depId
	   * @return
	   */
	List<TabHsfShouru> selectSumByDepIdAndDate(TabHsfShouruExample example);


}
