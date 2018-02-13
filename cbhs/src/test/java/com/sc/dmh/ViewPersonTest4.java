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
import com.sc.dmh.beans.ViewPerson;
import com.sc.dmh.beans.ViewPersonExample;
import com.sc.dmh.service.inter.ViewPersonServiceI;
import com.sc.dmh.service.inter.UserServiceI;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class ViewPersonTest4 {
	
	private static final Logger logger = Logger.getLogger(ViewPersonTest4.class);
	@Autowired
	private ViewPersonServiceI userServiceI;
	
	

	public ViewPersonServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(ViewPersonServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 ViewPersonExample example = new ViewPersonExample();
		List<ViewPerson> user = userServiceI.selectByExample(example);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
}
