package com.sc.dmh.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sc.dmh.beans.TabDepartment;
import com.sc.dmh.beans.TabDepartmentExample;
import com.sc.dmh.mapper.TabDepartmentMapper;
import com.sc.dmh.service.inter.DepartmentServiceI;



@Service
@Transactional
public class DepartmentServiceImpl extends AbstractService<TabDepartment,TabDepartmentExample,Long> implements DepartmentServiceI {

	
	  @Autowired
	  private TabDepartmentMapper departmentMapper;
	  
	  public void setDepartmentMapper(TabDepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	  }

	  //这句必须要加上。不然会报空指针异常，因为在实际掉用的时候不是BaseMapper调用，而是这个productMapper
	  @Autowired
	  public void setBaseMapper(){
	    super.setBaseMapper(departmentMapper);
	  }
	
	
	
	

	
}
