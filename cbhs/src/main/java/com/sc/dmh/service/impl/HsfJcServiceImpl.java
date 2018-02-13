package com.sc.dmh.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfJiecunExample;

import com.sc.dmh.mapper.TabHsfJiecunMapper;
import com.sc.dmh.service.inter.HsfJcServiceI;





@Service
@Transactional
public class HsfJcServiceImpl extends AbstractService<TabHsfJiecun, TabHsfJiecunExample, Long> implements HsfJcServiceI {

	
	  @Autowired
	  private TabHsfJiecunMapper hsfJcMapper;
	  
	  

	  public void setHsfJcMapper(TabHsfJiecunMapper hsfJcMapper) {
		this.hsfJcMapper = hsfJcMapper;
	}



	//这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(hsfJcMapper);
	  }



	@Override
	public List<TabHsfJiecun> selectLastByDepId(Long depId) {
		
		return hsfJcMapper.selectLastByDepId(depId);
	}
	
	
	
	

	
}
