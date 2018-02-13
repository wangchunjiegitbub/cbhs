package com.sc.dmh;



import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;
import com.sc.dmh.beans.CbhsUserExample.Criteria;
import com.sc.dmh.beans.TabDepartment;
import com.sc.dmh.service.inter.DepartmentServiceI;
import com.sc.dmh.service.inter.UserServiceI;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class DepartmentTest {
	
	private static final Logger logger = Logger.getLogger(DepartmentTest.class);
	@Autowired
	private DepartmentServiceI userServiceI;
	
	

	public DepartmentServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(DepartmentServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 TabDepartment user = userServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
}
