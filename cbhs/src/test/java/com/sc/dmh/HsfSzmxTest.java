package com.sc.dmh;





import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.beans.HsfSzmx;
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.beans.TabHsfPersonExample.Criteria;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.HsfPersonServiceI;
import com.sc.dmh.util.BigDecimalValueFilter;
import com.sc.dmh.util.DateStringUtil;





@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class HsfSzmxTest {
	
	private static final Logger logger = Logger.getLogger(HsfSzmxTest.class);
	@Autowired
	private HsfJcServiceI hsfJcServiceI;
	
	@Autowired
	private HsfPersonServiceI personServiceI;

	public HsfPersonServiceI getPersonServiceI() {
		return personServiceI;
	}

	public void setPersonServiceI(HsfPersonServiceI personServiceI) {
		this.personServiceI = personServiceI;
	}

	public HsfJcServiceI getUserServiceI() {
		return hsfJcServiceI;
	}

	public void setUserServiceI(HsfJcServiceI userServiceI) {
		this.hsfJcServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		  TabHsfJiecun user = hsfJcServiceI.selectByPrimaryKey((long) 1);
		
		
		
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testGetdepId() throws Exception {
		
		
		Date begingDate;
		Date endDate = new Date();
		
		//创建收支明细map对象键为日期
		//Map<String,List<HsfSzmx>> szmxMap = new HashMap<String,List<HsfSzmx>>();
		//创建收支明细map对象键为日期
		Map<Date,HsfSzmx> szmxMap = new HashMap<Date,HsfSzmx>();
		
		
		
		//根据部门id获取最后结存记录
		List<TabHsfJiecun> user = hsfJcServiceI.selectLastByDepId((long) 18);
		//设置开始日期为结存记录日期
		begingDate = user.get(0).getJiecunMonth();
		
		//获取日期差
		int i = DateStringUtil.daysBetween(begingDate, endDate);
		
		//map中添加日期及收支明细对象初始化首行及其他各行日期人员定额参数---收支明细初始化方法
		szmxInitializat(begingDate, szmxMap, user, i);
		
		
		//logger.info(JSON.toJSONStringWithDateFormat(begingDate, "yyyy-MM-dd HH:mm:ss"));
		
		//人员结存情况处理开始---------------------------------------------------------------
		
		getRy(begingDate, endDate, szmxMap, i);
		
		
		
		//人员结存情况处理结束---------------------------------------------------------------
		
//		logger.info(JSON.toJSONStringWithDateFormat(i, "yyyy-MM-dd HH:mm:ss"));
//		logger.info(JSON.toJSONStringWithDateFormat(person, "yyyy-MM-dd HH:mm:ss"));
		
		BigDecimalValueFilter filter = new BigDecimalValueFilter();
		
		String contentJson = JSON.toJSONString(szmxMap, filter); 
		
		logger.info(JSON.toJSONString(szmxMap, filter));
		//logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
	}

	private void getRy(Date begingDate, Date endDate, Map<Date, HsfSzmx> szmxMap, int i) {
		
		TabHsfPersonExample example = new TabHsfPersonExample();
		Criteria c = example.createCriteria();
		//设置查询添加为开始日期加一天，结束日期，开始日期已经结账了
		c.andPersonTimeBetween(DateStringUtil.addDateOneDay(begingDate), endDate);
		
		//查询开始到结束日期间的记录
		List<TabHsfPerson> person = personServiceI.selectByExample(example);
		
		
		//结存人数
		double yesterdayJcrs = 0;
		//获取开始日期结存人数
		yesterdayJcrs = szmxMap.get(begingDate).getRs();
		
		//记录当天日期
		Date dayDate = new Date();
		
		dayDate = DateStringUtil.addDateOneDay(begingDate);
		//jcrs = user.get(0).getJiecunRs();
		for(int j=0;j<i;j++){
			
			
			
			//设置是否查找到人员出入记录
			boolean findTrue = false;
			for (TabHsfPerson people : person) {
				//比较当前日期是否有人员出入记录
				if(people.getPersonTime().equals(dayDate)){//有出入记录计算结存人数并保存
					//设置结存人数为计算后人数
					szmxMap.get(dayDate).setRs((yesterdayJcrs+people.getPersonIn()-people.getPersonOut()));
					findTrue = true;
					break;
					
					
				}
			}
			
			//如果没有找到人员出入记录设置结存人数为昨天人数
			if(!findTrue)szmxMap.get(dayDate).setRs(yesterdayJcrs);
			
			//修改昨日结存人数为当前结存
			yesterdayJcrs= szmxMap.get(dayDate).getRs();
			//日期增加一天
			dayDate = DateStringUtil.addDateOneDay(dayDate);
		}
	}

	private void szmxInitializat(Date begingDate, Map<Date, HsfSzmx> szmxMap, List<TabHsfJiecun> user, int i) {
		for(int j=0;j<=i;j++){
			//List<HsfSzmx> szmxList = new ArrayList<HsfSzmx>();
			HsfSzmx szmx= new HsfSzmx();
			//设置日期
			
			szmx.setSzmxDate(begingDate);
			//设置人员定额为103/30=3.43元
			szmx.setRydinge(3.43);
			//如果j=0最后一次结账日期设置结存人数、结存金额、摘要
			if(j==0){
				//设置首行结存信息
				setTop(user, szmx);
			}
			//key为日期,map添加对象
			szmxMap.put(begingDate, szmx);
			//日期加一天
			begingDate=DateStringUtil.addDateOneDay(begingDate);
		}
		
	}
	
	//设置首行结存信息
	private void setTop(List<TabHsfJiecun> user, HsfSzmx szmx) {
		//设置上期结余人数
		szmx.setRs(user.get(0).getJiecunRs());
		//设置上期结余金额
		szmx.setJieyu(user.get(0).getJiecunMoney());
		//设置摘要为上期结余
		szmx.setZhaiyao("上期结余");
	}

	
	
	
	
	
}
