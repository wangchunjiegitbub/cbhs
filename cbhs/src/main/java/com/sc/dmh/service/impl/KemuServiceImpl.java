package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabKemu;
import com.sc.dmh.beans.TabKemuExample;
import com.sc.dmh.mapper.TabKemuMapper;
import com.sc.dmh.service.inter.KemuServiceI;



@Service
@Transactional
public class KemuServiceImpl extends AbstractService<TabKemu,TabKemuExample,Long> implements KemuServiceI {

	
	  @Autowired
	  private TabKemuMapper kemuMapper;
	  
	  public void setKemuMapper(TabKemuMapper kemuMapper) {
		this.kemuMapper = kemuMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(kemuMapper);
	  }
	
	
	
	

	
}
