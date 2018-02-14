package com.sc.dmh;



import java.util.Date;
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
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.service.inter.HsfPersonServiceI;
import com.sc.dmh.service.inter.UserServiceI;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfPersonTest3 {
	
	private static final Logger logger = Logger.getLogger(HsfPersonTest3.class);
	@Autowired
	private HsfPersonServiceI userServiceI;
	
	

	public HsfPersonServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(HsfPersonServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 TabHsfPerson user = userServiceI.selectByPrimaryKey((long) 514);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetDateAfter() throws Exception {
		
		 Date p = new Date(2);
		 Date JZ = new Date(2);
		
		logger.info(p);
		logger.info(JZ);
		
		logger.info(JSON.toJSONStringWithDateFormat(!JZ.before(p), "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	@Test
	public void testGetSum() throws Exception {
		
		 TabHsfPersonExample example = new TabHsfPersonExample();
		 example.setOrderByClause("person_time");
		 com.sc.dmh.beans.TabHsfPersonExample.Criteria c = example.createCriteria();
		 c.andDepIdEqualTo(2);
		 c.andPersonTimeLessThan(new Date());
		List<TabHsfPerson> user = userServiceI.selectByExample(example );
		int sumIn = 0;
		int sumOut = 0;
		for(int i=0;i<user.size();i++){
			int j=user.get(i).getPersonIn();
			sumIn+=j;
			int k=user.get(i).getPersonOut();
			sumOut+=k;
			
			}
		logger.info(sumIn);
		logger.info(sumOut);
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
}
