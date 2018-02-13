package com.sc.dmh.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.HsfJcTest3;
import com.sc.dmh.annotation.AuthPassport;
import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;
import com.sc.dmh.beans.ComboTree;
import com.sc.dmh.beans.DateMonthFirstLast;
import com.sc.dmh.beans.HsfSzmx;
import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.beans.TabHsfSh;
import com.sc.dmh.beans.TabHsfShExample;
import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfJiecunExample;
import com.sc.dmh.beans.TabHsfJiecunExample.Criteria;
import com.sc.dmh.service.inter.HsfCgServiceI;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.HsfPersonServiceI;
import com.sc.dmh.service.inter.HsfShouruServiceI;
import com.sc.dmh.service.inter.UserServiceI;

import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.MyBeanUtils;
import com.sc.dmh.util.TreeHelper;



@Controller
@RequestMapping("/hsf")
public class JzController {

	private static final Logger logger = Logger.getLogger(JzController.class);
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfCgServiceI hsfCgService;
	
	
	
	@Autowired
	private HsfPersonServiceI personServiceI;
	
	@Autowired
	private HsfShouruServiceI ShouruServiceI;
	
	@Autowired
	private HsfCgServiceI cgServiceI;
	
	@Autowired
	private HsfJcServiceI hsfJcServiceI;
	

	public void setHsfJcServiceI(HsfJcServiceI hsfJcServiceI) {
		this.hsfJcServiceI = hsfJcServiceI;
	}

	public void setShouruServiceI(HsfShouruServiceI shouruServiceI) {
		ShouruServiceI = shouruServiceI;
	}

	public void setCgServiceI(HsfCgServiceI cgServiceI) {
		this.cgServiceI = cgServiceI;
	}

	public void setPersonServiceI(HsfPersonServiceI personServiceI) {
		this.personServiceI = personServiceI;
	}

	

