package com.sc.dmh;



import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import com.sc.dmh.beans.TabHsfSh;
import com.sc.dmh.service.inter.HsfShServiceI;





@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfShTest4 {
	
	private static final Logger logger = Logger.getLogger(HsfShTest4.class);
	@Autowired
	private HsfShServiceI userServiceI;
	
	

	public HsfShServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(HsfShServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		    TabHsfSh user = userServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
}
