package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfSrtype;
import com.sc.dmh.beans.TabHsfSrtypeExample;
import com.sc.dmh.mapper.TabHsfSrtypeMapper;

import com.sc.dmh.service.inter.HsfSrtypeServiceI;



@Service
@Transactional
public class HsfSrtypeServiceImpl extends AbstractService<TabHsfSrtype,TabHsfSrtypeExample,Long> implements HsfSrtypeServiceI {

	
	  @Autowired
	  private TabHsfSrtypeMapper hsfSrtypeMapper;
	  
	  public void setDepartmentMapper(TabHsfSrtypeMapper hsfSrtypeMapper) {
		this.hsfSrtypeMapper = hsfSrtypeMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfSrtypeMapper);
	  }
	
	
	
	

	
}
