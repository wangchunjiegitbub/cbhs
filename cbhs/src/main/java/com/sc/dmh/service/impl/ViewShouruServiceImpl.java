package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.ViewShouru;
import com.sc.dmh.beans.ViewShouruExample;
import com.sc.dmh.mapper.ViewShouruMapper;


import com.sc.dmh.service.inter.ViewShouruServiceI;



@Service
@Transactional
public class ViewShouruServiceImpl extends AbstractService<ViewShouru,ViewShouruExample,Long> implements ViewShouruServiceI {

	
	  @Autowired
	  private ViewShouruMapper viewShouruMapper;
	  
	  public void setDepartmentMapper(ViewShouruMapper hsfPersonMapper) {
		this.viewShouruMapper = hsfPersonMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(viewShouruMapper);
	  }
	
	
	
	

	
}
