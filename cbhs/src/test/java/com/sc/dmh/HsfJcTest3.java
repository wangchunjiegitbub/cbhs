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

import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfJiecunExample;
import com.sc.dmh.beans.TabHsfJiecunExample.Criteria;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.util.DateStringUtil;





@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfJcTest3 {
	
	private static final Logger logger = Logger.getLogger(HsfJcTest3.class);
	@Autowired
	private HsfJcServiceI userServiceI;
	
	

	public HsfJcServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(HsfJcServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		  TabHsfJiecun user = userServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetdepId() throws Exception {
		
		  List<TabHsfJiecun> user = userServiceI.selectLastByDepId((long) 18);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetLimit() throws Exception {
		
		  TabHsfJiecunExample example = new TabHsfJiecunExample();
		  Criteria c = example.createCriteria();
		  Date value1 = DateStringUtil.toDate("2017-5-1", "yyyy-MM-dd");
		  Date value2 = DateStringUtil.toDate("2017-5-10", "yyyy-MM-dd");;
		  c.andJiecunMonthBetween(value1, value2);
		  
		  example.setStartRow(0);
		  example.setPageSize(2);
		List<TabHsfJiecun> user = userServiceI.selectByExample(example);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
}
