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


import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;
import com.sc.dmh.beans.ViewCgExample.Criteria;
import com.sc.dmh.service.inter.ViewCgServiceI;
import com.sc.dmh.util.DateStringUtil;





@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class ViewCgTest3 {
	
	private static final Logger logger = Logger.getLogger(ViewCgTest3.class);
	@Autowired
	private ViewCgServiceI userServiceI;
	
	

	public ViewCgServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(ViewCgServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 ViewCgExample example = new ViewCgExample();
		List<ViewCg> user = userServiceI.selectByExample(example);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetSum() throws Exception {
		
		 ViewCgExample example = new ViewCgExample();
		 Criteria c = example.createCriteria();
		 Date value1 = DateStringUtil.toDate("2017-05-01", "yyyy-MM-dd");
		 Date value2 = DateStringUtil.toDate("2017-05-10", "yyyy-MM-dd");
		c.andCgDateBetween(value1, value2);
		List<ViewCg> user = userServiceI.selectSumByExample(example);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
}