	public void setHsfCgService(HsfCgServiceI hsfCgService) {
		this.hsfCgService = hsfCgService;
	}

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	//自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	//请求的路径
	@RequestMapping("/jz")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/jz";
		}
	
	
	
	// 自定义权限验证注解加注解时表示有权限是才能访问
	@AuthPassport
	// 获取所有
	@RequestMapping("/getJzState")
	public String getJzState(Integer depId, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
	
		resp.setContentType("text/html;charset=utf-8");
		//创建点名会对象
		//TabRpf formRpfFp = new TabRpf();
		//得到用户
		CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
		
		if(userSession == null ){
			//获取用户部门设置到车站id		
			return null;
			
		}
		
		
		if(getPersonStrate(userSession.getBumenId()) && getCgBxStrte(userSession.getBumenId()) && getSrStrate(userSession.getBumenId())){
			
			resp.getWriter().write( 1 + "" );
		}else{
			
			return null;
		}
		return null;		
	} 
	
	
	
	
	
			//获取车站采购报销审核状态审核全部通过返回true
			private  boolean getCgBxStrte(Integer bmId) {
				
				
				TabHsfCgExample example = new TabHsfCgExample();
				com.sc.dmh.beans.TabHsfCgExample.Criteria c = example.createCriteria();
				c.andCgDepidEqualTo(bmId);
				c.andCgBxEqualTo(0);
				List<TabHsfCg> cgList = cgServiceI.selectByExample(example);
				
				logger.debug(JSON.toJSONString(cgList));
				//没有查询出审核未通过的记录返回true
				if(cgList.isEmpty()){
					return true;	
				}else{
					return false;
				}
				
			}
			
			//获取车站收入审核状态审核全部通过返回true
			private boolean getSrStrate(Integer bmId) {
				
				
				TabHsfShouruExample example= new TabHsfShouruExample();
				com.sc.dmh.beans.TabHsfShouruExample.Criteria c =example.createCriteria();
				c.andDepIdEqualTo(bmId);
				c.andShouruStateEqualTo(0);
				
				List<TabHsfShouru>  shouruList = ShouruServiceI.selectByExample(example);
				//没有查询出审核未通过的记录返回true
				logger.debug(JSON.toJSONString(shouruList));
				if(shouruList.isEmpty()){
					return true;	
				}else{
					return false;
				}
				
				
				
			}
			//获取车站人员审核状态审核全部通过返回true
			private boolean getPersonStrate(Integer bmId) {
				
				TabHsfPersonExample example = new TabHsfPersonExample();
				com.sc.dmh.beans.TabHsfPersonExample.Criteria c =  example.createCriteria();
				c.andDepIdEqualTo(bmId);
				c.andPersonStateEqualTo(0);
				List<TabHsfPerson> personList = personServiceI.selectByExample(example);
				logger.debug(JSON.toJSONString(personList));
				//没有查询出审核未通过的记录返回true
				if(personList.isEmpty()){
					return true;	
				}else{
					return false;
				}
				
			}

			// 自定义权限验证注解加注解时表示有权限是才能访问
			@AuthPassport
			// 添加客票管理中的客票用量中的粉票
			@RequestMapping("/addJz")
			public String fpAdd(String jiecunMonth, HttpServletRequest request,
					HttpServletResponse resp) throws IOException {
				resp.setContentType("text/html;charset=utf-8");
				//创建点名会对象
				//TabRpf formRpfFp = new TabRpf();
			    //得到用户
				CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
				//
//				if(userSession != null ){
//					//获取用户部门设置到车站id		
//					formRpfFp.setCzId(userSession.getBumenId());
//					
//				}
			
//				-----------------------------------------------------------------------------------------------------
				
				
				Date beginDate;
				//获取结束日期
				Date endDate = DateStringUtil.toDate(jiecunMonth, "yyyy-MM-dd");
				
				//创建收支明细map对象键为日期
				//Map<String,List<HsfSzmx>> szmxMap = new HashMap<String,List<HsfSzmx>>();
				//创建收支明细map对象键为日期
				Map<Date,HsfSzmx> szmxMap = new HashMap<Date,HsfSzmx>();
				
				
				
				//根据部门id获取最后结存记录
				List<TabHsfJiecun> hsfJc = hsfJcServiceI.selectLastByDepId((long) userSession.getBumenId());
				//无结存记录
				if(hsfJc.size()<1){
					//根据部门查询人员记录
					TabHsfPersonExample example = new TabHsfPersonExample();
					com.sc.dmh.beans.TabHsfPersonExample.Criteria c = example.createCriteria();
					c.andDepIdEqualTo(userSession.getBumenId());
					List<TabHsfPerson> personList = personServiceI.selectByExample(example);
					
					//根据部门查询收入记录
					TabHsfShouruExample example1 = new TabHsfShouruExample();
					com.sc.dmh.beans.TabHsfShouruExample.Criteria c1 = example1.createCriteria();
					c1.andDepIdEqualTo(userSession.getBumenId());
					List<TabHsfShouru> srList = ShouruServiceI.selectByExample(example1);
					
					//如果人员记录与收入记录同时为1条时添加结存记录
					if(srList.size() == 1 && personList.size() == 1 ){
						TabHsfJiecun record = new TabHsfJiecun();
						record.setDepId(userSession.getBumenId());
						record.setJiecunMoney(srList.get(0).getShouruMoney());
						record.setJiecunMonth(endDate);
						record.setJiecunRs(personList.get(0).getPersonIn());
						record.setUserId(userSession.getUserId());
						hsfJcServiceI.insertSelective(record);
						
						resp.getWriter().write("1");
						return null;
						
					}else{
						return null ;
					}
					
					
				}
				
				
				
				//设置开始日期为结存记录日期
				beginDate = hsfJc.get(0).getJiecunMonth();
				
				//获取日期差
				int i=0;
				
				try {
					i = DateStringUtil.daysBetween(beginDate, endDate);
					//日期差不能小于0
					if(i<0)return null;
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				//map中添加日期及收支明细对象初始化首行及其他各行日期人员定额参数---收支明细初始化方法
				szmxInitializat(beginDate, szmxMap, hsfJc, i);
				
				
				
				//人员结存情况处理开始---------------------------------------------------------------
				
				//得到人员变动情况
				getRy(beginDate, endDate, szmxMap, i, userSession);
				
				
				//获取部门按照日期汇总收入开始----------------------------------------------------------------
				addShouru(userSession, beginDate, endDate, szmxMap);
				
				
				//获取部门按照日期汇总支出开始----------------------------------------------------------------
				addCgZhichu(userSession, beginDate, endDate, szmxMap);
				System.out.println(JSON.toJSONString(szmxMap));
				//昨日结余
				BigDecimal srSum = new BigDecimal(0);
				//昨日结余
				BigDecimal zcSum = new BigDecimal(0);
				//对处理过的map计算结存金额
				Date dayDate = beginDate;
				for(int j=0;j<=i;j++){
					//昨日结余
					BigDecimal zrJieyu = new BigDecimal(0);
					//如果map中有这个key则添加value到list中
					if(szmxMap.containsKey(dayDate)){
						zrJieyu = szmxMap.get(dayDate).getJieyu();
					}
					//日期增加一天
					dayDate = DateStringUtil.addDateOneDay(dayDate);
					//设置结余为收入+昨日结余+定额-支出
					if(szmxMap.containsKey(dayDate)){
						szmxMap.get(dayDate).setJieyu(szmxMap.get(dayDate).getShouru().add(zrJieyu).add(szmxMap.get(dayDate).getDinge()).subtract(szmxMap.get(dayDate).getZhichu()));
						srSum=srSum.add(szmxMap.get(dayDate).getShouru());
						zcSum=zcSum.add(szmxMap.get(dayDate).getZhichu());
					}
				}
				System.out.println(JSON.toJSONString(srSum));
				System.out.println(JSON.toJSONString(zcSum));
				
				
				
				
				
				
				
				
				
//				List<HsfSzmx> hsfSzmxList = new ArrayList<HsfSzmx>();
				
				
				
				//map转list
//				mapToList(begingDate, szmxMap, i, hsfSzmxList);
				
				
				
					try {
						//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
						
						
						
						HsfSzmx reSzmx = szmxMap.get(endDate);
//						logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
					
						TabHsfJiecun record = new TabHsfJiecun();
						//部门
						record.setDepId(userSession.getBumenId());
						//结存金额
						record.setJiecunMoney(reSzmx.getJieyu());
						//录入人员
						record.setUserId(userSession.getUserId());
						//结存人数
						record.setJiecunRs((int) reSzmx.getRs());
						//结账日期
						record.setJiecunMonth(endDate);
						//设置结存状态   0=未通过     1=提交   2=通过
 						record.setJiecunState(1);
						hsfJcServiceI.insertSelective(record);
						
						// {"total":10 , "rows":[{},{}]}
//						String json = "{\"total\":" + hsfSzmxList.size() + " , \"rows\":"
//								+ JSON.toJSONStringWithDateFormat(hsfSzmxList, "yyyy-MM-dd") + "}";
//						
//						
						resp.getWriter().write("1");
						
						
					} catch (Exception e) {
						
						e.printStackTrace();
					} 
				
					
					
					
					
					return null;
				}

			
			private void addCgZhichu(CbhsUser userSession, Date beginDate, Date endDate,
					Map<Date, HsfSzmx> szmxMap) {
				TabHsfCgExample example = new TabHsfCgExample();
				com.sc.dmh.beans.TabHsfCgExample.Criteria c = example.createCriteria();
				 
				 
				//查询当前用户部门记录
				c.andCgDepidEqualTo(userSession.getBumenId());
				//结存日期加一天到结束日期间 
				c.andCgDateBetween(DateStringUtil.addDateOneDay(beginDate), endDate);
				//获取收入日期汇总 
				List<TabHsfCg> cgList = cgServiceI.selectSumByDepIdAndDate(example);
				
				//遍历所有收入记录
				for(TabHsfCg cg : cgList){
					//如果map中有当前日期key
					if(szmxMap.containsKey(cg.getCgDate())){
//						logger.info(JSON.toJSONStringWithDateFormat(sr.getShouruDate(), "yyyy-MM-dd HH:mm:ss"));
						//获取map中收入日期对应对象，设置map收入为收入记录中的金额
						szmxMap.get(cg.getCgDate()).setZhichu(cg.getCgMoney());
//						logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
					};
					
				}
			}

			private void addShouru(CbhsUser userSession, Date beginDate, Date endDate,
					Map<Date, HsfSzmx> szmxMap) {
				TabHsfShouruExample example= new TabHsfShouruExample();
				com.sc.dmh.beans.TabHsfShouruExample.Criteria c = example.createCriteria();
				 
				 
				//查询当前用户部门记录
				c.andDepIdEqualTo(userSession.getBumenId());
				//结存日期加一天到结束日期间 
				c.andShouruDateBetween(DateStringUtil.addDateOneDay(beginDate), endDate);
				//获取收入日期汇总 
				List<TabHsfShouru> shouru = ShouruServiceI.selectSumByDepIdAndDate(example);
				
				//遍历所有收入记录
				for(TabHsfShouru sr : shouru){
					//如果map中有当前日期key
					if(szmxMap.containsKey(sr.getShouruDate())){
//						logger.info(JSON.toJSONStringWithDateFormat(sr.getShouruDate(), "yyyy-MM-dd HH:mm:ss"));
						//获取map中收入日期对应对象，设置map收入为收入记录中的金额
						szmxMap.get(sr.getShouruDate()).setShouru(sr.getShouruMoney());
//						logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
					};
					
				}
			}

			


			private void getRy(Date beginDate, Date endDate, Map<Date, HsfSzmx> szmxMap, int i, CbhsUser userSession) {
				
				TabHsfPersonExample example = new TabHsfPersonExample();
				com.sc.dmh.beans.TabHsfPersonExample.Criteria c = example.createCriteria();
				//设置查询添加为开始日期加一天，结束日期，开始日期已经结账了
				c.andPersonTimeBetween(DateStringUtil.addDateOneDay(beginDate), endDate);
				//
				c.andDepIdEqualTo(userSession.getBumenId());
				
				//查询开始到结束日期间的记录
				List<TabHsfPerson> person = personServiceI.selectByExample(example);
				
				
				//结存人数
				double yesterdayJcrs = 0;
				//获取开始日期结存人数
				yesterdayJcrs = szmxMap.get(beginDate).getRs();
				
				//记录当天日期
				Date dayDate = new Date();
				
				dayDate = DateStringUtil.addDateOneDay(beginDate);
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

			private void szmxInitializat(Date beginDate, Map<Date, HsfSzmx> szmxMap, List<TabHsfJiecun> user, int i) {
				for(int j=0;j<=i;j++){
					//List<HsfSzmx> szmxList = new ArrayList<HsfSzmx>();
					HsfSzmx szmx= new HsfSzmx();
					//设置日期
					
					szmx.setSzmxDate(beginDate);
					//设置人员定额为103/30=3.41元
					szmx.setRydinge(3.41);
					//如果j=0最后一次结账日期设置结存人数、结存金额、摘要
					if(j==0){
						//设置首行结存信息
						setTop(user, szmx);
					}
					//key为日期,map添加对象
					szmxMap.put(beginDate, szmx);
//					logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
					//日期加一天
					beginDate=DateStringUtil.addDateOneDay(beginDate);
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
						
			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getJzAll")
						public String getJzAll(Integer depId, TabHsfJiecun formJz, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							// @PathVariable
							// String
							// id(路径变量如@RequestMapping("/{id}/showUser"))
							/*
							 * 
							 * 2. 校验表单数据 3. 使用service查询，得到User 4. 查看用户是否存在，如果不存在： * 保存错误信息：用户名或密码错误
							 * * 保存用户数据：为了回显 * 转发到login.jsp 5. 如果存在，查看状态，如果状态为false： * 保存错误信息：您没有激活
							 * * 保存表单数据：为了回显 * 转发到login.jsp 6. 登录成功： 　　* 保存当前查询出的user到session中 *
							 * 保存当前用户的名称到cookie中，注意中文需要编码处理。
							 */
							/*
							 * 2. 校验
							 */

							/*
							 * 3. 调用userService查询方法
							 */
							resp.setContentType("text/html;charset=utf-8");
							//创建点名会对象
							//TabRpf formRpfFp = new TabRpf();
						    //得到用户
							CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
							//
//							if(userSession != null ){
//								//获取用户部门设置到车站id		
//								formRpfFp.setCzId(userSession.getBumenId());
//								
//							}
						
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
									TabHsfJiecunExample example = new TabHsfJiecunExample();
									Criteria cri = example.createCriteria();
									
									
									
									
									//设置查询条件添加车站id为登录用户部门id
									
									
									if(depId==null){
										cri.andDepIdEqualTo(userSession.getBumenId());
										
									}else{
										cri.andDepIdEqualTo(depId);
									}
									
									
									
									
									
									//设置查询条件添加日期条件
									
//									cri.andCgDateEqualTo(formJz.getCgDate());
//									
									//查询当月当前车站售票记录
									List<TabHsfJiecun> jzAll = hsfJcServiceI.selectByExample(example);
									
									
									
									
//									//复制软票费列表到页面列表
//									if(rpfAll.size()>0){
//										
//										for(TabHsfCg t : rpfAll){
//											if(t.getKemuParentid() == 999999999 ){
//												list.add(TreeHelper.getComboTree(t.getKemuId().toString(),null, t.getKemuName()));
//											}else{
//												list.add(TreeHelper.getComboTree(t.getKemuId().toString(),t.getKemuParentid().toString(), t.getKemuName()));
//											}
//										}
//										
//									}
//									
//									
//									List<ComboTree> l= TreeHelper.makeTree(list);
//									
//									
//									
									
									
									
									// {"total":10 , "rows":[{},{}]}
									String json = "{\"total\":" + jzAll.size() + " , \"rows\":"
											+ JSON.toJSONStringWithDateFormat(jzAll, "yyyy-MM-dd") + "}";
									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							} 

			
	
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 修改班计划
						@RequestMapping("/deleteJz")
						public String dxcDelete(String ids, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
							//统一设置编码
							resp.setContentType("text/html;charset=utf-8");
							if(ids == null) return "";
							
							//转换参数为list
							String[] idsArr = ids.split(",");
							
							Integer [] num=new Integer[idsArr.length];
							
							for(int i=0;i<num.length;i++){
						            num[i]=Integer.parseInt(idsArr[i]);
						    }
							
							
							List<Integer> idList = java.util.Arrays.asList(num);
							
							
							
							int delNumb = 0;
							try {
								TabHsfJiecunExample example = new TabHsfJiecunExample();
								Criteria criteria = example.createCriteria();
								
								//设置查询条件添加日期条件
								criteria.andJiecunIdIn(idList);
//								
								 delNumb = hsfJcServiceI.deleteByExample(example);
								
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							resp.getWriter().write( delNumb + "");

								
							

							return null;

						}
				
				
				/**
				 * form表单提交 Date类型数据绑定 <功能详细描述>
				 * 
				 * @param binder
				 * @see [类、类#方法、类#成员]
				 */
				@InitBinder
				public void initBinder(WebDataBinder binder) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					dateFormat.setLenient(false);
					
					
					binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
					
//					SimpleDateFormat dateFormat = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					dateFormat.setLenient(false);
//					
//					
//					binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
					
				}
	
	
}
