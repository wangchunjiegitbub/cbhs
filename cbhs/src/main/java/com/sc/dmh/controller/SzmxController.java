package com.sc.dmh.controller;

import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sc.dmh.HsfShouruTest3;
import com.sc.dmh.annotation.AuthPassport;
import com.sc.dmh.beans.CbhsUser;
import com.sc.dmh.beans.CbhsUserExample;
import com.sc.dmh.beans.ComboTree;
import com.sc.dmh.beans.DateMonthFirstLast;
import com.sc.dmh.beans.HsfSzmx;
import com.sc.dmh.beans.TabDepartment;
import com.sc.dmh.beans.TabHsfCg;
import com.sc.dmh.beans.TabHsfCgExample;
import com.sc.dmh.beans.TabHsfJiecun;
import com.sc.dmh.beans.TabHsfPerson;
import com.sc.dmh.beans.TabHsfPersonExample;
import com.sc.dmh.beans.TabHsfShouru;
import com.sc.dmh.beans.TabHsfShouruExample;
import com.sc.dmh.beans.ViewCg;
import com.sc.dmh.beans.ViewCgExample;
import com.sc.dmh.beans.ViewCgExample.Criteria;
import com.sc.dmh.service.inter.DepartmentServiceI;
import com.sc.dmh.service.inter.HsfCgServiceI;
import com.sc.dmh.service.inter.HsfJcServiceI;
import com.sc.dmh.service.inter.HsfPersonServiceI;
import com.sc.dmh.service.inter.HsfShouruServiceI;
import com.sc.dmh.service.inter.UserServiceI;
import com.sc.dmh.service.inter.ViewCgServiceI;
import com.sc.dmh.util.DateStringUtil;
import com.sc.dmh.util.ExcelUtil;
import com.sc.dmh.util.MyBeanUtils;
import com.sc.dmh.util.TreeHelper;


import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;  
import org.springframework.context.annotation.Scope;  
import org.springframework.http.HttpHeaders;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.MediaType;  
import org.springframework.http.ResponseEntity;  
import org.springframework.stereotype.Component;  



@Controller
@RequestMapping("/hsf")
public class SzmxController {
	private static final Logger logger = Logger.getLogger(SzmxController.class);
	@Autowired
	private HsfJcServiceI hsfJcServiceI;
	@Autowired
	private HsfPersonServiceI personServiceI;
	@Autowired
	private ViewCgServiceI viewCgService;
	@Autowired
	private HsfCgServiceI hsfCgService;
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private HsfShouruServiceI ShouruServiceI;
	
	@Autowired
	private HsfCgServiceI cgServiceI;
	
	@Autowired
	private DepartmentServiceI depServiceI;
	
	

	public void setDepServiceI(DepartmentServiceI depServiceI) {
		this.depServiceI = depServiceI;
	}


	public void setCgServiceI(HsfCgServiceI cgServiceI) {
		this.cgServiceI = cgServiceI;
	}


	public void setShouruServiceI(HsfShouruServiceI shouruServiceI) {
		ShouruServiceI = shouruServiceI;
	}


	public void setHsfJcServiceI(HsfJcServiceI hsfJcServiceI) {
		this.hsfJcServiceI = hsfJcServiceI;
	}


	public void setPersonServiceI(HsfPersonServiceI personServiceI) {
		this.personServiceI = personServiceI;
	}


