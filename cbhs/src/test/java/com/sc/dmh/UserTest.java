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
import com.sc.dmh.service.inter.UserServiceI;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class UserTest {
	
	private static final Logger logger = Logger.getLogger(UserTest.class);
	@Autowired
	private UserServiceI userServiceI;
	
	public UserServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(UserServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 CbhsUser user = userServiceI.selectByPrimaryKey(1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetpassusername() throws Exception {
		
		CbhsUserExample example = new CbhsUserExample();
		Criteria criteria = example.createCriteria();
		//criteria.andUserIdEqualTo(9);
		
		criteria.andUserNameEqualTo("111111");
		criteria.andPasswordEqualTo("111111");
		
		List<CbhsUser> user = userServiceI.selectByExample(example);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
}
