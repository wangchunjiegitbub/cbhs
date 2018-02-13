package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfSh;
import com.sc.dmh.beans.TabHsfShExample;
import com.sc.dmh.mapper.TabHsfShMapper;

import com.sc.dmh.service.inter.HsfShServiceI;



@Service
@Transactional
public class HsfShServiceImpl extends AbstractService<TabHsfSh,TabHsfShExample,Long> implements HsfShServiceI {

	
	  @Autowired
	  private TabHsfShMapper hsfShMapper;
	  
	  public void setDepartmentMapper(TabHsfShMapper hsfShMapper) {
		this.hsfShMapper = hsfShMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfShMapper);
	  }
	
	
	
	

	
}