	public void setViewCgService(ViewCgServiceI viewCgService) {
		this.viewCgService = viewCgService;
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
	@RequestMapping("/szmx")
	public String showKpyl( 
			HttpServletRequest request, 
			HttpServletResponse resp) 
			throws IOException {
			//返回的视图
		
			return "hsf/szmx";
		}
	
	
//			// 自定义权限验证注解加注解时表示有权限是才能访问
//			@AuthPassport
//			// 添加客票管理中的客票用量中的粉票
//			@RequestMapping("/addCg")
//			public String fpAdd(TabHsfCg formHsfCg, HttpServletRequest request,
//					HttpServletResponse resp) throws IOException {
//				// @PathVariable
//				// String
//				// id(路径变量如@RequestMapping("/{id}/showUser"))
//				/*
//				 * 
//				 * 2. 校验表单数据 3. 使用service查询，得到User 4. 查看用户是否存在，如果不存在： * 保存错误信息：用户名或密码错误
//				 * * 保存用户数据：为了回显 * 转发到login.jsp 5. 如果存在，查看状态，如果状态为false： * 保存错误信息：您没有激活
//				 * * 保存表单数据：为了回显 * 转发到login.jsp 6. 登录成功： 　　* 保存当前查询出的user到session中 *
//				 * 保存当前用户的名称到cookie中，注意中文需要编码处理。
//				 */
//				/*
//				 * 2. 校验
//				 */
//
//				/*
//				 * 3. 调用userService查询方法
//				 */
//				resp.setContentType("text/html;charset=utf-8");
//				//创建点名会对象
//				//TabRpf formRpfFp = new TabRpf();
//				//得到用户
//				CbhsUser userSession = (CbhsUser) request.getSession().getAttribute("sessionUser");
//				//
//				if(userSession != null ){
//					//设置用户	
//					formHsfCg.setCgUserid(userSession.getUserId());
//					//设置部门
//					formHsfCg.setCgDepid(userSession.getBumenId());
//				}
//			
//				
//				
//					try {
//						
//						//添加粉票记录
//						//int i = rpfService.insertSelective(formRpfFp);
//						
//						int i  = hsfCgService.insertSelective(formHsfCg);
//						
//						resp.getWriter().write(i + "");
//						
//					} catch (Exception e) {
//						
//						e.printStackTrace();
//					} 
//				
//					
//					
//					
//					
//					return null;
//				} 
						
			
						// 自定义权限验证注解加注解时表示有权限是才能访问
						@AuthPassport
						// 获取所有
						@RequestMapping("/getSzmxAll")
						public String getSzmxAll(Integer depId,String formEndDate, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {
							
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
						
//							-----------------------------------------------------------------------------------------------------
							
							
							Date begingDate;
							//获取结束日期
							Date endDate = DateStringUtil.toDate(formEndDate, "yyyy-MM-dd");
							
							//创建收支明细map对象键为日期
							//Map<String,List<HsfSzmx>> szmxMap = new HashMap<String,List<HsfSzmx>>();
							//创建收支明细map对象键为日期
							Map<Date,HsfSzmx> szmxMap = new HashMap<Date,HsfSzmx>();
							
							
							
							
							if(depId==null){
								depId=userSession.getBumenId();
							}
							//根据部门id获取最后结存记录
							List<TabHsfJiecun> hsfJc = hsfJcServiceI.selectLastByDepId((long)depId);
							//设置开始日期为结存记录日期
							begingDate = hsfJc.get(0).getJiecunMonth();
							
							//获取日期差
							int i=0;
							
							try {
								i = DateStringUtil.daysBetween(begingDate, endDate);
								//日期差不能小于0
								if(i<0)return null;
							} catch (ParseException e1) {
								
								e1.printStackTrace();
							}
							
							//map中添加日期及收支明细对象初始化首行及其他各行日期人员定额参数---收支明细初始化方法
							szmxInitializat(begingDate, szmxMap, hsfJc, i);
							
							
							
							//人员结存情况处理开始---------------------------------------------------------------
							
							//得到人员变动情况
							getRy(begingDate, endDate, szmxMap, i, depId);
							
							
							//获取部门按照日期汇总收入开始----------------------------------------------------------------
							addShouru(depId, begingDate, endDate, szmxMap);
							
							
							//获取部门按照日期汇总支出开始----------------------------------------------------------------
							addCgZhichu(depId, begingDate, endDate, szmxMap);
							
							//对处理过的map计算结存金额
							Date dayDate;
							getJiecunMoney(begingDate, szmxMap, i);
							
							//计算定额、收入、支出合计数
							
							getSum(begingDate, endDate, szmxMap, i);
							
							
							
							List<HsfSzmx> hsfSzmxList = new ArrayList<HsfSzmx>();
							
							
							
							//map转list
							mapToList(begingDate, szmxMap, i, hsfSzmxList);
							
							
							
								try {
									//DateMonthFirstLast a = DateStringUtil.getDateFirstLast(formRpfFp.getInDate());
									
									
									
									
									
								
									
									
									// {"total":10 , "rows":[{},{}]}
									String json = "{\"total\":" + hsfSzmxList.size() + " , \"rows\":"
											+ JSON.toJSONStringWithDateFormat(hsfSzmxList, "yyyy-MM-dd") + "}";
									
									
									resp.getWriter().write(json);
									
									
								} catch (Exception e) {
									
									e.printStackTrace();
								} 
							
								
								
								
								
								return null;
							}


						private void getSum(Date begingDate, Date endDate, Map<Date, HsfSzmx> szmxMap, int i) {
							Date dayDate;
							Date sumDate =  DateStringUtil.addDateOneDay(endDate);
							
							HsfSzmx sumSzmx = new HsfSzmx();
							sumSzmx.setZhaiyao("合计");
							sumSzmx.setRydinge(3.41);
							dayDate = begingDate;
							for(int j=0;j<i;j++){
								
								
								//日期增加一天
								dayDate = DateStringUtil.addDateOneDay(dayDate);
								//计算人数合计、支出合计、收入合计
								if(szmxMap.containsKey(dayDate)){
									sumSzmx.setRs(sumSzmx.getRs() + szmxMap.get(dayDate).getRs());
									sumSzmx.setShouru(sumSzmx.getShouru().add(szmxMap.get(dayDate).getShouru()));
									sumSzmx.setZhichu(sumSzmx.getZhichu().add(szmxMap.get(dayDate).getZhichu()));
								}
								
							}
							szmxMap.put(sumDate, sumSzmx);
						}


						private void getJiecunMoney(Date begingDate, Map<Date, HsfSzmx> szmxMap, int i) {
							Date dayDate = begingDate;
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
								}
							}
						}

						
						private void addCgZhichu(Integer depId, Date begingDate, Date endDate,
								Map<Date, HsfSzmx> szmxMap) {
							TabHsfCgExample example = new TabHsfCgExample();
							com.sc.dmh.beans.TabHsfCgExample.Criteria c = example.createCriteria();
							 
							 
							//查询当前用户部门记录
							c.andCgDepidEqualTo(depId);
							//结存日期加一天到结束日期间 
							c.andCgDateBetween(DateStringUtil.addDateOneDay(begingDate), endDate);
							//获取收入日期汇总 
							List<TabHsfCg> cgList = cgServiceI.selectSumByDepIdAndDate(example);
							
							//遍历所有收入记录
							for(TabHsfCg cg : cgList){
								//如果map中有当前日期key
								if(szmxMap.containsKey(cg.getCgDate())){
//									logger.info(JSON.toJSONStringWithDateFormat(sr.getShouruDate(), "yyyy-MM-dd HH:mm:ss"));
									//获取map中收入日期对应对象，设置map收入为收入记录中的金额
									szmxMap.get(cg.getCgDate()).setZhichu(cg.getCgMoney());
//									logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
								};
								
							}
						}

