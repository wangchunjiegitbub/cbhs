package com.sc.dmh.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;
import com.sc.dmh.mapper.TabHsfShouruMapper;

import com.sc.dmh.service.inter.HsfShouruServiceI;



@Service
@Transactional
public class HsfShouruServiceImpl extends AbstractService<TabHsfShouru,TabHsfShouruExample,Long> implements HsfShouruServiceI {

	
	  @Autowired
	  private TabHsfShouruMapper hsfShouruMapper;
	  
	  public void setDepartmentMapper(TabHsfShouruMapper hsfShouruMapper) {
		this.hsfShouruMapper = hsfShouruMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfShouruMapper);
	  }

	@Override
	public List<TabHsfShouru> selectSumByDepIdAndDate(TabHsfShouruExample example) {
		
		return hsfShouruMapper.selectSumByDepIdAndDate(example);
	}

	

	
	
	
	
	

	
}
