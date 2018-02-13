package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfSc;
import com.sc.dmh.beans.TabHsfScExample;
import com.sc.dmh.mapper.TabHsfScMapper;

import com.sc.dmh.service.inter.HsfScServiceI;



@Service
@Transactional
public class HsfScServiceImpl extends AbstractService<TabHsfSc,TabHsfScExample,Long> implements HsfScServiceI {

	
	  @Autowired
	  private TabHsfScMapper hsfScMapper;
	  
	  public void setDepartmentMapper(TabHsfScMapper hsfScMapper) {
		this.hsfScMapper = hsfScMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfScMapper);
	  }
	
	
	
	

	
}
