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
import com.sc.dmh.beans.TabHsfSrtype;
import com.sc.dmh.service.inter.HsfSrtypeServiceI;
import com.sc.dmh.service.inter.UserServiceI;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfSrtypeTest4 {
	
	private static final Logger logger = Logger.getLogger(HsfSrtypeTest4.class);
	@Autowired
	private HsfSrtypeServiceI userServiceI;
	
	

	public HsfSrtypeServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(HsfSrtypeServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 TabHsfSrtype user = userServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
}