						private void addShouru(Integer depId, Date begingDate, Date endDate,
								Map<Date, HsfSzmx> szmxMap) {
							TabHsfShouruExample example= new TabHsfShouruExample();
							com.sc.dmh.beans.TabHsfShouruExample.Criteria c = example.createCriteria();
							 
							 
							//查询当前用户部门记录
							c.andDepIdEqualTo(depId);
							//结存日期加一天到结束日期间 
							c.andShouruDateBetween(DateStringUtil.addDateOneDay(begingDate), endDate);
							//获取收入日期汇总 
							List<TabHsfShouru> shouru = ShouruServiceI.selectSumByDepIdAndDate(example);
							
							//遍历所有收入记录
							for(TabHsfShouru sr : shouru){
								//如果map中有当前日期key
								if(szmxMap.containsKey(sr.getShouruDate())){
//									logger.info(JSON.toJSONStringWithDateFormat(sr.getShouruDate(), "yyyy-MM-dd HH:mm:ss"));
									//获取map中收入日期对应对象，设置map收入为收入记录中的金额
									szmxMap.get(sr.getShouruDate()).setShouru(sr.getShouruMoney());
//									logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
								};
								
							}
						}

						private void mapToList(Date begingDate, Map<Date, HsfSzmx> szmxMap, int i,
								List<HsfSzmx> hsfSzmxList) {
							Date dayDate = begingDate;
							//i+1表示添加合计列
							for(int j=0;j<=i+1;j++){
								//如果map中有这个key则添加value到list中
								if(szmxMap.containsKey(dayDate)){
									hsfSzmxList.add(szmxMap.get(dayDate));
								}
								//日期增加一天
								dayDate = DateStringUtil.addDateOneDay(dayDate);
							}
						} 

			
						private void getRy(Date begingDate, Date endDate, Map<Date, HsfSzmx> szmxMap, int i, Integer depId) {
							
							TabHsfPersonExample example = new TabHsfPersonExample();
							com.sc.dmh.beans.TabHsfPersonExample.Criteria c = example.createCriteria();
							//设置查询添加为开始日期加一天，结束日期，开始日期已经结账了
							c.andPersonTimeBetween(DateStringUtil.addDateOneDay(begingDate), endDate);
							//
							c.andDepIdEqualTo(depId);
							
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
								//设置人员定额为1245/365=3.41元
								szmx.setRydinge(3.41);
								//如果j=0最后一次结账日期设置结存人数、结存金额、摘要
								if(j==0){
									//设置首行结存信息
									setTop(user, szmx);
								}
								//key为日期,map添加对象
								szmxMap.put(begingDate, szmx);
//								logger.info(JSON.toJSONStringWithDateFormat(szmxMap, "yyyy-MM-dd HH:mm:ss"));
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

//						// 自定义权限验证注解加注解时表示有权限是才能访问
//						@AuthPassport
//						// 修改班计划
//						@RequestMapping("/deleteCg")
//						public String dxcDelete(String ids, HttpServletRequest request,
//								HttpServletResponse resp) throws IOException {
//							
//							//统一设置编码
//							resp.setContentType("text/html;charset=utf-8");
//							if(ids == null) return "";
//							
//							//转换参数为list
//							String[] idsArr = ids.split(",");
//							
//							Integer [] num=new Integer[idsArr.length];
//							
//							for(int i=0;i<num.length;i++){
//						            num[i]=Integer.parseInt(idsArr[i]);
//						    }
//							
//							
//							List<Integer> idList = java.util.Arrays.asList(num);
//							
//							
//							
//							int delNumb = 0;
//							try {
//								TabHsfCgExample example = new TabHsfCgExample();
//								com.sc.dmh.beans.TabHsfCgExample.Criteria criteria = example.createCriteria();
//								
//								//设置查询条件添加日期条件
//								criteria.andCgIdIn(idList);
////								
//								 delNumb = hsfCgService.deleteByExample(example);
//								
//							} catch (Exception e) {
//								
//								e.printStackTrace();
//							}
//							
//							resp.getWriter().write( delNumb + "");
//
//								
//							
//
//							return null;
//
//						}
				
				
				
						
						@RequestMapping("/downloadSzmx")    
					    public ResponseEntity<byte[]> download(Integer depId,String formEndDate, HttpServletRequest request,
								HttpServletResponse resp) throws IOException {   
					    	
					    	
//					    	resp.setContentType("text/html;charset=utf-8");
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
						
//							-----------------------------------------------------------------------------------------------------
							if(depId==null){
								depId=userSession.getBumenId();
							}
							
							Date begingDate;
							//获取结束日期
							Date endDate = DateStringUtil.toDate(formEndDate, "yyyy-MM-dd");
							
							//创建收支明细map对象键为日期
							//Map<String,List<HsfSzmx>> szmxMap = new HashMap<String,List<HsfSzmx>>();
							//创建收支明细map对象键为日期
							Map<Date,HsfSzmx> szmxMap = new HashMap<Date,HsfSzmx>();
							
							
							
							//根据部门id获取最后结存记录
							List<TabHsfJiecun> hsfJc = hsfJcServiceI.selectLastByDepId((long)depId);
							//设置开始日期为结存记录日期
							begingDate = hsfJc.get(0).getJiecunMonth();
							
							//获取日期差
							int i=0;
							
							try {
								i = DateStringUtil.daysBetween(begingDate, endDate);
								//日期差不能小于0
								if(i<0)return null;
							} catch (ParseException e1) {
								
								e1.printStackTrace();
							}
							
							//map中添加日期及收支明细对象初始化首行及其他各行日期人员定额参数---收支明细初始化方法
							szmxInitializat(begingDate, szmxMap, hsfJc, i);
							
							
							
							//人员结存情况处理开始---------------------------------------------------------------
							
							//得到人员变动情况
							getRy(begingDate, endDate, szmxMap, i, depId);
							
							
							//获取部门按照日期汇总收入开始----------------------------------------------------------------
							addShouru(depId, begingDate, endDate, szmxMap);
							
							
							//获取部门按照日期汇总支出开始----------------------------------------------------------------
							addCgZhichu(depId, begingDate, endDate, szmxMap);
							
							//对处理过的map计算结存金额
							Date dayDate;
							getJiecunMoney(begingDate, szmxMap, i);
							
							//计算定额、收入、支出合计数
							
							getSum(begingDate, endDate, szmxMap, i);
							
							
							
							List<HsfSzmx> hsfSzmxList = new ArrayList<HsfSzmx>();
							
							
							
							//map转list
							mapToList(begingDate, szmxMap, i, hsfSzmxList);
							
							//生成excel文件
							String tempFilePath = "D:/demo.xls";
					        File file = new File("d:/data.xls");
					        OutputStream os = new FileOutputStream(file);
					         
					        ExcelUtil excel = new ExcelUtil();
					        Map<String, Object> dataMap = new HashMap<String, Object>();
					        //部门名称
					        TabDepartment dep = depServiceI.selectByPrimaryKey((long)depId);
					        dataMap.put("C2", dep.getDepartmentName());
					        //添加list最后一行定额合计
					        dataMap.put("D36", hsfSzmxList.get(hsfSzmxList.size()-1).getDinge().toString());
					        //收入合计
					        dataMap.put("E36", hsfSzmxList.get(hsfSzmxList.size()-1).getShouru().toString());
					        //支出合计
					        dataMap.put("F36", hsfSzmxList.get(hsfSzmxList.size()-1).getZhichu().toString());
					        //结余合计统计最后日期
					        dataMap.put("G36", hsfSzmxList.get(hsfSzmxList.size()-2).getJieyu().toString());
					        excel.writeData(tempFilePath, dataMap, 0);
					        //合计添加后移除合计数
					        hsfSzmxList.remove(hsfSzmxList.size()-1);
					        List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer,Object>>();
					        Map<Integer, Object> data = new HashMap<Integer,Object>();
					        for(HsfSzmx szmx : hsfSzmxList){
					        	data = new HashMap<Integer,Object>();
					        	//得到日期字符串2015-02-03
					        	data.put(1, getDate(szmx.getSzmxDate()));
						        data.put(2, szmx.getZhaiyao());
						        data.put(3, szmx.getDinge().toString());
						        //如果收入不为0添加
						        if(!szmx.getShouru().toString().equals("0"))data.put(4, szmx.getShouru().toString());
						        //如果支出不为0添加
						        if(!szmx.getZhichu().toString().equals("0"))data.put(5, szmx.getZhichu().toString());
						        data.put(6, szmx.getJieyu().toString());
						        //data.put(3, szmx.getBeizhu());
						        datalist.add(data);
					        }
					        
					        
					        
					        
					       
					        String[] heads = new String[]{"B3","C3","D3","E3","F3","G3"};   //必须为列表头部所有位置集合，   输出 数据单元格样式和头部单元格样式保持一致
					        excel.writeDateList(tempFilePath,heads,datalist,0);
					         
					        //写到输出流并移除资源
					        excel.writeAndClose(tempFilePath, os);
					          
					        os.flush();
					        os.close();

					    	
					    	
					    	
					    	
					        String path="D:\\data.xls";  
					        File file1=new File(path);  
					        HttpHeaders headers = new HttpHeaders();    
					        //文件名部门名称+时间+xls
					        String fileName=new String((dep.getDepartmentName() + getDate(new Date()) + ".xls").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
					        headers.setContentDispositionFormData("attachment", fileName);   
					        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
					        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
					                                          headers, HttpStatus.CREATED);    
					    }    
						
						
						
					    public String getDate(Date date)
					    {
					        if( null == date ) return "";
					        return new SimpleDateFormat("MM-dd").format(date);
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
