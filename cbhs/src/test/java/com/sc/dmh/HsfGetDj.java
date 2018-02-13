package com.sc.dmh;



import java.math.BigDecimal;
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
import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgSum;
import com.sc.dmh.service.inter.HsfCgServiceI;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.FatherToChildUtils;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfGetDj {
	
	private static final Logger logger = Logger.getLogger(HsfGetDj.class);
	@Autowired
	private HsfCgServiceI userServiceI;
	
	

	public HsfCgServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(HsfCgServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 TabHsfCg user = userServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	@Test
	public void testGetSum() throws Exception {
		
		 TabHsfCgExample example = new TabHsfCgExample();
		 com.sc.dmh.beans.TabHsfCgExample.Criteria c = example.createCriteria();
		 c.andCgDepidEqualTo(18);
		 Date value1=DateStringUtil.toDate("2017-5-1", "yyyy-MM-dd");
		Date value2=DateStringUtil.toDate("2017-5-11", "yyyy-MM-dd");
		c.andCgDateBetween(value1, value2);
		
		 List<TabHsfCg> user = userServiceI.selectSumByDepIdAndDate(example );
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetDj() throws Exception {
		ViewCg a = new ViewCg();
//		 ViewCgSum a2 = new ViewCgSum();
		 BigDecimal cgMoney = new BigDecimal("10");
		a.setCgMoney(cgMoney);
		 BigDecimal cgSl =new BigDecimal("10");
		a.setCgSl(cgSl);
//		FatherToChildUtils.fatherToChild(a, a2);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(a, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
}
