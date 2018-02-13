package com.sc.dmh.service.inter;


import java.util.List;

import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfJiecunExample;

public interface HsfJcServiceI extends BaseService<TabHsfJiecun, TabHsfJiecunExample, Long> {

	
	/**
	   * 通过部门id获取最后结存数据
	   * @param depId
	   * @return
	   */
	  List<TabHsfJiecun> selectLastByDepId(Long depId);

}
