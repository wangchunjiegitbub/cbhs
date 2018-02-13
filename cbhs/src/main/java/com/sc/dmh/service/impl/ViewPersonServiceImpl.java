package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.ViewPerson;
import com.sc.dmh.beans.ViewPersonExample;
import com.sc.dmh.mapper.ViewPersonMapper;


import com.sc.dmh.service.inter.ViewPersonServiceI;



@Service
@Transactional
public class ViewPersonServiceImpl extends AbstractService<ViewPerson,ViewPersonExample,Long> implements ViewPersonServiceI {

	
	  @Autowired
	  private ViewPersonMapper hsfPersonMapper;
	  
	  public void setDepartmentMapper(ViewPersonMapper hsfPersonMapper) {
		this.hsfPersonMapper = hsfPersonMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfPersonMapper);
	  }
	
	
	
	

	
}
