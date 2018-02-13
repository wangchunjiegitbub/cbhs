package com.sc.dmh.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.mapper.TabHsfCgMapper;

import com.sc.dmh.service.inter.HsfCgServiceI;



@Service
@Transactional
public class HsfCgServiceImpl extends AbstractService<TabHsfCg,TabHsfCgExample,Long> implements HsfCgServiceI {

	
	  @Autowired
	  private TabHsfCgMapper hsfCgMapper;
	  
	  public void setDepartmentMapper(TabHsfCgMapper hsfCgMapper) {
		this.hsfCgMapper = hsfCgMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfCgMapper);
	  }

	@Override
	public List<TabHsfCg> selectSumByDepIdAndDate(TabHsfCgExample example) {
		
		return hsfCgMapper.selectSumByDepIdAndDate(example);
	}

	
	
	
	
	

	
}
