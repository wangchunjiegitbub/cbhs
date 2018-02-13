package com.sc.dmh.mapper;


import java.util.List;



import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;


public interface TabHsfShouruMapper  extends BaseMapper<TabHsfShouru, TabHsfShouruExample, Long> {
    
	/**
	   * 通过筛选条件按照日期汇总收入情况
	   * @param depId
	   * @return
	   */
	  List<TabHsfShouru> selectSumByDepIdAndDate(TabHsfShouruExample example);


}