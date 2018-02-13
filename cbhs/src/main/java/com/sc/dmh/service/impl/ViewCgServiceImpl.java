package com.sc.dmh.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;
import com.sc.dmh.mapper.ViewCgMapper;


import com.sc.dmh.service.inter.ViewCgServiceI;



@Service
@Transactional
public class ViewCgServiceImpl extends AbstractService<ViewCg,ViewCgExample,Long> implements ViewCgServiceI {

	
	  @Autowired
	  private ViewCgMapper viewCgMapper;
	  
	  public void setDepartmentMapper(ViewCgMapper viewCgMapper) {
		this.viewCgMapper = viewCgMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(viewCgMapper);
	  }

	@Override
	public List<ViewCg> selectSumByExample(ViewCgExample example) {
		
		return viewCgMapper.selectSumByExample(example);
	}

	
	
	
	
	

	
}
