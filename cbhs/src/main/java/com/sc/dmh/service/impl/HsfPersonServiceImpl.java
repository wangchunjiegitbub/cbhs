package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.mapper.TabHsfPersonMapper;

import com.sc.dmh.service.inter.HsfPersonServiceI;



@Service
@Transactional
public class HsfPersonServiceImpl extends AbstractService<TabHsfPerson,TabHsfPersonExample,Long> implements HsfPersonServiceI {

	
	  @Autowired
	  private TabHsfPersonMapper hsfPersonMapper;
	  
	  public void setDepartmentMapper(TabHsfPersonMapper hsfPersonMapper) {
		this.hsfPersonMapper = hsfPersonMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfPersonMapper);
	  }
	
	
	
	

	
}
