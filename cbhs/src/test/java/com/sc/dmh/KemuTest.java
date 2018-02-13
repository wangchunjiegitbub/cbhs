package com.sc.dmh;



import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.beans.ComboTree;
import com.sc.dmh.beans.TabKemu;
import com.sc.dmh.beans.TabKemuExample;
import com.sc.dmh.service.inter.KemuServiceI;
import com.sc.dmh.util.TreeHelper;





@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-mybatis.xml"})   
public class KemuTest {
	
	private static final Logger logger = Logger.getLogger(KemuTest.class);
	@Autowired
	private KemuServiceI userServiceI;
	
	

	public KemuServiceI getUserServiceI() {
		return userServiceI;
	}

	public void setUserServiceI(KemuServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}

	@Test
	public void testGet() throws Exception {
		
		 

		
		
		TabKemuExample example = new TabKemuExample();
//		Criteria criteria = example.createCriteria();
//		
//		//设置查询条件添加车站id为登录用户部门id
//		criteria.andCzIdEqualTo(userSession.getBumenId());
//		//设置查询条件添加日期条件
//		
//		criteria.andInDateBetween(a.getFirstday(),a.getLastday());
//		
		//查询当月当前车站售票记录
		List<TabKemu> rpfAll = userServiceI.selectByExample(example);
		
		List<ComboTree> list = new LinkedList<ComboTree>();
		
		
		//复制软票费列表到页面列表
		if(rpfAll.size()>0){
			
			for(TabKemu t : rpfAll){
				if(t.getKemuParentid() == 999999999 ){
					list.add(TreeHelper.getComboTree(t.getKemuId().toString(),null, t.getKemuName()));
				}else{
					list.add(TreeHelper.getComboTree(t.getKemuId().toString(),t.getKemuParentid().toString(), t.getKemuName()));
				}
			}
			
		}
		
		
		List<ComboTree> l= TreeHelper.makeTree(list);
		logger.info(JSON.toJSONStringWithDateFormat(rpfAll, "yyyy-MM-dd HH:mm:ss"));
		
		logger.info(TreeHelper.makeTree(list).toString());
	}
	
	
	
	
}
