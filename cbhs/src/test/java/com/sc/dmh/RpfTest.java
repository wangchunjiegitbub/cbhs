package com.sc.dmh;



import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
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
import com.sc.dmh.beans.Rpf;
import com.sc.dmh.beans.Rpf;
import com.sc.dmh.beans.TabRpf;

import com.sc.dmh.service.inter.TabRpfServiceI;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.util.FatherToChildUtils;
import com.sc.dmh.util.MyBeanUtils;




@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class RpfTest {
	
	private static final Logger logger = Logger.getLogger(RpfTest.class);
	@Autowired
	private UserServiceI userServiceI;
	
	@Autowired
	private TabRpfServiceI rpfServiceI;
	
	

	public UserServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(UserServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	
	
	
	@Test
	public void testGetRpf() throws Exception {
		
		
		Rpf r = new Rpf();
		
		TabRpf tr = new TabRpf();
		
		tr.setCzId(5);
		
		//copyProperties(tr,r,null);
		
		//BeanUtils.copyProperties(r, tr);
		 
		tr = rpfServiceI.selectByPrimaryKey(2);
		MyBeanUtils.copyProperties(tr,r,null);
		
		
		logger.info(JSON.toJSONStringWithDateFormat(tr, "yyyy-MM-dd HH:mm:ss"));
		logger.info(JSON.toJSONStringWithDateFormat(r, "yyyy-MM-dd HH:mm:ss"));
		//logger.info(JSON.toJSONStringWithDateFormat(r, "yyyy-MM-dd HH:mm:ss"));
		//logger.info(JSON.toJSONStringWithDateFormat(rpf.getCzId(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	
	
	
	
	
	
}
